/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author drfelix
 */
public class testFornecedor extends TestPai implements Serializable {
    
    private String nome;
    private String sobrenome;
    private List<testModelo> listModelo = new ArrayList<>();
    private Boolean testBoole;

    
    /**
     *
     * @param nome
     * @param sobrenome
     * @param listModelo
     * @param testBoole
     */
    public testFornecedor(String nome, String sobrenome,List<testModelo> listModelo, boolean testBoole) {
        super(sobrenome);
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.listModelo = listModelo;
        this.testBoole = testBoole;
    }

    public Boolean getTestBoole() {
        return testBoole;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public testFornecedor(String nome, String sobrenome, Boolean testBoole, String helloWorhd) {
        super(helloWorhd);
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.testBoole = testBoole;
    }

    
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public testFornecedor() {
        super("");
    }


    public List<testModelo> getListModelo() {
        return listModelo;
    }

    public void setListModelo(List<testModelo> listModelo) {
        this.listModelo = listModelo;
    }

    @Override
    public String toString() {
        return getClass().getCanonicalName()+"{" + "nome=" + nome + ", sobrNome=" + sobrenome + '}';
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
        final testFornecedor other = (testFornecedor) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.sobrenome, other.sobrenome)) {
            return false;
        }
        return Objects.equals(this.testBoole, other.testBoole);
    }
    
    
    
}
