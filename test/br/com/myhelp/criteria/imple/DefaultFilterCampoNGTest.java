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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.testFornecedor;
import test.testModelo;
import static org.testng.Assert.*;

/**
 *
 * @author drfelix
 */
public class DefaultFilterCampoNGTest {
    
    public DefaultFilterCampoNGTest() {
    }

    /**
     * Teste de método setCampo, da classe DefaultFilterCampo.
     */
    
    Object campo1;
    Object campo2;
    @Test
    public void testLerNext() throws Exception {
        System.out.println("setCampo");
        
//        configuração da lista(tanformar a lista em lista de Value Atual
        ArrayList<ValueAtual> value = new ArrayList<>();
        
        for (testFornecedor list1 : list) {
            value.add(new DefaultValueAtual("this",1, list1));
        }
        
        
//        studs para "melhorar o desempenho"
        StudMelhora melhora = new StudMelhora();
        StudMelhora2 melhora2 = new StudMelhora2();
        
        ListIteratorCriterio iteraExpec = new ListIteratorCriterio(value, new String[]{"this"});
        
        DefaultFilterCampo instance = new DefaultFilterCampo("sub", "listModelo");
        
        instance.setIteratorCriterio1(iteraExpec);
        
        IteratorCriterio iter1 = instance.iterator1();
        IteratorCriterio iter2 = instance.iterator2();
        
        campo1 = "this.nome";
        campo2 = "sub.nome";
        
        int i = 0;
        while(iter1.hashNext()){
            ValueAtual forne = iter1.next();
            testFornecedor forn = list.get(i);
            assertEquals(forne.getCampo(campo1, melhora), forn.getNome());
            
            int i1 = 0;
            while(iter2.hashNext()){
                testModelo model = forn.getListModelo().get(i1);
                assertEquals(model.getNome(), iter2.next().getCampo(campo2, melhora2));
                i1++;
            }
            i++;
        }
        
    }
    
    
    @Test
    public void testLerGetAndSize() throws Exception {
        System.out.println("setCampo");
        
//        configuração da lista(tanformar a lista em lista de Value Atual
        ArrayList<ValueAtual> value = new ArrayList<>();
        
        for (testFornecedor list1 : list) {
            value.add(new DefaultValueAtual("this",1, list1));
        }
        
        
//        studs para "melhorar o desempenho"
        StudMelhora melhora = new StudMelhora();
        StudMelhora2 melhora2 = new StudMelhora2();
        
        ListIteratorCriterio iteraExpec = new ListIteratorCriterio(value, new String[]{"this"});
        
        DefaultFilterCampo instance = new DefaultFilterCampo("sub", "listModelo");
        
        instance.setIteratorCriterio1(iteraExpec);
        
        IteratorCriterio iter1 = instance.iterator1();
        IteratorCriterio iter2 = instance.iterator2();
        
        campo1 = "this.nome";
        campo2 = "sub.nome";
        
        int i = 0;
        while(i < iter1.size()){
            
            if(i%2==0){
                iter1.resetCursor();
            }
            
            ValueAtual forne = iter1.get(i);
            testFornecedor forn = list.get(i);
            assertEquals(forne.getCampo(campo1, melhora), forn.getNome());
            
            
            
            int i1 = 0;
            while(i1 < iter2.size()){
                testModelo model = forn.getListModelo().get(i1);
                assertEquals(model.getNome(), iter2.get(i1).getCampo(campo2, melhora2));
                i1++;
            }
            i++;
        }
        
    }
    
    
    
    
    private class StudMelhora implements ValueAtual.MelhoraDesempenho{

        @Override
        public void setCampo(Object campo) {
            campo1 = campo;
        }

        @Override
        public void setValueAt(Object valor, int index) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    private class StudMelhora2 implements ValueAtual.MelhoraDesempenho{

        @Override
        public void setCampo(Object campo) {
            campo2 = campo;
        }

        @Override
        public void setValueAt(Object valor, int index) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    List<testFornecedor> list;
    @BeforeClass
    private void list(){
        List<testModelo> listModelo = new ArrayList<>();
        
        listModelo.add(new testModelo(null,"Anderson"));
        listModelo.add(new testModelo(null,"Felix"));
        listModelo.add(new testModelo(null,"Silva"));
        listModelo.add(new testModelo(null,"Aquele"));
        
        
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
