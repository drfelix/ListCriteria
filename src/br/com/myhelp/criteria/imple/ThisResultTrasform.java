/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria.imple;

import br.com.myhelp.criteria.IteratorCriterio;
import br.com.myhelp.criteria.ResultTransform;
import br.com.myhelp.criteria.ValueAtual;
import br.com.myhelp.criteria.aux.Projections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Usado apenas para leitura.
 *
 * ###Cuidado: não é suportado edição de nem um tipo.
 *
 * @author drfelix
 */
public class ThisResultTrasform implements ResultTransform {

    private List<Projections.ProjectionItem> itensProjection;

    @Override
    public void setProjectionItems(List<Projections.ProjectionItem> itens) {
        this.itensProjection = itens;
    }

    @Override
    public List resultTransform(IteratorCriterio filter) throws Exception {
        filter.resetCursor();
        if (itensProjection == null || itensProjection.isEmpty()) {
            return new LisThis(filter);
        } else {
            List listRet = new ArrayList();

            GetProjecttions projec;

            if (itensProjection.size() == 1) {
                projec = new GetUnicValue();
            } else {
                projec = new GetValueToArray();
            }

            for (int i = 0; i < filter.size(); i++) {
                listRet.add(projec.getProjections(filter.get(i)));
            }

            return listRet;
        }
    }

    private class LisThis extends ArrayList {

        private IteratorCriterio iterar;

        public LisThis(IteratorCriterio iterar) {
            this.iterar = iterar;
        }

        @Override
        public boolean isEmpty() {
            return size() == 0;
        }

        @Override
        public Object get(int index) {
            try {
                Object c = iterar.get(index).getThis();
                if (c instanceof ValueAtual.IsNotArray) {
                    return ((ValueAtual.IsNotArray) c).getValues();
                } else {
                    return c;
                }
            } catch (Exception ex) {
                throw new ArrayIndexOutOfBoundsException();
            }
        }

        @Override
        public int size() {
            return iterar.size();
        }

        @Override
        public Iterator iterator() {
            return new IteraThis(iterar);
        }

    }

    private class IteraThis implements Iterator {

        IteratorCriterio crite;

        public IteraThis(IteratorCriterio crite) {
            this.crite = crite;
            this.crite.setCriterios(null);
        }

        @Override
        public boolean hasNext() {
            try {
                return crite.hashNext();
            } catch (Exception ex) {
                return false;
            }
        }

        @Override
        public Object next() {
            try {
                return crite.next().getThis();
            } catch (Exception ex) {
                Logger.getLogger(ThisResultTrasform.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }

    }

    private interface GetProjecttions {

        public Object getProjections(ValueAtual value) throws Exception;
    }

    public class GetValueToArray implements GetProjecttions {

        @Override
        public Object getProjections(ValueAtual value) throws Exception {
            Object[] obj = new Object[itensProjection.size()];
            int i = 0;
            for (Projections.ProjectionItem iten : itensProjection) {
                obj[i] = iten.getValorCampo(value);
                i++;
            }
            return obj;
        }

    }

    public class GetUnicValue implements GetProjecttions {

        @Override
        public Object getProjections(ValueAtual value) throws Exception {
            return itensProjection.get(0).getValorCampo(value);
        }

    }

}
