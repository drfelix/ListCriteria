/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.criteria.Criterios;

import br.com.myhelp.criteria.imple.DefaultValueAtual;
import br.com.myhelp.criteria.ValueAtual;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author drfelix
 */
public class Restricions {

    /**
     *
     * @param campo este tem que ser do tipo list ou seus filhos
     * @return
     *
     * Verifica se o campo é empty ou null
     * 
     */
    public static CriteriaBoolean listIsEmpty(Object campo) {
        return new CriteriaBoolean(campo) {

            @Override
            public boolean myEquals(Object test,ValueAtual value) {
                if (test == null) {
                    return true;
                }

                return ((List) test).isEmpty();
            }
        };
    }

    /**
     *
     * @param campo pode ser o caminho de qualquer tipo de objeto.
     * @return true se for null e false se não for null
     */
    public static CriteriaBoolean isNull(Object campo) {
        return new CriteriaBoolean(campo) {

            @Override
            public boolean myEquals(Object test,ValueAtual value) {
                return test == null;
            }
        };
    }

    
    /**
     *
     * @param exe - parametro a ser invertido
     * @return o inverso de exe {true se exe for false} e {false se exe for
     * true}
     * 
     * @@@ Caso o exe seja um
     */
    public static CriteriaBoolean not(CriteriaBoolean exe) {
        return new CriteriaBoolean(exe.getCampo(), exe) {

            @Override
            public boolean myEquals(Object test,ValueAtual value) throws Exception {
                return !((CriteriaBoolean) parametros[0]).myEquals(test,value);
            }
        };
    }

    /**
     * 
     * @param test1 primeiro teste
     * @param test2 outro teste
     * @return true se algum dos dois for true
     **/
    public static CriteriaBoolean or(CriteriaBoolean test1, CriteriaBoolean test2) {
        return new CriteriaBoolean(null, test1, test2) {
            @Override
            public boolean myEquals(Object test,ValueAtual valu) throws Exception {
                //parametros[0] equivale ao criterio 1
                //parametros[1] equivale ao criterio 2
                
//                aqui eu vejo se são os dois instancias de CriteriaBoolean
//                  se sim eu pego e testo seus valores. Retornando true se algum dos dois retornar true 
                                                                        
                if (parametros[0] instanceof CriteriaBoolean && (parametros[1] instanceof CriteriaBoolean)) {
                    CriteriaBoolean criteriaList1 = (CriteriaBoolean) parametros[0];
                    CriteriaBoolean criteriaList2 = (CriteriaBoolean) parametros[1];
                    
                    Object obj1 = valu.getCampo(criteriaList1.getCampo(),this);
                    Object obj2 = valu.getCampo(criteriaList2.getCampo(),this);
                    
                    return criteriaList1.myEquals(obj1,valu) || criteriaList2.myEquals(obj2,valu);
                    
                }
                throw new IllegalArgumentException("Os Parametros passados para o or devem ser CriteriaList");
            }
        };
    }
    
    /**
     *
     * @param test1 primeiro test
     * @param test2 outro test
     * @return sé os dois for true, sera util quando for ter que fazer mais de uma função no projection.
     *
     */
    public static CriteriaBoolean and(CriteriaBoolean test1, CriteriaBoolean test2) {
        return new CriteriaBoolean(null, test1, test2) {
            @Override
            public boolean myEquals(Object test, ValueAtual valu) throws Exception {
                //parametros[0] equivale ao criterio 1
                //parametros[1] equivale ao criterio 2
                
//                aqui eu vejo se são os dois instancias de CriteriaBoolean
//                  se sim eu pego e testo seus valores. Retornando true se os dois retornar true 
                
                
                if (parametros[0] instanceof CriteriaBoolean && (parametros[1] instanceof CriteriaBoolean)) {
                    CriteriaBoolean criteriaList1 = (CriteriaBoolean) parametros[0];
                    CriteriaBoolean criteriaList2 = (CriteriaBoolean) parametros[1];
                    
                    Object obj1 = valu.getCampo(criteriaList1.getCampo(),this);
                    Object obj2 = valu.getCampo(criteriaList2.getCampo(),this);
                    
                    return criteriaList1.myEquals(obj1,valu) && criteriaList2.myEquals(obj2,valu);
                    
                }
                throw new IllegalArgumentException("Os Parametros passados para o or devem ser CriteriaList");
            }
        };
    }
    
