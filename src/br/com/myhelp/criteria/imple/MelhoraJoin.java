/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria.imple;

import br.com.myhelp.criteria.ValueAtual;

/**
 *
 * @author drfelix
 */
public class MelhoraJoin implements ValueAtual.MelhoraDesempenho {

    private ValueAtual.MelhoraDesempenho md;
    private String as;

    public void setMd(ValueAtual.MelhoraDesempenho md) {
        this.md = md;
    }

    public void setAs(String as) {
        this.as = as;
    }

    @Override
    public void setCampo(Object campo) {
        md.setCampo(new ModelInternoJoin(as, campo));
    }

    @Override
    public void setValueAt(Object valor, int index) {
        md.setValueAt(new ModelInternoJoin(as, valor), index);
    }

    public ValueAtual.MelhoraDesempenho getMd() {
        return md;
    }

    public String getAs() {
        return as;
    }

     /**
     * Essa class é para guardar o as e o "campo" para garantir que o as não sera perdido.
     */
    public static class ModelInternoJoin {

//        o "as" do value em que o campo pertence.
        private String as;
//        o model pertencente ao outros values atual.
        private Object campo;
        
        
        
        public ModelInternoJoin(String as, Object campo) {
            this.as = as;
            this.campo = campo;
        }

        public String getAs() {
            return as;
        }

        public Object getCampo() {
            return campo;
        }

    }
    
}
