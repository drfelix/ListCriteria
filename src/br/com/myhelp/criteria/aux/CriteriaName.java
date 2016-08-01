/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria.aux;

/**
 *
 * @author drfelix
 */
public class CriteriaName {
    private String campo;
    private Class percence;//pode ser null
    
    /**Cria um "CriteriaName" setando o "campo".
     * @PontoPositivo Não Tem que especificar a class.
     * @PontoNegativo Não Otimiza a busca do campo.
     *
     * @param campo
     * @return
     */
    public static CriteriaName cria(String campo){
        return cria(campo, null);
    }
    
    /**Cria um "CriteriaName" setando "campo" e "percence".
     * @PontoPositivo Otimiza a busca do campo.
     * @PontoNegativo Tem que especificar a class.
     *
     * @param campo
     * @param percence
     * @return
     */
    public static CriteriaName cria(String campo, Class percence){
        return new CriteriaName(campo, percence);
    }

    private CriteriaName(String campo, Class percence) {
        this.campo = campo;
        this.percence = percence;
    }

    /** 
     * @return O campo do campo ao qual deverar ser pego o valor(Obrigatorio)
     */
    public String getNome() {
        return campo;
    }

    /**CUIDADO: Pode ser null
     * @return A class ao qual o campo percence
     */
    public Class getPercence() {
        return percence;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public void setPercence(Class percence) {
        this.percence = percence;
    }
    
    
    
}
