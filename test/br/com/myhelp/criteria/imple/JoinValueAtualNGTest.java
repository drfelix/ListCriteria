/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria.imple;

import br.com.myhelp.criteria.ValueAtual;
import br.com.myhelp.criteria.aux.CriteriaName;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import static org.mockito.Mockito.*;
import org.testng.annotations.BeforeClass;
import test.testFornecedor;

/**
 *
 * @author drfelix
 */
public class JoinValueAtualNGTest {
    
    public JoinValueAtualNGTest() {
        melhoraJoin = new MelhoraJoin();
    }
    
    MelhoraJoin melhoraJoin;

    /**
     * Teste de método getValue, da classe JoinValueAtual.
     */
    @Test
    public void testFindValue() throws Exception {
        System.out.println("getValue");
        String campo = "this.nome";
        
        ValueAtual val1 = mock(ValueAtual.class);
        ValueAtual val2 = mock(ValueAtual.class);
        
        when(val1.as()).thenReturn(new String[]{"this","ola"});
        when(val2.as()).thenReturn(new String[]{"p","aq"});
        
        JoinValueAtual instance = new JoinValueAtual(val1,val2, melhoraJoin, new String[]{"this","ola","p","aq"});
        
        ValueAtual expResult = instance.findValue(campo);
        assertEquals(val1, expResult);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindValueNotFould() throws Exception {
        System.out.println("getValue");
        String campo = "thi.nome";
        
        ValueAtual val1 = mock(ValueAtual.class);
        ValueAtual val2 = mock(ValueAtual.class);
        
        when(val1.as()).thenReturn(new String[]{"this","ola"});
        when(val2.as()).thenReturn(new String[]{"p","aq"});
        
        JoinValueAtual instance = new JoinValueAtual(val1,val2, melhoraJoin, new String[]{"this","ola","p","aq"});
        
        ValueAtual expResult = instance.findValue(campo);
        assertEquals(val1, expResult);
    }
    
    @Test
    public void testGetThisSimples(){
        System.out.println("getThis");
        ValueAtual val1 = mock(ValueAtual.class);
        ValueAtual val2 = mock(ValueAtual.class);
        
        when(val1.getThis()).thenReturn("Ola");
        when(val2.getThis()).thenReturn("Aquele");
                                                                           //as não usado no getThis
        JoinValueAtual value = new JoinValueAtual(val1, val2, melhoraJoin, new String[]{});
        
        Object expec = new ValueAtual.IsNotArray(new Object[]{"Aquele","Ola"});
        
        Object result = value.getThis();
        
        assertEquals(expec,result);
    }
    
    
    @Test
    public void testGetThisIsNotArray(){
        System.out.println("getThis");
        ValueAtual val1 = mock(ValueAtual.class);
        ValueAtual val2 = mock(ValueAtual.class);
        
        when(val1.getThis()).thenReturn(new ValueAtual.IsNotArray(new Object[]{"object",10}));
        when(val2.getThis()).thenReturn("Aquele");
                                                                           //as não usado no getThis
        JoinValueAtual value = new JoinValueAtual(val1, val2, melhoraJoin, new String[]{});
        
        Object expec = new ValueAtual.IsNotArray(new Object[]{"Aquele","object",10});
        
        Object result = value.getThis();
        
        assertEquals(expec,result);
    }
    
    @Test
    public void testGetThisAllIsNotArray(){
        System.out.println("getThis");
        ValueAtual val1 = mock(ValueAtual.class);
        ValueAtual val2 = mock(ValueAtual.class);
        
        when(val1.getThis()).thenReturn(new ValueAtual.IsNotArray(new Object[]{"object",10}));
        when(val2.getThis()).thenReturn(new ValueAtual.IsNotArray(new Object[]{90.10,"Anderson",12}));
                                                                           //as não usado no getThis
        JoinValueAtual value = new JoinValueAtual(val1, val2, melhoraJoin, new String[]{});
        
        Object expec = new ValueAtual.IsNotArray(new Object[]{90.10,"Anderson",12,"object",10});
        
        Object result = value.getThis();
        
        assertEquals(expec,result);
    }
    
    Object campoPego1;
    Object campoPego2;
    @Test
    public void testGetCampoMock() throws Exception{
        ValueAtual val1 = mock(ValueAtual.class);
        ValueAtual val2 = mock(ValueAtual.class);
        
        campoPego1 = "this.nome";
        campoPego2 = "qw.sobrenome";
        
        when(val1.getCampo(campoPego1, melhoraJoin)).thenReturn("Anderson");
        when(val1.getCampo(campoPego2, melhoraJoin)).thenReturn("Anderson Felix");
        
        when(val1.as()).thenReturn(new String[]{"this","qs"});
        when(val2.as()).thenReturn(new String[]{"qw","pl"});
        
        JoinValueAtual instanci = new JoinValueAtual(val1, val2, melhoraJoin, new String[]{"this","qs","qw","pl"});
        
        assertEquals(instanci.getCampo(campoPego1, new StudMelhora()), "Anderson");
    }
    
    /**
     * Foi realizado o test que testa o MelhoraJoin e ocorreuu tudo certo.
     * foi utilizado uma lista que foi carregando a instancia e testando.
     * e ocorreu tudo como esperado. incrusive na depuração do codigo.
     * @throws Exception 
     */
    @Test
    public void testGetCampoAndValueSequencialmente() throws Exception{
        campoPego1 = "this.nome";
        campoPego2 = CriteriaName.cria("qw.sobrenome");
        JoinValueAtual instance;
        StudMelhora stu = new StudMelhora();
        Object result1;
        Object result2;
        for(int i = 0; i < list.size();i++){
            testFornecedor[] aux = list.get(i);
            instance = new JoinValueAtual(
                    new DefaultValueAtual("this", i,aux[0]),
                    new DefaultValueAtual("qw", i,aux[1]),
                    melhoraJoin, 
                    new String[]{"this","qw"});
            result1 = instance.getCampo(campoPego1, stu);
            result2 = instance.getValue(campoPego2, stu, 0);
            
            assertEquals(result1,aux[0].getNome());
            assertEquals(result2,aux[1].getSobrenome());
        }
        
        campoPego1 = "qw.testBoole";
        
        campoPego2 = CriteriaName.cria("this.nome");
        
        for(int i = 0; i < list.size();i++){
            testFornecedor[] aux = list.get(i);
            instance = new JoinValueAtual(
                    new DefaultValueAtual("this", i,aux[0]),
                    new DefaultValueAtual("qw", i,aux[1]),
                    melhoraJoin, 
                    new String[]{"this","qw"});
            result1 = instance.getCampo(campoPego1, stu);
            result2 = instance.getValue(campoPego2, stu, 0);
            
            assertEquals(result1,aux[1].getTestBoole());
            assertEquals(result2,aux[0].getNome());
        }
    }
    
    
    List<testFornecedor[]> list;
    
    @BeforeClass
    private void list(){
        List listModelo = null;
        list = new ArrayList<>();
        list.add(
                new testFornecedor[]{new testFornecedor("zueira", "gonsaga", listModelo, true),
                    new testFornecedor("Cachaseiro", "pinguço", listModelo, true)
        });
        list.add(new testFornecedor[]{new testFornecedor("Taguel", "Laviure", listModelo, false),
        new testFornecedor("Anderson", "Felix", listModelo, true)});
        list.add(new testFornecedor[]{new testFornecedor("zuado", "gonsaga", listModelo, false),
        new testFornecedor("Cachorro", "pinguço", listModelo, false)});
        list.add(new testFornecedor[]{new testFornecedor("zangado", "gonsaga", listModelo, true),
        new testFornecedor("Cachumbeiro", "pinguço", listModelo, true)});
        list.add(new testFornecedor[]{new testFornecedor("Toquinho", "Laviure", listModelo, false),
        new testFornecedor("Andando", "Felix", listModelo, true)});
        list.add(new testFornecedor[]{new testFornecedor("Zaolho", "gonsaga", listModelo, false),
        new testFornecedor("CUecão", "pinguço", listModelo, false)});
        list.add(new testFornecedor[]{new testFornecedor("zueira", "gonsaga", listModelo, true),
        new testFornecedor("Cigarro", "pinguço", listModelo, true)});
        list.add(new testFornecedor[]{new testFornecedor("Tagando", "Laviure", listModelo, false),
        new testFornecedor("Anderson", "Felix", listModelo, true)});
        list.add(new testFornecedor[]{new testFornecedor("zueira", "gonsaga", listModelo, false),
        new testFornecedor("Cachaseiro", "pinguço", listModelo, false)});
        list.add(new testFornecedor[]{new testFornecedor("Rita de Cassia", "gonsaga", listModelo, false),
        new testFornecedor("Rita cadlaque", "pinguço", listModelo, false)});
        list.add(new testFornecedor[]{new testFornecedor("Miguel", "gonsaga", listModelo, true),
        new testFornecedor("Miguel Marques", "pinguço", listModelo, true)});
        list.add(new testFornecedor[]{new testFornecedor("Anderson Felix da Silva", "Laviure", listModelo, false),
        new testFornecedor("Toquinho Felix", "Felix", listModelo, true)});
    }
    
    private class StudMelhora implements ValueAtual.MelhoraDesempenho{

        @Override
        public void setCampo(Object campo) {
            campoPego1 = campo;
        }

        @Override
        public void setValueAt(Object valor, int index) {
            campoPego2 = valor;
        }
        
    }
}
