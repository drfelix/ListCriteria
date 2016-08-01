/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria.aux;

import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import test.testFornecedor;

/**
 *
 * @author drfelix
 */
public class CriteriaNameFactoryNGTest {
    
    public CriteriaNameFactoryNGTest() {
    }

    


    /**
     * Teste de método trasformToAsCampo, da classe CriteriaNameFactory.
     */
    @Test
    public void testTrasformToAsCampoString() {
        Object objs = "fh.nome.and";
        String expResult = "fh.nome.and";
        String result = CriteriaNameFactory.trasformToAsCampo(objs);
        assertEquals(result, expResult);
    }
    
    
    @Test(expectedExceptions = NullPointerException.class)
    public void testTrasformToAsCampoNull() {
        Object objs = null;
        assertEquals(CriteriaNameFactory.trasformToAsCampo(objs),"");
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testTrasformToAsCampoOuterObject() {
        Object objs = new testFornecedor();
        String expResult = "fh.nome.and";
        String result = CriteriaNameFactory.trasformToAsCampo(objs);
        assertEquals(result, expResult);
    }

    /**
     * Teste de método trasformToAsParametros, da classe CriteriaNameFactory.
     */
    @Test
    public void testTrasformToAsParametros() {
        Object[] objs = new Object[]{"Adf.grt",CriteriaName.cria("oi.campo")};
        List expResult = new ArrayList();
        expResult.add("oi.campo");
        List result = CriteriaNameFactory.trasformToAsParametros(objs);
        assertEquals(result, expResult);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void testTrasformToAsParametrosNull() {
        Object[] objs = null;
//        throw NullPOintException
        List result = CriteriaNameFactory.trasformToAsParametros(objs);
    }
    
}
