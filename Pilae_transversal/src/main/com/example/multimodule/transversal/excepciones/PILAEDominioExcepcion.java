package main.com.example.multimodule.transversal.excepciones;

import main.com.example.multimodule.transversal.excepciones.base.ComponenteEnum;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;

public class PILAEDominioExcepcion extends PILAEExcepcion {

    private static final long serialVersionUID = -3030168344298978822L;

    private PILAEDominioExcepcion(TipoExcepcionEnum tipo, String textoUsuario, String textoTecnico, Exception excepcionRaiz) {
        super(tipo, textoUsuario, textoTecnico, ComponenteEnum.DOMINIO, excepcionRaiz);
    }

    public static PILAEExcepcion crear(TipoExcepcionEnum tipo, String textoUsuario, String textoTecnico, Exception excepcionRaiz) {
        return new PILAEDominioExcepcion(tipo, textoUsuario, textoTecnico, excepcionRaiz);
    }

    public static PILAEExcepcion crear(TipoExcepcionEnum tipo, String textoUsuario, String textoTecnico) {
        return new PILAEDominioExcepcion(tipo, textoUsuario, textoTecnico, new Exception(textoTecnico));
    }

}