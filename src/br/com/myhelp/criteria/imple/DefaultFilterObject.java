/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria.imple;

import br.com.myhelp.criteria.FilterObjects;
import br.com.myhelp.criteria.IteratorCriterio;
import br.com.myhelp.criteria.ValueAtual;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * CUIDADO: NÃO TEM SUPORTE PARA "indexOf".//ultrapassado
 *
 * FILTRA uma lista de Object.
 * 
 * #OQueDeveSerTestado:
 * 1-Os Metodos add, addAll, set e utilizado o do arrayList, entao nao devo 
 *   Me preocupar.
 * 2-Devo me preocupar apenas com o metodo iteratorCriterio, pois,
 *   e implementado uma ListaEncapsulada para retornar os objetos.
 * 2.1-Deste deve testar se o retorno do "next()" é igual aos objetos dentro de um 
 *     "DefaultValueAtual".
 *
 * @author drfelix
 */
public class DefaultFilterObject implements FilterObjects {

    protected List busca;
    protected String[] as;//aqui tenho que verificar e tirar no ValueAtual.

    /**
     *
     * @param busca lista que sera filtrada
     * @param as o que representa o filtreObjects // * @param clsPer garanto que
     * vai retorna o "asIndex"
     */
    public DefaultFilterObject(List busca, String as) {
        this.busca = busca;
        this.as = new String[]{as};
    }

    @Override
    public String[] asIndex() {
        return as;
    }

    @Override
    public void add(Object obj) {
        busca.add(obj);
    }

    @Override
    public IteratorCriterio iteratorCriterio() {
        return new ListIteratorCriterio(new ListEncapsulada(busca), as);
    }

    @Override
    public void addAll(List obj) {
        busca.addAll(obj);

    }

    @Override
    public Object set(Object e, int at) {
        busca.set(at, e);
        return e;
    }
    
    private class ListEncapsulada extends ArrayList{

        List l;

        public ListEncapsulada(List l) {
            this.l = l;
        }

        @Override
        public int size() {
            return l.size();
        }

        @Override
        public boolean isEmpty() {
            return l.isEmpty(); 
        }
        
        @Override
        public Object get(int index) {
            return new DefaultValueAtual(as[0], index,l.get(index));
        }

        @Override
        public Iterator iterator() {
            return new IteratorEncapsulado(l.iterator());
        }
        
        
        
    }
    
    private class IteratorEncapsulado implements Iterator{

        public IteratorEncapsulado(Iterator itera) {
            this.itera = itera;
        }
        private Iterator itera;
        private int index =0;
        
        @Override
        public boolean hasNext() {
            return itera.hasNext();
        }

        @Override
        public Object next() {
            ValueAtual valu = new DefaultValueAtual(as[0], index, itera.next());
            index++;
            return valu;
        }
        
    }
    
    
    
}
