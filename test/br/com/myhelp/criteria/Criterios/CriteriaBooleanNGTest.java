/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria.Criterios;

import br.com.myhelp.criteria.ValueAtual;
import br.com.myhelp.criteria.aux.CriteriaName;
import br.com.myhelp.criteria.imple.MelhoraJoin;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author drfelix
 */
public class CriteriaBooleanNGTest {
    
    public CriteriaBooleanNGTest() {
    }
    
    private CriteriaBoolean booEdit;
    
    @BeforeMethod
    public void iniciaCriteriaForTest(){
        booEdit = new CriteriaBoolean("campo.test") {
            
            @Override
            public boolean myEquals(Object test, ValueAtual atual) throws Exception {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
    
    @Test
    public void testSetCampo(){
        booEdit.setCampo("campo");
        List asExpect = new ArrayList();
        asExpect.add("campo");
        assertEquals(booEdit.getAs(),asExpect);
    }
    
    @Test
    public void testParametros(){
        booEdit.setParametros(CriteriaName.cria("testnome.ola"),new Object(),"ola.campo",CriteriaName.cria("nome"),null);
        List asExpect = new ArrayList();
        asExpect.add("campo.test");
        asExpect.add("testnome.ola");
        asExpect.add("nome");
        assertEquals(booEdit.getAs(),asExpect);
    }
    
    
    @Test
    public void testSetAll(){
        booEdit.setParametros(CriteriaName.cria("testnome.ola"),new Object(),"ola.campo",CriteriaName.cria("nome"),null);
        booEdit.setCampo("campo");
        List asExpect = new ArrayList();
        asExpect.add("campo");
        asExpect.add("testnome.ola");
        asExpect.add("nome");
        assertEquals(booEdit.getAs(),asExpect);
    }
    
    @Test
    public void testSetMelhoraDese(){
        booEdit.setCampo(new MelhoraJoin());
        List asExpect = new ArrayList();
        asExpect.add("campo.test");
        assertEquals(booEdit.getAs(),asExpect);
    }
    
    
    
    @Test
    public void testIniciaCampos() {
        System.out.println("getCampo");
        CriteriaBoolean instance = new CriteriaBoolean("nome",
                CriteriaName.cria("nome.com"),
                "aqule.x") {
            
            @Override
            public boolean myEquals(Object test, ValueAtual atual) throws Exception {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        List resul = new ArrayList();
        resul.add("nome");
        resul.add("nome.com");
        
        assertEquals(instance.getAs(), resul);
    }
    
    @Test
    public void testIniciaCamposCriteria() {
        System.out.println("getCampo");
        CriteriaBoolean instance = new CriteriaBoolean("nome",
                CriteriaName.cria("nome.com"),
                "aqule.x",new CriteriaBoolean("NãoSei","muitoLouco",CriteriaName.cria("nome.com")) {
                    
                    @Override
                    public boolean myEquals(Object test, ValueAtual atual) throws Exception {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                }) {
            
            @Override
            public boolean myEquals(Object test, ValueAtual atual) throws Exception {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        List resul = new ArrayList();
        resul.add("nome");
        resul.add("nome.com");
        resul.add("NãoSei");
        resul.add("nome.com");
        
        assertEquals(instance.getAs(), resul);
    }
    
}
