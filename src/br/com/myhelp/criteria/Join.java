package br.com.myhelp.criteria;

/**
 * Responsavel por unir o model principal ao model desejado.
 *
 * @author drfelix
 */
public interface Join {
    public IteratorCriterio join(IteratorCriterio filter1, IteratorCriterio filter2) throws Exception;
}
