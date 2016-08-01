/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria.imple;

import br.com.myhelp.criteria.ValueAtual;
import br.com.myhelp.criteria.aux.CriteriaName;
import br.com.myhelp.util.Util;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Nesse caso aqui irei guardar o caminho do objeto que eu quero, ou o objeto
 * passado como parametro.
 *
 * @Economisa a extração da List<Field>, caso o tenha.
 *
 * #PASSOS #getValue
 * @Se "ob" not instance CriteriaName seto o "ob" e o retorno.
 * @Se "ob" instance CriteriaName -> "cn" && "cn.percence" != null : estraio do
 * jeito convencional. ; Utiliza-se o "cn.percence" para estrair.
 *
 *
 * @author drfelix
 */
public class DefaultValueAtual implements ValueAtual {
    

    /**
     *
     * @param as
     * @param index
     * @param valorAtual
     */
    public DefaultValueAtual(String as, int index,Object valorAtual) {
        this.as = new String[]{as};
        this.index = index;
        this.valorAtual = valorAtual;
        
    }
    
    private Object valorAtual = null;
    private String[] as;
    private int index;

    @Override
    public Object getValue(Object ob, MelhoraDesempenho md, int index) throws Exception {
//        caso seja a primeira vez que isso ocorre e seja CriteriaName

        if (ob instanceof ModelInternoFields) {
            return Util.pegeValue(((ModelInternoFields) ob).getCampos(), valorAtual);
        } else if (ob instanceof CriteriaName) {
            CriteriaName cri = (CriteriaName) ob;
            if (cri.getPercence() == null) {
                cri.setPercence(valorAtual.getClass());
            }
            ModelInternoFields fields = new ModelInternoFields(Util.extractCampo(cri.getNome(), cri.getPercence(), as[0]));
            md.setValueAt(fields, index);
            return Util.pegeValue(fields.getCampos(), valorAtual);
        } else {
            return ob;
        }
    }

    @Override
    public Object getCampo(Object campo, MelhoraDesempenho md) throws Exception {
        if (campo instanceof ModelInternoFields) {
            return Util.pegeValue(((ModelInternoFields) campo).getCampos(), valorAtual);
        }else{
            ModelInternoFields fields = new ModelInternoFields(campos(valorAtual, campo));
            md.setCampo(fields);
            return Util.pegeValue(fields.getCampos(),valorAtual);
        }
    }

    public void setValorAtual(Object valorAtual) {
        this.valorAtual = valorAtual;
    }

    private List<Field> campos(Object e, Object campo) throws NoSuchFieldException {
        Class auxCl = e.getClass();
        if (campo instanceof String) {
            return Util.extractCampo(((String) campo), auxCl,as[0]);
        } else if (campo instanceof CriteriaName) {
            CriteriaName cri = (CriteriaName) campo;
            if(cri.getPercence() == null){
                cri.setPercence(auxCl);
            }
            return Util.extractCampo(cri.getNome(), cri.getPercence(),as[0]);
        } else {
            throw new IllegalArgumentException("O Objeto passado no valor do campo tem que ser do tipo \"String\" ou \"CriteriaName\"");
        }
    }

    @Override
    public int getIndex(String as) throws IllegalArgumentException {
        return this.index;
    }

    @Override
    public String[] as() {
        return as;
    }

    @Override
    public Object getThis() {
        return valorAtual;
    }

    public class ModelInternoFields {

        private List<Field> campos;

        public ModelInternoFields(List<Field> campos) {
            this.campos = campos;
        }

        public List<Field> getCampos() {
            return campos;
        }

        @Override
        public String toString() {
            return "ModelInternoFields{" + "campos=" + campos + '}';
        }
        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultValueAtual other = (DefaultValueAtual) obj;
        if (!Objects.equals(this.valorAtual, other.valorAtual)) {
            return false;
        }
        if (!Arrays.deepEquals(this.as, other.as)) {
            return false;
        }
        if (this.index != other.index) {
            return false;
        }
        return true;
    }

    
    
    
}
