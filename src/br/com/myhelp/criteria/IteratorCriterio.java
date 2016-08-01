/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria;

import br.com.myhelp.criteria.Criterios.CriteriaBoolean;
import java.util.List;

/**
 * Usado para percorrer a estrutura.
 * #Todas as formas de pegar o objeto vai estar nessa class.
 * #Os dados deve vir para ca ja filtrado.
 * 
 * @author drfelix
 */
public interface IteratorCriterio {
    
    
    /**
     * Usado para Carregar/recarregar a lista de acordo com os criterios passados.
     * @param crits lista de criterios.
     * #Caso for null: Representa que vai percorrer todos
     * 
     * @throws IllegalArgumentException 
     */
    public void setCriterios(List<CriteriaBoolean> crits)throws IllegalArgumentException;
    
    //percorrer sequencialmente
    
    /**
     * Pula um para frente de acordo  com os criterios passados.
     * @return true- tiver um objeto setado
     *         false- Não tiver mais objetos na lista e for setado null.
     * @throws Exception 
     */
    public boolean hashNext()throws Exception;
    
    /**
     * Pega o valueAtual setado pelo hashNext
     * @return 
     * @throws Exception 
     */
    public ValueAtual next() throws Exception;
    
    public void resetCursor()throws Exception;
    
    //fim
    
    
    //percorrer aleatoriamente;
    
    /**
     * #Levando em consideração o Criterio
     * @return a quantidade de itens tem na estrutura.
     */
    public int size();
    
    /**
     * int[] aux = int[]{10,3,67,12,76,12,3};
     * 10 < aux[i] < 70
     * 
     * //dividir para conquistar.
     * 
     * 
     * 
     * Pega o valueatual na posição eIndex.
     * #Levando em consideração o Criterio
     * @param eIndex
     * @return 
     * @throws java.lang.Exception 
     */
    public ValueAtual get(int eIndex)throws Exception;
    
    //fim
    
    
    /**
     * 
     * @return os asIndex que representa esse IteratorCriterio.
     * @throws java.lang.Exception
     */
    public String[] asIndex()throws Exception;
}
