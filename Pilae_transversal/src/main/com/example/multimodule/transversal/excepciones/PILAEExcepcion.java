package main.com.example.multimodule.transversal.excepciones;

import main.com.example.multimodule.transversal.excepciones.base.BaseExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.ComponenteEnum;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;

public class PILAEExcepcion extends BaseExcepcion {

    private static final long serialVersionUID = 3280632630011172520L;

    protected PILAEExcepcion(TipoExcepcionEnum tipo, String textoUsuario, String textoTecnico, ComponenteEnum componente, Exception excepcionRaiz) {
        super(tipo, textoUsuario, textoTecnico, componente, excepcionRaiz);
    }
}
