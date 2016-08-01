/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria.imple;

import br.com.myhelp.criteria.IteratorCriterio;
import br.com.myhelp.criteria.ValueAtual;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.testFornecedor;

/**.
 * 
 * Em todos os testes foi verificado o depurador de arquivo e foi mantido o esperado
 * 1-apenas leitura: foi verificado os totais iguais.
 * 2-add: verificado que foi add e os totais iguais.
 * 3-remove: verificado que removel e os totais iguais.
 * 
 *
 * @author drfelix
 */
public class DefaultFilterObjectNGTest {

    public DefaultFilterObjectNGTest() {
    }
    
    
    
    
    
    private static DefaultFilterObject instance;
    private List list;
    private List<ValueAtual> values;

    @BeforeClass
    public void setUpMethod() throws Exception {
        instance = new DefaultFilterObject(list(),"this");
    }
    
    private List list(){
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
        return list;
    }

    /**
     * Teste de método asIndex, da classe DefaultFilterObject.
     */
    @Test
    public void testAsIndex() {
        System.out.println("asIndex");
        String[] expResult = new String[]{"this"};
        String[] result = instance.asIndex();
        assertEquals(result, expResult);
    }

    /**
     * Teste de método iteratorCriterio, da classe DefaultFilterObject.
     * 
     * O iteratorCriterio retornado pelo ("DefaultFilterObject" as dfo) quando percorrido
     * estava indicando valor enpty, pois, no "dfo" é usado uma lista encapsulada que
     * implementa os metodos de leitura de um "List" e o metodo getSize nao estava 
     * implementado, assim era tido semple como 0 e não percorria nada.
     * 
     * @throws java.lang.Exception
     */
    @Test
    public void testIteratorCriterioIqualsValuesAtual() throws Exception{
        System.out.println("iteratorCriterio");
        IteratorCriterio result = instance.iteratorCriterio();
        values = new ArrayList<>();
        
        while(result.hashNext()){
            values.add(result.next());
        }
        assertEquals(values.size(),list.size());
    }

    @Test
    public void testIteratorCriterioIqualsValuesAtualDepoisAdd() throws Exception{
        System.out.println("iteratorCriterio");
        
        list.add(new testFornecedor("Yure","Silva",null, true));
        
        IteratorCriterio result = instance.iteratorCriterio();
        values = new ArrayList<>();
        
        while(result.hashNext()){
            values.add(result.next());
        }
        assertEquals(values.size(),list.size());
    }
    
    @Test
    public void testIteratorCriterioIqualsValuesAtualDepoisRemove0() throws Exception{
        System.out.println("iteratorCriterio");
        
        list.remove(0);
        
        IteratorCriterio result = instance.iteratorCriterio();
        values = new ArrayList<>();
        
        while(result.hashNext()){
            values.add(result.next());
        }
        assertEquals(values.size(),list.size());
    }
    
}