    /**
     *
     * @param campo
     * @param value
     * @return true se o valor do campo for igual ao value
     * 
     * @@@ se campo for um numero ou campo extender de Comparable.
     */
    public static CriteriaBoolean equals(Object campo, Object value) {
        return new CriteriaBoolean(campo, value) {

            @Override
            public boolean myEquals(Object test,ValueAtual valu) throws Exception {
                return test
                        .equals(
                                valu.getValue(parametros[0],this,0)
                        );
            }
        };
    }

    /**
     *
     * @param campo
     * @param value uma String ou um CriteriaName
     * @return testa se o final do valor do campo é igual a value
     *
     * 
     * @@@ tem mas vai ser um saco
     */
    public static CriteriaBoolean endsWith(Object campo, Object value) {
        return new CriteriaBoolean(campo, value) {

            @Override
            public boolean myEquals(Object test,ValueAtual valu) throws Exception {
                String end = valu.getValue(parametros[0],this,0) + "";
                return (test + "").endsWith(end);
            }
        };
    }

    /**
     *
     * @param campo
     * @param value uma String ou CriteriaName
     * @return true sé o inicio do valor do campo for igual a value
     * 
     * @@@ tem
     */
    public static CriteriaBoolean likeTo(Object campo, Object value) {
        return new CriteriaBoolean(campo, value) {

            @Override
            public boolean myEquals(Object test,ValueAtual valu) throws Exception {
                String para = valu.getValue(parametros[0],this,0) + "";
                if(para.length()>(test+"").length()){
                    return false;
                }
                String striTest = (test + "").substring(0, para.length());
                return para.equalsIgnoreCase(striTest);
            }
        };
    }
//

    /**
     *
     * @param campo
     * @param value uma Class ou CriteriaName
     * @return true se o valor do campo for uma instancia de value
     *
     */
    public static CriteriaBoolean instanceOf(Object campo, Object value) {
        return new CriteriaBoolean(campo, value) {

            @Override
            public boolean myEquals(Object test,ValueAtual valu) throws Exception {
                Class ob1 = (Class) valu.getValue(parametros[0],this,0);
                return ob1.isInstance(test);
            }
        };
    }
//

    /**
     *
     * @param campo
     * @param value um Number ou CriteriaName
     * @return true se o valor do campo for maior que o value
     */
    public static CriteriaBoolean maiorQue(Object campo,Object value) {
        //tenho duas situações: 1-quando for filtrar vai ser necessario 2 campos.
//                              2- quando for ordenar sera necessario 1 campo
        return new CriteriaBoolean(campo,value) {
            @Override
            public boolean myEquals(Object test,ValueAtual valu) throws Exception {
                Number num = (Number) valu.getValue(parametros[0],this,0);
                return (((Number) test).doubleValue() > num.doubleValue());

            }
        };
    }
//

    /**
     *
     * @param campo
     * @param value Number ou CriteriaName
     * @return true se o valor do campo for menor que value
     */
    public static CriteriaBoolean menorQue(Object campo, Object value) {
        return new CriteriaBoolean(campo, value) {
            @Override
            public boolean myEquals(Object test,ValueAtual valu) throws Exception {
                Number num1 = (Number) valu.getValue(parametros[0],this,0);
                return (((Number) test).doubleValue() < num1.doubleValue());
            }
        };
    }


