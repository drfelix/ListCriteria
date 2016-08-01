package br.com.myhelp.criteria.aux;

import br.com.myhelp.criteria.ValueAtual;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author drfelix
 */
public class Projections {

    private List<ProjectionItem> projectionsItem;

    private Projections() {
        projectionsItem = new ArrayList<>();
    }

    public static Projections cria() {
        return new Projections();
    }

    public Projections addProjectio(ProjectionItem item) {
        projectionsItem.add(item);
        return this;
    }

    public List<ProjectionItem> getListProjections() {
        return projectionsItem;
    }

    /**
     *
     * @param campo campo ao qual sera pego
     * @return o valor do campo do objeto.
     */
    public static ProjectionItem propertName(Object campo) {
        return new Projections.ProjectionItem(campo) {
            @Override
            public Object getValorCampo(ValueAtual pojo) throws Exception {
                return pojo.getCampo(campo, this);
            }
        };
    }
    
    
    public static ProjectionItem index(String as){
        return new ProjectionItem(as) {
            
            @Override
            public Object getValorCampo(ValueAtual pojo) throws Exception {
                return pojo.getIndex(campo+"");
            }
        };
    }
    

//    isso é pra que "entre" em um objeto e traga tudo de uma só vez
    public static abstract class ProjectionItem implements ValueAtual.MelhoraDesempenho{

        protected Object campo;//o campo atual ao qual deve ser feito a ação.
//                              (pode ser uma String com o nome do campo ou o outro bagulho igual na CriteriaList)
        protected String as;//apelido, pode ser null na hora de pegar o valor
//        private Class clasPertence = null;//class ao qual pertence o campo
//        private List<Field> campoPego = null;

        /**
         *
         * @param campo
         */
        public ProjectionItem(Object campo) {
            this.campo = campo;
            this.as = null;
        }

        public ProjectionItem as(String as) {
            this.as = as;
            return this;
        }

        public String getAs() {
            return this.as;
        }
        
        @Override
        public void setValueAt(Object valor, int index) {
            throw new UnsupportedOperationException("Não há parametros."); //To change body of generated methods, choose Tools | Templates.
        }
        
        @Override
        public void setCampo(Object campo) {
            this.campo = campo;
        }

        public Object getCampo() {
            return campo;
        }

        /**
         * "Digo" lhe entrego um "pegador de objeto" mas quero um
         * ModelProjection.
         *
         * Este metodo é responsavel por pegar o valor dos campos referente ao
         * pojo e colocar em uma lista de ModelProjection independente da
         * quantidade e criterio que vou usar.
         *
         * @param pojo objeto ao qual sera retirado o valor
         *
         * @return o ModelProjection com o campo "String[] as" preenchido com o
         * apelido ou nome do campo e o "List<Object[]> values" com o valor na
         * mesma ordem do "as" Exemplo: Pessoa Anderson (nome) 20 (idade)
         * dividas (lista de dividas) Divida 1 22/05/2000 (data) 300.00(valor)
         * Divida 2 26/08/2001 (data) 200.00(valor) Rita (nome) 21 (idade)
         * dividas (lista de dividas) Divida 1 22/05/2015 (data) 123.00(valor)
         *
         * Nesse caso eu escolhi apenas o "nome" e foi passado o primeiro item:
         * as={nome} values={Anderson}
         *
         * agora o segundo: as={nome} values={Rita}
         *
         * e no final: as={nome} values={ {Anderson}, {Rita} }
         *
         * Com esses dados eu monto um Array de uma coluna e o "apelido" "nome".
         *
         * E quando precisar pegar uma lista dentre outros dados: Exemplo: Pegue
         * "idade","nome","agora de dividas pegue"data","valor"" ...
         *
         *
         * CUIDADO: caso retorne null o objeto sera desconsiderado.
         * @throws java.lang.Exception
         */
        public abstract Object getValorCampo(ValueAtual pojo) throws Exception;

    }
}
