/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author drfelix
 */
public class testModelo extends TestPai implements Serializable{

   
    public testModelo(testFornecedor fornecedor, String nome, int idadeMenino) {
        super("");
        this.fornecedor = fornecedor;
        this.nome = nome;
        this.idadeMenino = idadeMenino;
    }

    public testModelo(testFornecedor fornecedor, String nome, int idadeMenino, Date dataNacimento, String cpf, boolean sexo, float testDinheiro) {
        super(nome);
        this.fornecedor = fornecedor;
        this.nome = nome;
        this.idadeMenino = idadeMenino;
        this.dataNacimento = dataNacimento;
        this.cpf = cpf;
        this.sexo = sexo;
        this.testDinheiro = testDinheiro;
    }

    public testModelo() {
        super("");
    }

    
    
    private int id;
    
    private testFornecedor fornecedor;
    
    private List<testFornecedor> listFornecedor;
    
    
    private String nome;
    
//    @MyMessage(message = "O Menino não pode ter mais que 18 anos!!!")
//    @MyNumberMaximo(numMax = 18)
//    @MyConfigure(requirid = true)
    private int idadeMenino;
    
    private String tipo;
    
//    @MyConfigure
//    @MyMessage(message = "Não é possivel Cadastrar uma Pessoa Com menos de 18 Anos!!!")
//    @MyDate(tipoDeIncrementacao = MyValEnuTypIncrement.DECREMENTA,tipoDeValide = MyValEnuDate.NOT_DATA_POSTERIOR,quantAnos = 18)
    private Date dataNacimento;
    
//    @MyConfigure
//    private TestPai<String> list;
    
//    @MyCPF
//    @MyMessage(message = "Programador Burro o CPF estava sendo validado de forma errada!!!")
    private String cpf;

    private boolean sexo;
    
    private boolean tezt;

    private float testDinheiro;

    public List<testFornecedor> getListFornecedor() {
        return listFornecedor;
    }

    public void setListFornecedor(List<testFornecedor> listFornecedor) {
        this.listFornecedor = listFornecedor;
    }

    

    public testFornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(testFornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdadeMenino() {
        return idadeMenino;
    }

    public void setIdadeMenino(int idadeMenino) {
        this.idadeMenino = idadeMenino;
    }

    public Date getDataNacimento() {
        return dataNacimento;
    }

    public void setDataNacimento(Date dataNacimento) {
        this.dataNacimento = dataNacimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public float getTestDinheiro() {
        return testDinheiro;
    }

    public void setTestDinheiro(float testDinheiro) {
        this.testDinheiro = testDinheiro;
    }

    public testModelo(testFornecedor fornecedor, String nome) {
        super(nome);
        this.fornecedor = fornecedor;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "testModelo{" + "id=" + id + ", fornecedor=" + fornecedor + ", listFornecedor=" + listFornecedor + ", nome=" + nome + ", idadeMenino=" + idadeMenino + ", tipo=" + tipo + ", dataNacimento=" + dataNacimento + ", cpf=" + cpf + ", sexo=" + sexo + ", tezt=" + tezt + ", testDinheiro=" + testDinheiro + '}';
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
        final testModelo other = (testModelo) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.fornecedor, other.fornecedor)) {
            return false;
        }
        if (!Objects.equals(this.listFornecedor, other.listFornecedor)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (this.idadeMenino != other.idadeMenino) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.dataNacimento, other.dataNacimento)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (this.sexo != other.sexo) {
            return false;
        }
        if (this.tezt != other.tezt) {
            return false;
        }
        if (Float.floatToIntBits(this.testDinheiro) != Float.floatToIntBits(other.testDinheiro)) {
            return false;
        }
        return true;
    }

    
    
}
