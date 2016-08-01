package br.com.myhelp.criteria.Criterios;

import br.com.myhelp.criteria.GetParametros;
import br.com.myhelp.criteria.ValueAtual;
import br.com.myhelp.criteria.aux.CriteriaNameFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author drfelix
 */
public abstract class CriteriaBoolean implements
        ValueAtual.MelhoraDesempenho,
        GetParametros {

    protected Object campo;//campo onde vai pegar o parametro, pode ser CriteriaName.
    protected Object[] parametros;
    private List<String> as;

    public CriteriaBoolean(Object campo, Object... parametros) {
        this.campo = campo;
        this.parametros = parametros;

        as = new ArrayList<>();
        String campoString = null;
        
        if(campo != null){
            campoString = CriteriaNameFactory.trasformToAsCampo(campo);
        }
        
        if (campoString != null) {
            as.add(campoString);
        }
        as.addAll(CriteriaNameFactory.trasformToAsParametros(parametros));

    }

    public Object getCampo() {
        return campo;
    }

    @Override
    public void setCampo(Object campo) {
        try {
            this.campo = campo;
            as.set(0, CriteriaNameFactory.trasformToAsCampo(campo));
        } catch (Exception e) {
            System.out.println("Erro aceitavel- ao mudar valor de campo criteriaBoolean");
        }
    }

    /**
     * Usado apenas para melhorar o desempenho então não sera mudado
     * o valor no "as"
     * @param valor
     * @param index 
     */
    @Override
    public void setValueAt(Object valor, int index) {
        parametros[index] = valor;
    }

    public Object[] getParametros() {
        return parametros;
    }

    public void setParametros(Object... parametros) {
        this.parametros = parametros;
        //remove antigos.

        Object obj = as.get(0);

        as = new ArrayList();

        as.add(obj + "");
        as.addAll(CriteriaNameFactory.trasformToAsParametros(parametros));
    }

    @Override
    public List<String> getAs() {
        return as;
    }

    public abstract boolean myEquals(Object test, ValueAtual atual) throws Exception;

    @Override
    public String toString() {
        return "CriteriaList{" + "campo=" + campo + ", parametros=" + Arrays.toString(parametros) + '}';
    }

}
