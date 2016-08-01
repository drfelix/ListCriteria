/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria;

import br.com.myhelp.criteria.Criterios.CriteriaComparator;
import java.util.List;

/**
 *
 * @author drfelix
 */
public interface Sort {

    /**
     * Ordena o iterator e o retorna em um filterObjects.
     * @param itera
     * @param criterios
     * @return 
     */
    public IteratorCriterio sort(IteratorCriterio itera, List<CriteriaComparator> criterios);
    
}
