/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria.imple;

import br.com.myhelp.criteria.IteratorCriterio;
import br.com.myhelp.criteria.Join;
import br.com.myhelp.criteria.ValueAtual;
import br.com.myhelp.util.Util;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author drfelix
 */
public class InnerJoin implements Join {

    public InnerJoin() {
        melhoraJoin = new MelhoraJoin();
    }

    private String[] asIndex;

    private List<ValueAtual> values;

    private MelhoraJoin melhoraJoin;
    
    /**
     * .
     * Multiplica os models e o retorna em formato FilterObject. 1 | 8 | 4 4 | 5
     * | 6
     *
     * x
     *
     * 2 | 7
     *
     * =
     * 2 | 7 | 1 | 8 | 4
     * 2 | 7 | 4 | 5 | 6
     *
     *
     * --------------------
     *
     * 6 | 9
     *
     * x
     *
     * null
     *
     * =
     * null
     *
     *
     *
     * @param filter1
     * @param filter2
     * @return
     * @throws Exception
     * 
     * Casos de uso:
     * entregua dois iteratorCriterio e fazer a multiplicação deles como descrito em sima;
     * 
     */
    @Override
    public IteratorCriterio join(IteratorCriterio filter1, IteratorCriterio filter2) throws Exception {
        values = new ArrayList<>(filter1.size() * filter2.size());
        asIndex = Util.addAll(filter1.asIndex(), filter2.asIndex());
        ValueAtual valueObj;
        
        if (filter1.size() != 0 && filter2.size() != 0) {
            while (filter1.hashNext()) {
                valueObj = filter1.next();
                while (filter2.hashNext()) {
                    
                    values.add(new JoinValueAtual(
                            valueObj,
                            filter2.next(), melhoraJoin, asIndex));
                }
                filter2.resetCursor();
            }
            filter1.resetCursor();
        }
        return new ListIteratorCriterio(values, asIndex);
    }

}
