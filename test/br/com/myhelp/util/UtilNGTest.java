/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.util;

import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import test.TestPai;
import test.testFornecedor;
import test.testModelo;

/**
 *
 * @author drfelix
 */
public class UtilNGTest {
    
    public UtilNGTest() {
    }
    /**
     * Teste de método likeToThis, da classe Util.
     */
    @Test
    public void assertLikeToThis() {
        String a = "this.nome";
        String b = "this";
        System.out.println("assertLikeToThis");
        System.out.println("a: "+a+"\nb: "+b);
        assertEquals(Util.likeToThis(a, b), true);
    }
    
    @Test
    public void assertLikeToThisPonto(){
        String a = "idade";
        String b = "id";
        assertEquals(Util.likeToThis(a, b), false);
    }
    
    @Test
    public void assertLikeToThisEquals(){
        String a = "id";
        String b = "id";
        assertEquals(Util.likeToThis(a, b), true);
    }
    
    @Test
    public void failLikeToThisEquals(){
        String a = "l..a";
        String b = "l";
        assertEquals(Util.likeToThis(a, b), false);
    }
    
    @Test
    public void failLikeToThisNoEqualsString(){
        String a = "ith.jgf";
        String b = "asd";
        assertEquals(Util.likeToThis(a, b), false);
    }
    

    /**
     * Teste de método myCreateFile, da classe Util.
     * Não sera testado chamada externa.
     */
//    @Test
//    public void testMyCreateFile() throws Exception {
//        System.out.println("myCreateFile");
//        File file = null;
//        File expResult = null;
//        File result = Util.myCreateFile(file);
//        assertEquals(result, expResult);
//        // TODO verifica o código de teste gerado e remove a chamada default para falha.
//        fail("O caso de teste \u00e9 um prot\u00f3tipo.");
//    }

    /**
     * Teste de método addAll, da classe Util.
     */
    @Test
    public void assertAddAllObject() {
        System.out.println("addAll");
        Object[] adicionado = new Object[]{0,1,4};
        Object[] to = new Object[]{9,8,2,4,6};
        
        Object[] expResult = new Object[]{9,8,2,4,6,0,1,4};
        
        Object[] result = Util.addAll(adicionado, to);
        
        assertEquals(result, expResult);
    }
    
    @Test
    public void assertAddAllObjectVazil() {
        System.out.println("addAll");
        Object[] adicionado = new Object[]{0,1,4};
        Object[] to = new Object[]{};
        
        Object[] expResult = new Object[]{0,1,4};
        
        Object[] result = Util.addAll(adicionado, to);
        
        assertEquals(result, expResult);
    }

    /**
     * Teste de método extractCampo, da classe Util.
     */
    @Test
    public void assertExtractCampo() throws Exception {
        System.out.println("extractCampo");
        String campo = "tm.id";
        Class c = testModelo.class;
        String as = "tm";
        List expResult = new ArrayList();
        expResult.add(testModelo.class.getDeclaredField("id"));
        List result = Util.extractCampo(campo, c, as);
        assertEquals(result, expResult);
    }
    
    @Test
    public void assertExtractCampoOneToOne() throws Exception {
        String campo = "tm.fornecedor.nome";
        Class c1 = testModelo.class;
        String as = "tm";
        List expResult = new ArrayList();
        expResult.add(testModelo.class.getDeclaredField("fornecedor"));
        expResult.add(testFornecedor.class.getDeclaredField("nome"));
        List result = Util.extractCampo(campo, c1, as);
        assertEquals(result, expResult);
    }
    
    @Test
    public void assertExtractCampoOneToOneAndClassPai() throws Exception {
        String campo = "tm.fornecedor.helloWorhd";
        Class c1 = testModelo.class;
        String as = "tm";
        List expResult = new ArrayList();
        expResult.add(testModelo.class.getDeclaredField("fornecedor"));
        expResult.add(TestPai.class.getDeclaredField("helloWorhd"));
        List result = Util.extractCampo(campo, c1, as);
        assertEquals(result, expResult);
    }
    
    @Test
    public void failExtractCampo() throws Exception {
        System.out.println("extractCampo");
        String campo = "tm.id";
        Class c = testModelo.class;
        String as = "t";
        List expResult = new ArrayList();
        expResult.add(testModelo.class.getDeclaredField("id"));
        List result = Util.extractCampo(campo, c, as);
        assertEquals(result, expResult);
    }

//    /**
//     * Teste de método pegeValue, da classe Util.
//     */
//    @Test
//    public void testPegeValue() throws Exception {
//        System.out.println("pegeValue");
//        List<Field> cam = null;
//        Object o = null;
//        Object expResult = null;
//        Object result = Util.pegeValue(cam, o);
//        assertEquals(result, expResult);
//        // TODO verifica o código de teste gerado e remove a chamada default para falha.
//        fail("O caso de teste \u00e9 um prot\u00f3tipo.");
//    }
//    
}
