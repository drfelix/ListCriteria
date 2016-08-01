/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria.imple;

import br.com.myhelp.criteria.Criterios.CriteriaBoolean;
import br.com.myhelp.criteria.IteratorCriterio;
import br.com.myhelp.criteria.ValueAtual;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 *
 * @author drfelix
 */
public class ListIteratorCriterio implements IteratorCriterio{
    private List<CriteriaBoolean> restricoes;
    private int cursor;
    private List<ValueAtual> busca;
    private List<ValueAtual> listaFiltrada;
    
    private String[] as;

    public ListIteratorCriterio(List<ValueAtual> busca, String[] as) {
        this.busca = busca;
        this.as = as;
        this.cursor = 0;
        setCriterios(null);
    }
    
    
    
    @Override
    public void setCriterios(List<CriteriaBoolean> crits) throws IllegalArgumentException {
        try {
            cursor =0 ;
            this.restricoes = crits;
//            daqui pra baixo é o filtro.
            
            if (crits != null) {
                listaFiltrada = new ArrayList<>();
                Iterator<ValueAtual> aux = busca.iterator();
                while (aux.hasNext()) {
                    ValueAtual va = aux.next();
                    if (myEquals(va)) {
                        listaFiltrada.add(va);
                    }
                }
            }else{
                listaFiltrada = busca;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hashNext() throws Exception {
        return cursor != listaFiltrada.size();
    }

    private boolean myEquals(ValueAtual e) throws Exception {
        Object test = null;
        for (CriteriaBoolean criteModelo : restricoes) {
            if (criteModelo.getCampo() != null) {
                test = e.getCampo(criteModelo.getCampo(), criteModelo);
            } else {
                test = null;
            }
            if (!criteModelo.myEquals(test, e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ValueAtual next() throws Exception {
        int i = cursor;
        if (i >= listaFiltrada.size()) {
            throw new NoSuchElementException();
        }
        cursor = i + 1;
        return listaFiltrada.get(i);
    }

    @Override
    public String[] asIndex() {
        return as;
    }

    @Override
    public int size() {
        return listaFiltrada.size();
    }

    @Override
    public ValueAtual get(int eIndex) {
        return listaFiltrada.get(eIndex);
    }

    @Override
    public void resetCursor() {
       cursor = 0; 
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final ListIteratorCriterio other = (ListIteratorCriterio) obj;
        if (!Objects.equals(this.busca, other.busca)) {
            return false;
        }
        if (!Arrays.deepEquals(this.as, other.as)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
