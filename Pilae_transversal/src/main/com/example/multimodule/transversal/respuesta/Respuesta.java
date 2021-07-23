package main.com.example.multimodule.transversal.respuesta;

import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import main.com.example.multimodule.transversal.utilitarios.UtilTexto;

import java.util.ArrayList;
import java.util.List;

public class Respuesta<T> {

    private EstadoRespuestaEnum estado;
    private List<String> mensajesRepuesta;
    private List<T> resultado;

    public void agregarMensaje(String mensaje) {
        if(!UtilTexto.estaVacia(mensaje)) {
            getMensajesRepuesta().add(mensaje);
        }
    }

    public EstadoRespuestaEnum getEstado() {
        if (UtilObjeto.objetoEsNulo(estado)) {
            setEstado(estado);
        }
        return estado;
    }

    public void setEstado(EstadoRespuestaEnum estado) {
        this.estado = UtilObjeto.obtenerValorPorDefecto(estado, EstadoRespuestaEnum.ERROR);
    }

    public List<String> getMensajesRepuesta() {
        if (UtilObjeto.objetoEsNulo(mensajesRepuesta)) {
            setMensajesRepuesta(mensajesRepuesta);
        }
        return mensajesRepuesta;
    }

    public void setMensajesRepuesta(List<String> mensajesRepuesta) {
        this.mensajesRepuesta = UtilObjeto.obtenerValorPorDefecto(mensajesRepuesta, new ArrayList<>());
    }

    public List<T> getResultado() {
        if (UtilObjeto.objetoEsNulo(resultado)) {
            setResultado(resultado);
        }
        return resultado;
    }

    public void setResultado(List<T> resultado) {
        this.resultado = UtilObjeto.obtenerValorPorDefecto(resultado, new ArrayList<>());
    }
}
