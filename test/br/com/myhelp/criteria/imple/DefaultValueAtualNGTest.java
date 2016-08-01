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
import org.testng.annotations.BeforeClass;
import test.testFornecedor;

/**
 *
 * @author drfelix
 * 
 * Os dois testes principais dignos de test foi teestado com uma lista de ~ 25 itens
 * e visto na depuração um acopanhamento linha por linha para saber se estava executando
 * o que foi programado.
 */
public class DefaultValueAtualNGTest {
    
    public DefaultValueAtualNGTest() {
    }

    /**
     * Teste de método getValue, da classe DefaultValueAtual.
     */
    
    Object campot;
    List<testFornecedor> list;

    @Test
    public void testGetCampoSequancialmente() throws Exception {
        System.out.println("getCampo++++++++++++++");

        ValueAtual.MelhoraDesempenho md = new ValueAtual.MelhoraDesempenho() {

            @Override
            public void setCampo(Object campo) {
                campot = campo;
            }

            @Override
            public void setValueAt(Object valor, int index) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        DefaultValueAtual instance ;

        Object expResult = null;
        campot = "this.testBoole";

        for (testFornecedor l1 : list) {
            expResult = l1.getTestBoole();
            
            instance = new DefaultValueAtual("this",0,l1);
            Object result = instance.getCampo(campot, md);
            System.out.println("-------------------\n"+result);
            assertEquals(result, expResult);
        }

    }
    
    
    Object[] obes;
    @Test
    public void testGetValueSequancialmente() throws Exception {
        System.out.println("getValue++++++++++++++++++++");
        Object ob = new testFornecedor("Anderson", "Felix", null, true);

        ValueAtual.MelhoraDesempenho md = new ValueAtual.MelhoraDesempenho() {

            @Override
            public void setCampo(Object campo) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void setValueAt(Object valor, int index) {
                obes[index] = valor;
            }
        };

        DefaultValueAtual instance = new DefaultValueAtual("this", 0, ob);

        Object expResult = null;
        obes = new Object[]{CriteriaName.cria("this.nome")};

        for (testFornecedor l1 : list) {
            expResult = l1.getNome();
            
            instance = new DefaultValueAtual("this",0,l1);
            Object result = instance.getValue(obes[0], md,0);
            System.out.println("-------------------\n"+result);
            assertEquals(result, expResult);
        }

    }
    
    
    @BeforeClass
    private void list(){
        List listModelo = null;
        list = new ArrayList<>();
        list.add(new testFornecedor("zueira", "gonsaga", listModelo, true));
        list.add(new testFornecedor("Cachaseiro", "pinguço", listModelo, true));
        list.add(new testFornecedor("Taguel", "Laviure", listModelo, false));
        list.add(new testFornecedor("Anderson", "Felix", listModelo, true));
        list.add(new testFornecedor("zuado", "gonsaga", listModelo, false));
        list.add(new testFornecedor("Cachorro", "pinguço", listModelo, false));
        list.add(new testFornecedor("zangado", "gonsaga", listModelo, true));
        list.add(new testFornecedor("Cachumbeiro", "pinguço", listModelo, true));
        list.add(new testFornecedor("Toquinho", "Laviure", listModelo, false));
        list.add(new testFornecedor("Andando", "Felix", listModelo, true));
        list.add(new testFornecedor("Zaolho", "gonsaga", listModelo, false));
        list.add(new testFornecedor("CUecão", "pinguço", listModelo, false));
        list.add(new testFornecedor("zueira", "gonsaga", listModelo, true));
        list.add(new testFornecedor("Cigarro", "pinguço", listModelo, true));
        list.add(new testFornecedor("Tagando", "Laviure", listModelo, false));
        list.add(new testFornecedor("Anderson", "Felix", listModelo, true));
        list.add(new testFornecedor("zueira", "gonsaga", listModelo, false));
        list.add(new testFornecedor("Cachaseiro", "pinguço", listModelo, false));
        list.add(new testFornecedor("Rita de Cassia", "gonsaga", listModelo, false));
        list.add(new testFornecedor("Rita cadlaque", "pinguço", listModelo, false));
        list.add(new testFornecedor("Miguel", "gonsaga", listModelo, true));
        list.add(new testFornecedor("Miguel Marques", "pinguço", listModelo, true));
        list.add(new testFornecedor("Anderson Felix da Silva", "Laviure", listModelo, false));
        list.add(new testFornecedor("Toquinho Felix", "Felix", listModelo, true));
        list.add(new testFornecedor("zola", "gonsaga", listModelo, false));
    }
    
    
}
