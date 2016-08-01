/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria.imple;

import br.com.myhelp.criteria.Criterios.CriteriaBoolean;
import br.com.myhelp.criteria.FilterCampos;
import br.com.myhelp.criteria.IteratorCriterio;
import br.com.myhelp.criteria.ValueAtual;
import java.util.ArrayList;
import java.util.List;

/**
 * Nessa class sera feito o join de subLista. o metodo resetCursor da iter\tor2
 * vai pular um cursor.
 * 
 * O metodo "size" dos iteratores tém comportamentos differentes:
 * 1-O ite1.size() vai ser 1 quando ite2.getIteraCrit() for null e ite1.size() quando
 * o ite2.getIteraCrit() não for null.
 * 2-O ite2.size vai ser iteraCrit.size() quando iteraCrit não for null e somaSize2 quando
 * iteraCrit for null.
 * Isso indica que o segundo não esta setado
 * 
 * 
 *
 * @author drfelix
 */
public class DefaultFilterCampo implements FilterCampos, ValueAtual.MelhoraDesempenho {

    private Object campo;
    private String[] as;
    
    private Iterator1 ite1;
    private Iterator2 ite2;
//    enquanto o metodo next do primeiro não for acionado é usado esse como size.
    private int somaSize2 = 0;

    public DefaultFilterCampo(String as, Object campo) {
        this.campo = campo;
        this.as = new String[]{as};
        this.ite2 = new Iterator2();
        
    }

    @Override
    public void setCampo(Object campo) {
        this.campo = campo;
    }

    @Override
    public void setValueAt(Object valor, int index) {
        throw new UnsupportedOperationException("Not supported yet DefaultFilterCampo.");
    }

    @Override
    public IteratorCriterio iterator2() {
        return ite2;
    }

    @Override
    public IteratorCriterio iterator1() {
        return ite1;
    }

    @Override
    public String[] asIndex() {
        return this.as;
    }

    @Override
    public void setIteratorCriterio1(IteratorCriterio iterator) {
        ite1 = new Iterator1(iterator);

        try {
            Object value;
            
//            soma o valor que tem na lista 2
            while (ite1.hashNext()) {
                value = ite1.next().getCampo(campo, this);
                if(value instanceof List){
                    somaSize2 +=((List) value).size();
                }else if (value instanceof Object[]){
                    somaSize2+=((Object[]) value).length;
                }else{
                    throw new IllegalArgumentException(value+" Nao suportavel");
                }
            }
            
            ite1.resetCursor();
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }

        ite2.setIteraCrit(null);
    }

    
    /**
     * Encapsula um iterator com mudanças para setar o iterator2 para que possa ser lido.
     */
    private class Iterator1 implements IteratorCriterio {

        public Iterator1(IteratorCriterio iteraInter1) {
            this.iteraInter1 = iteraInter1;
        }
        private IteratorCriterio iteraInter1;

        @Override
        public void setCriterios(List<CriteriaBoolean> crits) throws IllegalArgumentException {
            iteraInter1.setCriterios(crits);
        }

        @Override
        public boolean hashNext() throws Exception {
            return iteraInter1.hashNext();
        }

        @Override
        public ValueAtual next() throws Exception {
            ValueAtual value = iteraInter1.next();
            setList(value.getCampo(campo, DefaultFilterCampo.this));
            return value;
        }

        @Override
        public void resetCursor() throws Exception {
            ite2.setIteraCrit(null);
            iteraInter1.resetCursor();
        }

        @Override
        public int size() {
            return ite2.getIteraCrit()==null?1:iteraInter1.size();
        }

        @Override
        public ValueAtual get(int eIndex) throws Exception {
            setList(iteraInter1.get(eIndex).getCampo(campo, DefaultFilterCampo.this));
            return iteraInter1.get(eIndex);
        }

        @Override
        public String[] asIndex() throws Exception {
            return iteraInter1.asIndex();
        }

        private void setList(Object obj) {
            List lista = null;
            if (obj instanceof List) {
                lista = new ArrayList();
                int i = 0;
                for(Object obj1 : ((List)obj)){
                    lista.add(new DefaultValueAtual(as[0], i, obj1));
                    i++;
                }
            } else if (obj instanceof Object[]) {
                Object[] listObj = (Object[]) obj;
                lista = new ArrayList<Object>();
                int i = 0;
                for (Object obj1 : listObj) {
                    lista.add(new DefaultValueAtual(as[0], i, obj1));
                    i++;
                }
            }
            
            ite2.setIteraCrit(new ListIteratorCriterio(lista, as));
        }

    }

    /**
     * suportado List e Object[]
     * 
     * E apenas um encapsulador de iteratorCriterio para que possa ser mudado o iterator com facilidade.
     * 
     */
    private class Iterator2 implements IteratorCriterio {

        IteratorCriterio iteraCrit;
        List<CriteriaBoolean> criterios;

        
        
        public void setIteraCrit(IteratorCriterio iteraCrit) {
            this.iteraCrit = iteraCrit;
            if (this.iteraCrit != null) {
                this.iteraCrit.setCriterios(criterios);
            }
        }
        

        
        
        @Override
        public void setCriterios(List<CriteriaBoolean> crits) throws IllegalArgumentException {
            criterios = crits;
        }

        @Override
        public boolean hashNext() throws Exception {
            return iteraCrit.hashNext();
        }

        @Override
        public ValueAtual next() throws Exception {
            return iteraCrit.next();
        }

        @Override
        public void resetCursor() throws Exception {
            iteraCrit.resetCursor();
        }

        public IteratorCriterio getIteraCrit() {
            return iteraCrit;
        }
        
        

        @Override
        public int size()  {
            return this.iteraCrit == null? somaSize2:this.iteraCrit.size();
        }

        @Override
        public ValueAtual get(int eIndex) throws Exception {
            return iteraCrit.get(eIndex);
        }

        @Override
        public String[] asIndex() {
            return as;
        }

    }

}
