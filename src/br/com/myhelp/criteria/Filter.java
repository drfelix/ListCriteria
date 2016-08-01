/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria;

/**
 *
 * @author drfelix
 */
public interface Filter {
    /**
     * Os "asIndex" que representa esse filterObject.
     *
     * apenas o inicio.
     * 
     * representa uma lista de pessoa(as "pe"), uma pessoa tem 
     * nome, telefone.
     * ai os campos vão ser:
     * pe.nome ,
     * pe.telefone
     * 
     * o as é apenas "pe"
     * 
     * mas o caso de ter ocorrido joins vai ter mais de um as.
     * 
     * 
     * @return o asIndex que representa o FilterObjects.
     */
    public String[] asIndex();
}
