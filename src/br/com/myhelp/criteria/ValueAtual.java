package br.com.myhelp.criteria;

import java.util.Arrays;

/**
 *
 * @author drfelix
 */
public interface ValueAtual {

    /**Sera usado para pegar valores secundarios, ou seja, que não sera passado
     * altomaticamente. LEMBRANDO-> SÓ NO CASO DO SEGUNDO/S CAMPOS. O primeiro é
     * normal.
     *
     * um exemplo Restricions.equals(primeiro,segundo)
     *
     * este fica responsavel por pegar o "segundo", ou seja um valor comun ou um
     * CriteriaName
     *
     * @param ob
     * @param md
     * @param index
     * @return O valor a ser testado. Se valor for instancia de CriteriaName
     * retorno o valor do campo do objeto atual. se Não retorno o valor passado.
     * @throws java.lang.Exception
     */
    public Object getValue(Object ob,MelhoraDesempenho md,int index) throws Exception;

    /**
     * Diferente do primeiro esse vai ser usado pro primeiro campo. um exemplo
     * Restricions.equals(primeiro,segundo)
     *
     * esse fica responsavel por pegar o "primeiro". ou seja o nome de campo(String), ou
     * um CriteriaName que é um campo especial(da class super)
     *
     *
     *
     * @param campo representa o as do campo
     * @param md
     * @return Se String retorna o valor do campo com a class principal. 
     * Se CriteriaName retorna o valor do campo com a class modificada.
     * 
//     * CUIDADO: Quando passado this é para passar os objetos na estrutura que eles estão.
//     * Independente se estiver na forma de TAD, tipo primitivo, ou array.
     * 
     *
     * @throws Exception
     */
    public Object getCampo(Object campo,MelhoraDesempenho md) throws Exception;

    /**Usado para indicar o index do Value Atual
     *
     * @param as "que represente um valueAtual" null ou "this" quando for o principal(primeiro) ou o nome dado ao filefilter
     * @return o index do as
     */
    public int getIndex(String as) throws IllegalArgumentException;
    
    
    /**
     * 
     * @return o objeto que esta nesse valueAtual.
     */
    public Object getThis();
    
    /**
     * 
     * @return os as que representa esse valueAtual.
     */
    public String[] as();
    
    
//    /**
//     * 
//     * @return os as dos vaues atual.
//     */
//    public String[] as();
    
    /**
     * Sera usado para melhorar o desempenho.
     * Quando for definir onde pegar o campo eu guardo esse valor, pois ai não sera preciso
     * buscar novamente.
     */
    public static interface MelhoraDesempenho{
        /**
         * O campo principal.
         * @param campo 
         */
        public void setCampo(Object campo);
        /**
         * Quando tiver um array de parametros, sera usado esse.
         * #pode não ter esse metodo.
         * @param valor 
         * @param index 
         */
        public void setValueAt(Object valor,int index);
    }
    /**
     * Sera usado para resolver o problema de dois tipos de array
     * diferentes nos Joins.
     * 1º- é um array parametro.
     * 2º- é um array que deve ser juntado ao outros, que essa class vai representar.
     */
    public static class IsNotArray{
        private Object[] array;

        public IsNotArray(Object[] array) {
            this.array = array;
        }
        
        public Object[] getValues(){
            return array;
        }

        @Override
        public int hashCode() {
            int hash = 7;
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
            final IsNotArray other = (IsNotArray) obj;
            return Arrays.deepEquals(this.array, other.array);
        }
        
        
    }
    
}
