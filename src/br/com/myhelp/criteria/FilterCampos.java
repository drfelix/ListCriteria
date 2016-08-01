package br.com.myhelp.criteria;

/**@Faz a filtragem do dado do campo e retorna o modelo dele
 * @NãoUsado o metodo "list" da interface "FilterObjects" pois sera usado o campo
 *
 * 
 * 
 * Funcionamento
     * list Alunos
     *      { nome:"Anderson",
     *        list Materias{
     *                      {nomeMateria:"Matematica"},
     *                      {nomeMateria:"portugues"}
     *        }
     *      }
     *      { nome:"Rita",
     *          list Materias{
     *                        {nomeMateria:"portugues"},
     *                        {nomeMateria:"Historia"}
     *          }
     *      }
     * 
     * Resultado:
     *  list Total {nome:"Anderson",nomeMateria:"Matrmatica"}
     *             {nome:"Anderson",nomeMateria:"portugues"}
     *             {nome:"Rita",nomeMateria:"portugues"}
     *             {nome:"Rita",nomeMateria:"Historia"}
 * 
 * 
 * @author drfelix
 */
public interface FilterCampos extends Filter{
   
    /**Seta o Iterator Principal.
     * 
     * @param iterator
     */
    public void setIteratorCriterio1(IteratorCriterio iterator);
    
    
    /**
     * 
     * @return 
     */
    public IteratorCriterio iterator1();
    
    /**
     * Pega o segundo iterator para cruzar as informaçoes e colocar em uma linha(join).
     * @return 
     */
    public IteratorCriterio iterator2();
    
    
    
//    /**
//     * Criterios do filtro 2
//     * @param cbs 
//     */
//    public void setCriterios2(List<CriteriaBoolean> cbs);
}
