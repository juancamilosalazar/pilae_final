package main.com.example.multimodule.transversal.utilitarios;

import org.apache.commons.lang3.StringUtils;

import static main.com.example.multimodule.transversal.utilitarios.UtilObjeto.objetoEsNulo;

public class UtilTexto {

    private static final String PATRON_SOLO_DIGITOS = "[0-9]+";
    private static final String PATRON_SOLO_LETRAS_Y_ESPACIOS = "[A-Za-z������������ ]+";
    private static final String FORMATO_EXPRESION_REGULAR = "^{1}$";
    public static final String VACIO = "";

    private UtilTexto() {
    }

    public static String obtenerValorDefecto(String valor, String defecto) {
        return StringUtils.defaultString(valor, defecto);
    }

    public static String aplicarTrim(String valor) {
        return StringUtils.trimToEmpty(valor);
    }

    public static boolean estaVacia(String valor) {
        return StringUtils.EMPTY.equals(aplicarTrim(valor));
    }

    public static String reemplazar(String cadena, ParametroDTO... parametros) {
        String cadenaRetorno = aplicarTrim(cadena);

        for (ParametroDTO parametroDTO : parametros) {
            if (!objetoEsNulo(parametroDTO)) {
                cadenaRetorno = cadenaRetorno.replace(aplicarTrim(parametroDTO.getClave()), aplicarTrim(parametroDTO.getValor()));
            }
        }

        return cadenaRetorno;
    }

    public static boolean cumplePatron(String cadena, String patron) {
        return aplicarTrim(cadena).matches(aplicarTrim(patron));
    }

    public static boolean contieneSoloDigitos(String cadena) {
        String expresionRegular = reemplazar(FORMATO_EXPRESION_REGULAR, ParametroDTO.crear("{1}", PATRON_SOLO_DIGITOS));
        return cumplePatron(cadena, expresionRegular);
    }

    public static boolean contieneSoloLetrasEspacios(String cadena) {
        String expresionRegular = reemplazar(FORMATO_EXPRESION_REGULAR, ParametroDTO.crear("{1}", PATRON_SOLO_LETRAS_Y_ESPACIOS));
        return cumplePatron(cadena, expresionRegular);
    }

}
