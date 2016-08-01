/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria;

import br.com.myhelp.criteria.aux.Projections;
import java.util.List;

/**
 *  Responsavel por transformar o resultado da saida.
 * @author drfelix
 */
public interface ResultTransform {
    /**
     * seta os itens que tem que pegar.
     * @param itens 
     */
    public void setProjectionItems(List<Projections.ProjectionItem> itens);
    /**
     * Transforma o filter em uma lista que deseja.
     * @param filter
     * @return 
     * @throws java.lang.Exception 
     */
    public List resultTransform(IteratorCriterio filter)throws Exception;
}
