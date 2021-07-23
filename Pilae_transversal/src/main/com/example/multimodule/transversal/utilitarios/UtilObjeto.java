package main.com.example.multimodule.transversal.utilitarios;

import org.apache.commons.lang3.ObjectUtils;

public class UtilObjeto {
    private UtilObjeto() {
    }

    public static <T> boolean objetoEsNulo(T objeto) {
        return objeto == null;
    }

    public static <T> T obtenerValorPorDefecto(T valor, T defecto) {
        return ObjectUtils.defaultIfNull(valor, defecto);
    }
}
