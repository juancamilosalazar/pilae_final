package main.com.example.multimodule.transversal.excepciones;

import main.com.example.multimodule.transversal.excepciones.base.ComponenteEnum;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;

public class PILAETransversalExepcion extends PILAEExcepcion {

    private static final long serialVersionUID = -3030168344298978822L;

    private PILAETransversalExepcion(TipoExcepcionEnum tipo, String textoUsuario, String textoTecnico, Exception excepcionRaiz) {
        super(tipo, textoUsuario, textoTecnico, ComponenteEnum.TRANSVERSAL, excepcionRaiz);
    }

    public static PILAEExcepcion crear(TipoExcepcionEnum tipo, String textoUsuario, String textoTecnico, Exception excepcionRaiz) {
        return new PILAETransversalExepcion(tipo, textoUsuario, textoTecnico, excepcionRaiz);
    }

}