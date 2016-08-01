/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria.imple;

import br.com.myhelp.criteria.Criterios.CriteriaBoolean;
import br.com.myhelp.criteria.IteratorCriterio;
import br.com.myhelp.criteria.Join;
import br.com.myhelp.criteria.ValueAtual;
import br.com.myhelp.util.Util;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author drfelix
 */
public class LeftJoin implements Join {

    private CriteriaBoolean on;
    private MelhoraJoin joinMelho;

    public LeftJoin(CriteriaBoolean on) {
        this.on = on;
        joinMelho = new MelhoraJoin();
    }

    /**
     * .
     * filter1 = 2|8|1|23|3
     * filter2 = 8|9|2|23|7|1|5|1
     * on = f1 = f2
     * ---------------------------
     * 2|2
     * 8|8
     * 1|1
     * 1|1
     * 23|23
     * 3|null
     *
     * @param filter1
     * @param filter2
     * @return
     * @throws Exception
     */
    @Override
    public IteratorCriterio join(IteratorCriterio filter1, IteratorCriterio filter2) throws Exception {
//        lista usada para retorno
        List<ValueAtual> retorno = new ArrayList<>();
//        join os as em um só
        String[] asIndex = Util.addAll(filter1.asIndex(), filter2.asIndex());
//        value usado para o test
        JoinValueAtual value = null;
//        guarda o value do primeiro
        ValueAtual value1;
//        me diz se esta vazil.
        boolean valueVazil = true;
//        percorro todos do primeiro.
        while (filter1.hashNext()) {
//            me informa que esta vazil
            valueVazil = true;
//            pego o valor do primeiro
            value1 = filter1.next();
//            percorro o segundo
            while (filter2.hashNext()) {
//                caso seja null inicio um
                if (value == null) {
                    value = new JoinValueAtual(value1, filter2.next(), joinMelho, asIndex);
                } else {
//                    caso não apenas seto com o valor do filtro2
                    value.setValue2(filter2.next());
                }
//                caso respeitar o "on" eu add o value digo que não esta vazil e zero value
                if (myEquals(value)) {
                    retorno.add(value);
                    valueVazil = false;
                    value = null;
                }

            }
            if (valueVazil) {
                value.setValue2(new ValueAtualVazil(filter2.asIndex()));
                retorno.add(value);
            }
            valueVazil = true;
            value = null;
            filter2.resetCursor();
        }
        filter1.resetCursor();
        return new ListIteratorCriterio(retorno, asIndex);
    }

    private boolean myEquals(ValueAtual e) throws Exception {
        Object test = null;
        if (on.getCampo() != null) {
            test = e.getCampo(on.getCampo(), on);
        } else {
            test = null;
        }
        return on.myEquals(test, e);
    }

    private class ValueAtualVazil implements ValueAtual {

        public ValueAtualVazil(String[] as) {
            this.as = as;
        }

        private String[] as;

        @Override
        public Object getValue(Object ob, MelhoraDesempenho md, int index) throws Exception {
            return null;
        }

        @Override
        public Object getCampo(Object campo, MelhoraDesempenho md) throws Exception {
            return null;
        }

        @Override
        public int getIndex(String as) throws IllegalArgumentException {
            return -1;
        }

        @Override
        public Object getThis() {
            return null;
        }

        @Override
        public String[] as() {
            return as;
        }

    }

}
