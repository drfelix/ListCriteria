/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myhelp.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author drfelix
 */
public class Util { 
    /**
     *
     * @param a
     * @param b
     * @return true se o inicio de "a" for igual a "b" e é obrigatorio ter um ponto no 
     * meio deles.
     * a= "pessoa.nome" b="pessoa."-> "pessoa.nome." return true; 
     * ---------------------------
     * caso for apenas o index "this"
     * ---------------------------
     * a="pessoa." b="pessoa" -> "pessoa." return true;
     */
    public static boolean likeToThis(String a, String b) {
        return //Primeira acertiva: eles b não pode ser maior que a
                (!(b.length()> a.length())) 
                &&
                //Segunda Acertiva: b+"." tem que ser igual a inicio de a +"."
                (b+".").equals((a + ".").substring(0,(b+".").length()));
    }
    
//    cria arquivo caso não exista

    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    
    public static File myCreateFile(File file) throws IOException {
        String fileStr = file.getPath();

        int at = 0;
        for (int i = fileStr.length() - 1; i > 0; i--) {
            if ((fileStr.charAt(i) + "").matches("/")) {
                at = i;
                break;
            }
        }
        File fileDiretorio = new File(fileStr.substring(0, at));

        fileDiretorio.mkdirs();

        file.createNewFile();

        return file;
    }

    /**
     *
     * @param to sera adicionado primeiro no array de retorno
     * @param adicionado sera adicionado depois no array de retorno
     * @return to+adicionado em um array.
     */
    public static Object[] addAll(Object[] adicionado, Object[] to) {
        Object[] ret = new Object[adicionado.length + to.length];
        for (int i = 0; i < to.length; i++) {
            ret[i] = to[i];
        }
        for (int i = to.length; i < to.length + adicionado.length; i++) {
            ret[i] = adicionado[i - to.length];
        }

        return ret;
    }

    /**
     *
     * @param adicionado
     * @param to
     * @return
     */
    public static String[] addAll(String[] adicionado, String[] to) {
        String[] ret = new String[adicionado.length + to.length];
        for (int i = 0; i < to.length; i++) {
            ret[i] = to[i];
        }
        for (int i = to.length; i < to.length + adicionado.length; i++) {
            ret[i] = adicionado[i - to.length];
        }

        return ret;
    }

    /**
     *
     * @param campo caminho do campo ao qual vai ser usado, se passado this
     * retorna null, esse metodo reconhece herança.
     * @param c class onde vai ser pego o campo
     * @param as indica quem é o root, se não sabe o que colocar coloque "this"
     * @return uma lista de Fields que representa o caminho do campo
     * @throws NoSuchFieldException
     */
    public static List<Field> extractCampo(String campo, Class c,String as) throws NoSuchFieldException {
//        null dis ao pegeValue que tem que retornar o objeto atual.
        if (campo.equals(as)) {
            return null;
        }
//        lista de retorno.
        List<Field> ret = new ArrayList<>();
        try {
//            usado para guardar o nome do campo
            String auxNomeCampo = "";
//            caracter pego.
            char pego = ' ';
//            guarda o ultimo campo pego
            Field temCampo = null;

//            percorro todos os caracteres da string
            for (int i = 0; i <= campo.length(); i++) {

//                se i menor que o numero de caracteres.
                if (i < campo.length()) {
//                pego o atual
                    pego = campo.charAt(i);

                }
//                se atual igual "." || "i" igual ao tamanho do campo: indica que ja tem um campo pego ; indica que ainda esta faltando partes do mesmo
                if ((pego + "").equals(".") || i == campo.length()) {
//                    se tiver pego algum campo, é pego a class que ele pertence.
                    if (temCampo != null) {
                        c = temCampo.getType();
                    }
//                    se não for o primeiro || se for o primeiro e o campo for diferente do as. ai eu pego o campo atual
                    if (temCampo != null || !auxNomeCampo.equals(as)) {
//                    loop infinito
                        while (true) {
                            try {
//                            pego o campo da class passada
                                temCampo = c.getDeclaredField(auxNomeCampo);
                                temCampo.setAccessible(true);
                                ret.add(temCampo);
                                break;

//                            caso ocorra uma exceotion
                            } catch (NoSuchFieldException | SecurityException e) {
//                            Se "c" não tiver class pai
                                if (c.getSuperclass().equals(Object.class)) {
//                                lanço a exception
                                    throw e;
                                } else {
//                                caso não pego a superclass.
                                    c = c.getSuperclass();
                                }
                            }
                        }
                    }

//                    limpo o campo para um novo campo
                    auxNomeCampo = "";
                } else {
//                    concateno o valor de "pego" com "auxNomeCampo"
                    auxNomeCampo += pego;
                }
            }
        } catch (NoSuchFieldException | SecurityException e) {
            throw new IllegalArgumentException(campo + " Não encontrado AT " + c);
        }
        return ret;
    }

    /**
     *
     * @param cam caminho do objeto. Se passado null retorna o
     * @param o root do caminho do objeto
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Object pegeValue(List<Field> cam, Object o) throws IllegalArgumentException, IllegalAccessException {
        if (cam == null) {
            return o;
        }
        Object ret = o;
        for (Field cam1 : cam) {
            ret = cam1.get(ret);
        }
        return ret;
    }

}
