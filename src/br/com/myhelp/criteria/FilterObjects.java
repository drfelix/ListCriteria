package br.com.myhelp.criteria;

import java.util.List;

/**Todas as formas de add na lista vai estar nessa class.
 * 
 *
 * @author drfelix
 * @param <E>
 */
public interface FilterObjects<E> extends Filter{
    
    
    
    /**
     * Add novo objeto na estrutura.
     * @param obj 
     */
    public void add(E obj);
    
    /**
     * add mais de um elemento.
     * @param obj 
     */
    public void addAll(List<E> obj);
    
    /**
     * Usado para colocar um novo objeto no lugar de outro
     * @param e novo elemento
     * @param at onde colocar
     * @return elemento velho
     */
    public E set(E e ,int at );
    
    
    
    
    
    /**
     * Usado para percorrer os objetos da lista.
     * #Este iterator deve representar a estrutura ja filtrada. 
     *  Para isso deve-se setar os criterios antes de chamar esse iterator.
     * #O metodo setCriterios deve ser setado apenas no ListCriteria.
     * @return 
     */
    public IteratorCriterio iteratorCriterio();
    
    
    
}
