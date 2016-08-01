/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria.imple;

import br.com.myhelp.criteria.ValueAtual;
import br.com.myhelp.criteria.aux.CriteriaName;
import br.com.myhelp.util.Util;

/**
 * Essa class sera usada para armazenar o resultado do join.
 * Por isso pode ocorrer que um dos values atuais terem valores null.
 * Assim quando for fazer a projeção devese de alguma forma verificar o valor null.
 *
 * @author drfelix
 */
public class JoinValueAtual implements ValueAtual {

    private ValueAtual value1;
    private ValueAtual value2;
    
    private String[] asIndex;

    private MelhoraJoin joinMelhora;

    public JoinValueAtual(ValueAtual value1, ValueAtual value2,MelhoraJoin joinMelhora,String[] asIndex) {
        this.value1 = value1;
        this.value2 = value2;
        this.joinMelhora = joinMelhora;
        this.asIndex = asIndex;
    }

    @Override
    public Object getValue(Object ob, ValueAtual.MelhoraDesempenho md, int index) throws Exception {
        joinMelhora.setMd(md);
        if (ob instanceof MelhoraJoin.ModelInternoJoin) {
            MelhoraJoin.ModelInternoJoin ij = (MelhoraJoin.ModelInternoJoin) ob;

            joinMelhora.setAs(ij.getAs());
            return findValue(ij.getAs()).getValue(ij.getCampo(), joinMelhora, index);
        } else if (ob instanceof CriteriaName) {
            joinMelhora.setAs(((CriteriaName) ob).getNome());
            return findValue(joinMelhora.getAs()).getValue(ob, joinMelhora, index);
        }
        return ob;
    }

    @Override
    public Object getCampo(Object campo, ValueAtual.MelhoraDesempenho md) throws Exception {
        joinMelhora.setMd(md);
        if (campo instanceof MelhoraJoin.ModelInternoJoin) {
            MelhoraJoin.ModelInternoJoin mij = (MelhoraJoin.ModelInternoJoin) campo;
            joinMelhora.setAs(mij.getAs());
            return findValue(mij.getAs()).getCampo(mij.getCampo(), joinMelhora);
            
        } else if (campo instanceof String) {
            joinMelhora.setAs(campo + "");
            return findValue(campo + "").getCampo(campo, joinMelhora);
        } else if (campo instanceof CriteriaName) {
            joinMelhora.setAs(((CriteriaName) campo).getNome());
            return findValue(((CriteriaName) campo).getNome()).getCampo(campo, joinMelhora);
        }
        throw new IllegalArgumentException(campo + "- tem que ser uma string ou criterioName");
    }

    @Override
    public int getIndex(String asIndex) throws IllegalArgumentException {
        return findValue(asIndex).getIndex(asIndex);
    }

    public void setValue1(ValueAtual value1) {
        this.value1 = value1;
    }

    public void setValue2(ValueAtual value2) {
        this.value2 = value2;
    }

    
    
    /**
     *
     * @param as representa o campo que quero pegar
     * @return
     */
    public ValueAtual findValue(String as) {
        for (String as1 : value1.as()) {
            if (Util.likeToThis(as, as1)) {
                return value1;
            }
        }
        for (String as1 : value2.as()) {
            if (Util.likeToThis(as, as1)) {
                return value2;
            }
        }
        throw new IllegalArgumentException(as + "- Não encontrado.");
    }

    @Override
    public String[] as() {
        return asIndex;
    }

    @Override
    public Object getThis() {
        Object[] array1;
        Object[] array2;
        if (value1.getThis() instanceof ValueAtual.IsNotArray) {
            array1 = ((ValueAtual.IsNotArray) value1.getThis()).getValues();
        } else {
            array1 = new Object[]{value1.getThis()};
        }

        if (value2.getThis() instanceof ValueAtual.IsNotArray) {
            array2 = ((ValueAtual.IsNotArray) value2.getThis()).getValues();
        } else {
            array2 = new Object[]{value2.getThis()};
        }

        return new ValueAtual.IsNotArray(Util.addAll(array1, array2));
    }

    
    /**
     * 
     */
    

   

}
