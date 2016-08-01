/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria.aux;

import br.com.myhelp.criteria.Criterios.CriteriaComparator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author drfelix
 */
public final class OrderBy {
    private List<CriteriaComparator> ordenamentos;

    private OrderBy() {
        ordenamentos = new ArrayList<>();
    }
    
    public static OrderBy ordene(){
        return new OrderBy();
    }
    
    public OrderBy addCriterio(CriteriaComparator crite){
        ordenamentos.add(crite);
        return this;
    }

    public List<CriteriaComparator> getOrdenamentos() {
        return ordenamentos;
    }
    
    
}
