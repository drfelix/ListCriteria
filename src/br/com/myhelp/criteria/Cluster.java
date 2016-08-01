/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria;

import br.com.myhelp.criteria.Criterios.CriteriaBoolean;
import java.util.List;

/**
 *
 * @author drfelix
 */
public interface Cluster {
    /**Seta os criterios de agrupamento.
     * 
     * @param groups 
     */
    public void setCriterios(List<CriteriaBoolean> groups);
    
    /**Agrupa os ValueAtual do "iterator"
     * 
     * @param iterator
     * @return 
     */
    public FilterObjects groupBy(IteratorCriterio iterator);
}