    /**
     *Cuidado com esse metodo ele usa o object.equals(Object e);
     * 
     * @param campo tem que fazer referencia a uma lista ou um Array
     * @param in
     * @return - true se todos os parametros(in) tiver na lista(campo)
     */
    public static CriteriaBoolean inList(Object campo, Object... in) {
        return new CriteriaBoolean(campo, in) {

            @Override
            public boolean myEquals(Object test,ValueAtual valu) throws Exception {
                if (test == null) {
                    return false;
                }
                
                List onjeList = null;
                if(test instanceof List){
                    onjeList = (List) test;
                }else if(test instanceof Object[]){
                    onjeList = Arrays.asList((Object[])test);
                }else{
                    throw new IllegalArgumentException("O campo "+campo+" Tem que ser um List ou Array");
                }
                
                Object[] para = null;
                
//                pode rreferenciar uma lista.
                if(parametros.length == 1){
                    try {
                        para = (Object[]) valu.getValue(parametros[0], this,0);
                    } catch (Exception e) {
                        para = parametros;
                    }
                }else{
                    para = parametros;
                }
                
                for (Object parame : para) {
                    if (!onjeList.contains(parame)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    
    /**
     *Cuidado com esse metodo ele usa o object.equals(Object e);
     * 
     * @param campo tem que fazer referencia a uma lista ou um Array
     * @param in
     * @return - true se todos da lista(campo) tiver nos parametros(in)
     */
    public static CriteriaBoolean listIn(Object campo, Object... in) {
        return new CriteriaBoolean(campo, in) {

            @Override
            public boolean myEquals(Object test,ValueAtual valu) {
                if (test == null) {
                    return false;
                }
                
                List onjeList = null;
                if(test instanceof List){
                    onjeList = (List) test;
                }else if(test instanceof Object[]){
                    onjeList = Arrays.asList((Object[])test);
                }else{
                    throw new IllegalArgumentException("O campo "+campo+" Tem que ser um List ou Array");
                }
                
                List parametrosLis = Arrays.asList(parametros);
                
                for (Object parame : onjeList) {
                    if (!parametrosLis.contains(parame)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }
    
    
    /**
     *
     * @param campo um objeto
     * @param in
     * @return true se o valor do campo for igual a algum parametro(in)
     */
    public static CriteriaBoolean in(Object campo, Object... in) {
        return new CriteriaBoolean(campo, in) {
            @Override
            public boolean myEquals(Object test,ValueAtual valu) {
                for (Object cam : parametros) {
                    if (cam.equals(test)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    /**Todos os criterios(value) passado aqui referece a lista(campo) passado aqui.
     *
     * @param campo tem que fazer referencia a uma lista ou um Array
     * @param value criterios
     * @return true sé na lista(campo) tiver um ou mais objeto que atenda a
     * todos os criterios(value)
     */
    public static CriteriaBoolean contains(Object campo, CriteriaBoolean... value) {
        return new CriteriaBoolean(campo, (Object[]) value) {

            @Override
            public boolean myEquals(Object test,ValueAtual valu) throws Exception {
                if (test == null) {
                    throw new NullPointerException("O Valor do Campo \"" + campo + "\" esta null");
                }

                    List<ValueAtual> list = null;
                    if (test instanceof List) {
                        list = new ListEncapsulada((Collection) test, "this");
                    } else if (test instanceof Object[]) {
                        list = new ListEncapsulada(Arrays.asList((Object[]) test), "this");
                    } else {
                        throw new IllegalArgumentException("O campo " + campo + " Tem que ser um List ou Array");
                    }

                    boolean testCrite = false;
                    Object thiTest = null;
                    
                    for (ValueAtual dva : list) {
                        for (Object te : parametros) {
                            
                            thiTest = dva.getCampo(((CriteriaBoolean) te).getCampo(),this);
                            
                            if (((CriteriaBoolean) te).myEquals(thiTest,dva)) {
                                testCrite = true;
                            } else {
                                testCrite = false;
                                break;
                            }
                        }
                        if (testCrite) {
                            return true;
                        }
                    }
                    return false;
              
            }
        };
    }

    
    
    /**
     *
     * @param campo tem que fazer referencia a uma lista ou um Array
     * @param value
     * @return true se todos os valores da lista atenderem ao passado.
     */
    public static CriteriaBoolean containsAll(Object campo, CriteriaBoolean... value) {
        return new CriteriaBoolean(campo, (Object[]) value) {

            @Override
            public boolean myEquals(Object test,ValueAtual value) throws Exception {
                if (test == null) {
                    throw new NullPointerException("O Valor do Campo \"" + campo + "\" esta null");
                }
                    List<ValueAtual> list = null;
                    if (test instanceof List) {
                        list = new ListEncapsulada((Collection) test, "this");
                    } else if (test instanceof Object[]) {
                        list = new ListEncapsulada(Arrays.asList((Object[]) test), "this");
                    } else {
                        throw new IllegalArgumentException("O campo " + campo + " Tem que ser um List ou Array");
                    }
                    
                    boolean testCrite = false;
                    Object thiTest = null;
                    
                    for (ValueAtual dva : list) {
                        for (Object te : parametros) {
                            thiTest = dva.getCampo(((CriteriaBoolean) te).getCampo(),this);
                            if (((CriteriaBoolean) te).myEquals(thiTest,dva)) {
                                testCrite = true;
                            } else {
                                testCrite = false;
                                break;
                            }
                        }
                        if (!testCrite) {
                            return false;
                        }
                    }
                    return true;
            }
        };
    }

    
    
    private static class ListEncapsulada extends ArrayList {
        private String as;

        public ListEncapsulada(Collection c,String as) {
            super(c);
            this.as = as;
            
        }

        @Override
        public Object get(int index) {
            Object obje = super.get(index);
            return new DefaultValueAtual(as, index, obje);
        }

    }
}
