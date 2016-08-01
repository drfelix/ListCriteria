/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria.aux;

/**
 *
 * @author drfelix
 */
public final class GroupBy {

    private GroupBy() {
    }
    
    public GroupBy ordene(){
        return new GroupBy();
    }
    
    public abstract static class Funcao extends Projections.ProjectionItem{

        public Funcao(Object campo) {
            super(campo);
        }
        
        public abstract Object clear();
        
        public abstract void execute();
        
    }
    
}
