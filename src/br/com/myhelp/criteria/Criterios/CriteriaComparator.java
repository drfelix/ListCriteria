/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria.Criterios;

import br.com.myhelp.criteria.ValueAtual;

/**
 *
 * @author drfelix
 */
public abstract class CriteriaComparator extends CriteriaBoolean{

    public CriteriaComparator(Object campo, Object... parametros) {
        super(campo, parametros);
    }
    
    
    public abstract int compare(ValueAtual v1, ValueAtual v2);
    
}
