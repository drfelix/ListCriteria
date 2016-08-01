/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria.aux;

import br.com.myhelp.criteria.GetParametros;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author drfelix
 */
public class CriteriaNameFactory {
    private Class pertence;

    private CriteriaNameFactory(Class pertence) {
        this.pertence = pertence;
    }
    
    public static CriteriaNameFactory begin(Class pertence){
        return new CriteriaNameFactory(pertence);
    }
    
    public CriteriaName cria(String nome){
        return CriteriaName.cria(nome, pertence);
    }
    /**
     * Pega o nome do campo passado no CriteriaBoolean.
     * 
     * @param objs
     * @return 
     */
    public static String trasformToAsCampo(Object objs){
        if(objs == null){
            throw new NullPointerException("objs não pode ser null.");
        }
        String auxRet="";
        if(objs instanceof String){
            return objs+"";
        }else if(objs instanceof CriteriaName){
            return ((CriteriaName)objs).getNome();
        }
        throw new IllegalArgumentException(objs.getClass()+" Não aceito para nome de campo.");
    }
    
    /**Pega o nome dos campos passado como parametros, caso o tenha.
     * 
     * @param objs
     * @return 
     */
    public static List<String> trasformToAsParametros(Object[] objs){
        List<String> auxRet = new ArrayList();
        for (Object obj : objs) {
            if(obj instanceof CriteriaName){
                auxRet.add(((CriteriaName)obj).getNome());
            }else if(obj instanceof GetParametros){
                auxRet.addAll(((GetParametros)obj).getAs());
            }
        }
        return auxRet;
    }
}
