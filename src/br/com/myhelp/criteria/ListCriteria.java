package br.com.myhelp.criteria;

import br.com.myhelp.criteria.imple.InnerJoin;
import br.com.myhelp.criteria.imple.DefaultFilterObject;
import br.com.myhelp.criteria.aux.Projections;
import br.com.myhelp.criteria.Criterios.CriteriaBoolean;
import br.com.myhelp.criteria.Criterios.CriteriaComparator;
import br.com.myhelp.criteria.aux.GroupBy;
import br.com.myhelp.criteria.aux.OrderBy;
import br.com.myhelp.criteria.imple.DefaultFilterCampo;
import br.com.myhelp.criteria.imple.ThisResultTrasform;
import br.com.myhelp.util.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @objetivo pegar os "FilterObjects" passar o filtro.
 * @fazerOsJoins
 * @passarOsFiltros se preciso
 * @executarUmaProjeção caso o tenha
 *
 * @maisAFrente caso precise implementar um TransformModel.
 *
 * @author drfelix Esta class servira pra fazer busca em uma lista em memoria em
 * formato criteria
 */
public final class ListCriteria {

    //propriedades
    private List<ModelInternoFiltro> filterObjects;

//    criterio para busca na lista
    private List<CriteriaBoolean> restricoes;

    private Projections projection;

    private ResultTransform resultTransforme;

    private OrderBy order;

    private Sort sort;

    private GroupBy groupBy;

    private Cluster group;

    //fim
    //construtor
    /**
     * Controi um ListCriteria com o "filterObjects" personalizado.
     *
     * @param filtro
     */
    private ListCriteria(FilterObjects filtro) {
        filterObjects = new ArrayList();
        filterObjects.add(new ModelInternoFiltro(filtro, null));
        restricoes = new ArrayList<>();
        resultTransforme = new ThisResultTrasform();
    }
    //fim

//    metodos de inicialização
    /**
     *
     * @param busca a lista que sera filtrada
     * @param as apelido para esse filtro
     * @return
     */
    public static ListCriteria begin(List busca, String as) {
        return begin(new DefaultFilterObject(busca, as));
    }

    /**
     * Cria um ListCriteria passando um FilterObjects padronizado.
     *
     * @param objects filtro personalizado
     * @return new ListCriteria
     */
    public static ListCriteria begin(FilterObjects objects) {
        return new ListCriteria(objects);
    }

//    fim
//    metodos que cria joins
    /**
     * Cria mais um FiltroObjects com a união padrão.
     *
     * @param list uma lista que vai ser criado o bagulho
     * @param as
     * @return this
     * @throws java.lang.Exception
     */
    public ListCriteria createCriteria(List list, String as) throws Exception {
        return createCriteria(new DefaultFilterObject(list, as), new InnerJoin());
    }

    public ListCriteria createCriteria(Object campo, String as) {
        return createCriteria(new DefaultFilterCampo(as, campo), new InnerJoin());
    }

    /**
     * Cria um criterio com o FilterObjects e Join personalizados.
     *
     * @param objFil
     * @param unir
     * @return
     */
    public ListCriteria createCriteria(Filter objFil, Join unir) {
        filterObjects.add(new ModelInternoFiltro(objFil, unir));
        return this;
    }

//    fim
//    metodos para configurar as operações
    /**
     * @param lbm criterio de busca na lista
     * @return uma instancia de CriteriaBoolean
     */
    public ListCriteria add(CriteriaBoolean lbm) {
        restricoes.add(lbm);
        return this;
    }

    /**
     *
     * @param projection uma projeção(seleção de dados)
     * @return this
     */
    public ListCriteria setProjection(Projections projection) {
        this.projection = projection;
        return this;
    }

    public ListCriteria setResultTransforme(ResultTransform resultTransforme) {
        if(resultTransforme == null){
            resultTransforme = new ThisResultTrasform();
        }else{
            this.resultTransforme = resultTransforme;
        }
        return this;
    }

    public ListCriteria orderBy(OrderBy order) {
        this.order = order;
        return this;
    }

    public ListCriteria setSort(Sort sort) {
        this.sort = sort;
        return this;
    }

    public ListCriteria groupBy(GroupBy gby) {
        this.groupBy = gby;
        return this;
    }

    public ListCriteria setGroup(Cluster group) {
        this.group = group;
        return this;
    }

    //fim
    //metodos que retorna o resultado da pesquisa.
    public <T> T uniqueResult() throws Exception {
        List auxRet = list();
        if (auxRet.isEmpty()) {
            return null;
        }
        
        if (auxRet.size() != 1) {
            throw new IllegalArgumentException("Mais de um Resultado no uniqueResult, possivelmente os criterios não selecionam apenas um.", null);
        }

        return (T) auxRet.get(0);
    }

