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
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import static org.mockito.Mockito.*;
import org.testng.annotations.BeforeClass;
import test.testFornecedor;
import test.testModelo;

/**
 *
 * @author drfelix
 */
public class InnerJoinNGTest {
    
    public InnerJoinNGTest() {
    }

    /**
     * Teste de método join, da classe InnerJoin.
     * 
     * impossivel(vai dar muito trabalho) testar mesmo criando mocks pois não é possivel resetar a contagem
     * 
     */
    @Test
    public void testJoin() throws Exception {
        System.out.println("join");
        IteratorCriterio filter1 = mock(IteratorCriterio.class);
        IteratorCriterio filter2 = mock(IteratorCriterio.class);
        
        when(filter1.hashNext()).thenReturn(true,true,true,true,true,true,true,true,false);
        when(filter2.hashNext()).thenReturn(true,true,true,true,false);
        
        when(filter1.size()).thenReturn(8);
        when(filter2.size()).thenReturn(4);
        
        when(filter1.asIndex()).thenReturn(new String[]{"this"});
        when(filter2.asIndex()).thenReturn(new String[]{"as2"});
        
        List<ValueAtual> valuesFiltro1 = createListValues(list1);
        
        when(filter1.next()).thenReturn(valuesFiltro1.get(0),
                valuesFiltro1.get(1),
                valuesFiltro1.get(2),
                valuesFiltro1.get(3),
                valuesFiltro1.get(4),
                valuesFiltro1.get(5),
                valuesFiltro1.get(6),
                valuesFiltro1.get(7));
        
        List<ValueAtual>  valuesFiltro2 = createListValues(list2);
        
        when(filter2.next()).thenReturn(valuesFiltro2.get(0),
                valuesFiltro2.get(1),
                valuesFiltro2.get(2),
                valuesFiltro2.get(3));
        
        InnerJoin instance = new InnerJoin();
        
        IteratorCriterio iteResult = instance.join(filter1, filter2);
        
        assertEquals(32, iteResult.size());
        
    }
    
    
    private List<ValueAtual> createListValues(List list){
        List<ValueAtual> valuesRet = new ArrayList<>();
        int i = 0;
        for (Object obj : list) {
            ValueAtual valu = new DefaultValueAtual("this", i, obj);
            valuesRet.add(valu);
            i++;
        }
        return valuesRet;
    }
    
    List list1;
    List list2;
    @BeforeClass
    private void list(){
        List<testModelo> listModelo = null;
        list1 = new ArrayList<>();
        list1.add(new testFornecedor("zueira", "gonsaga", listModelo, true));
        list1.add(new testFornecedor("Cachaseiro", "pinguço", listModelo, true));
        list1.add(new testFornecedor("Taguel", "Laviure", listModelo, false));
        list1.add(new testFornecedor("Anderson", "Felix", listModelo, true));
        list1.add(new testFornecedor("zuado", "gonsaga", listModelo, false));
        list1.add(new testFornecedor("Cachorro", "pinguço", listModelo, false));
        list1.add(new testFornecedor("zangado", "gonsaga", listModelo, true));
        list1.add(new testFornecedor("Cachumbeiro", "pinguço", listModelo, true));
        
        
        
        list2 = new ArrayList();
        
        list2.add(new testModelo(null,"Anderson"));
        list2.add(new testModelo(null,"Felix"));
        list2.add(new testModelo(null,"Silva"));
        list2.add(new testModelo(null,"Aquele"));
        
        
    }
    
}