    /**
     *
     * @return @throws Exception
     */
    public List list() throws Exception {
//        aux que vai guardar a lista do inicio ate o fim da execução.
        IteratorCriterio iteratorResult = null;
//        guarda o iterator temporario
        IteratorCriterio iteraTempo;

//        filtro atual.
        Filter filtro;
//        Vai guardar a lista para que possa ser deletado os criterios sem perda para proxima execução.
        ArrayList<CriteriaBoolean> auxRestrDelet = ((ArrayList) ((ArrayList) restricoes).clone());
//        aux que vai guardar os criterios temporario. de acordo com o asFiltro.
        List<CriteriaBoolean> criterios;

//        percorro todos os filtros setados por create criteria.
        for (int i = 0; i < filterObjects.size(); i++) {
//            pego o atual
            filtro = filterObjects.get(i).getFiltro();
//            pego os criterios pertecentes ao filtro atual.
            criterios = getCriteriosForFiltro(filtro.asIndex(), auxRestrDelet);
//            se não ouver algum criterio pro filtro atual.
            if (criterios.isEmpty()) {
                criterios = null;
            }

            
            /**
             * caminhos i = 0 e i > 0. i=0 : não tem como fazer join, mas faz a
             * ordenação. i>0 : faz a ordenação e filtra faz o join tambem.
             * sendo que quando for i>0 pode ocorrer de ser FilterObjects. ai a
             * join tem que ser primeiro.
             *
             * //sempre faz a ordenação primeiro.
             */
            if (filtro instanceof FilterCampos) {
                if (i == 0) {
                    throw new IllegalArgumentException("Não é possivel setar o FiltroCampos no index 0;");
                }
                /**
                 * Aqui ao meu ver tem duas possibilidades:. 1-> a cada registro
                 * que tem é crusado com a sua propria lista. É o que me
                 * intereça. 2-> pega todos os registros e crusa com todo mundo
                 * mantendo algum bagulho. O que preciso para fazer isso? toda a
                 * lista principal.
                 */
                FilterCampos filterCampos = (FilterCampos) filtro;
                filterCampos.setIteratorCriterio1(iteratorResult);
                IteratorCriterio itera = filterCampos.iterator2();
                itera.setCriterios(criterios);
                iteratorResult = filterObjects.get(i).unir.join(filterCampos.iterator1(), itera);
                
//                if(isOrder(iteratorResult.asIndex())){
//                    iteratorResult =sort.sort(iteratorResult, order.getOrdenamentos());
//                }
                
            } else {
                if(!(filtro instanceof FilterObjects)){
                    throw new IllegalArgumentException("O objeto filtto tem que ser FilterCampos ou FilterObject", null);
                }
                iteraTempo = ((FilterObjects)filtro).iteratorCriterio();
                iteraTempo.setCriterios(criterios);

//                //caso tenha ordenação.
//                if (isOrder(iteraTempo.asIndex())) {
//                    iteraTempo = sort.sort(iteraTempo, order.getOrdenamentos());
//                }

                if (i > 0) {
                    iteratorResult = filterObjects.get(i).unir.join(iteratorResult, iteraTempo);
                } else {
                    iteratorResult = iteraTempo;
                }
            }
        }

        if(projection == null){
            resultTransforme.setProjectionItems(null);
        }else{
            resultTransforme.setProjectionItems(projection.getListProjections());
        }
        
        return resultTransforme.resultTransform(iteratorResult);
    }

    private boolean isOrder(String[] asfilter) {
        for (CriteriaComparator compa : order.getOrdenamentos()) {
            if (!isAdd(compa, asfilter)) {
                return false;
            }
        }
        return true;
    }

    //fim
    /**
     * ha cada ação de join é executado essa operação, e a cada novo filtro da
     * lista.
     *
     * no caso de novo filtro caso este retorne vazil é entregue a lista sem
     * filtrar.
     *
     * as = ""
     *
     * criterios 
     * "nome","cpf"
     * -----------------------------
     * ??????
     * R: é Exigido o "as".
     *
     *
     * @param as
     * @param criterios deve ser um clone.
     * @return
     */
    private List getCriteriosForFiltro(String[] asFiltro, List criterios) {
        List<CriteriaBoolean> aux = criterios;
        List auxRet = new ArrayList();
        CriteriaBoolean auxCri;
        //add se todos os "as" do criterioBoolean comesar com algum do "asFiltro"
        Iterator auxIter = aux.iterator();
        while (auxIter.hasNext()) {
            auxCri = (CriteriaBoolean) auxIter.next();
            if (isAdd(auxCri, asFiltro)) {
                auxIter.remove();
                auxRet.add(auxCri);
            }
        }
        return auxRet;
    }

    /**
     * Verifico se todos os "as" do "crite" inicia com algum do "asfiltro".
     *
     * @param crite
     * @param asFiltro
     * @return
     */
    private boolean isAdd(CriteriaBoolean crite, String[] asFiltro) {
        boolean auxContinue;
        for (String auxCriterio : crite.getAs()) {
            auxContinue = false;
            for (String auxFiltro : asFiltro) {
                if (Util.likeToThis(auxCriterio, auxFiltro)) {
                    auxContinue = true;
                    break;
                }
            }
            if (!auxContinue) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "ListCriteria{" + "filterObjects=" + filterObjects + ", restricoes=" + restricoes + ", projection=" + projection + '}';
    }

    //modelos internos.
    private class ModelInternoFiltro {

        private Filter filtro;//responsavel por filtrar os dados: FilterObjects ou FilterCampos
        private Join unir;//responsavel por unir ao resto dos dados, no primeiro deve ser null

        public ModelInternoFiltro(Filter filtro, Join unir) {
            this.filtro = filtro;
            this.unir = unir;
        }

        public Filter getFiltro() {
            return filtro;
        }

        public Join getUnir() {
            return unir;
        }

    }

}
