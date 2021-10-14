package com.example.multimodule.utilitario;

import main.com.example.multimodule.transversal.respuesta.Respuesta;
import main.com.example.multimodule.transversal.utilitarios.UtilTexto;
import org.springframework.stereotype.Component;

@Component
public class Validadores<T> {

    public void validarDatosCodigo(String codigo, Respuesta<T> respuesta, boolean datosValidos){

        if (UtilTexto.estaVacia(codigo)) {
            String mensajeUsuario = "El código  es un dato obligatorio!";
            respuesta.agregarMensaje(mensajeUsuario);
            datosValidos = false;
        } else if (!UtilTexto.contieneSoloDigitos(codigo)) {
            String mensajeUsuario = "El código sólo puede contener dígitos!";
            respuesta.agregarMensaje(mensajeUsuario);
            datosValidos = false;
        } else if (UtilTexto.aplicarTrim(codigo).length() > 60) {
            String mensajeUsuario = "El código no puede exceder los 60 caracteres!";
            respuesta.agregarMensaje(mensajeUsuario);
            datosValidos = false;
        }

    }

    public void validarDatosNombre(String nombre, Respuesta<T> respuesta, boolean datosValidos){

        if (UtilTexto.estaVacia(nombre)) {
            String mensajeUsuario = "El nombre es un dato obligatorio!";
            respuesta.agregarMensaje(mensajeUsuario);
            datosValidos = false;
        } else if (!UtilTexto.contieneSoloLetrasEspacios(nombre)) {
            String mensajeUsuario = "El nombre  sólo puede contener letras y espacios!";
            respuesta.agregarMensaje(mensajeUsuario);
            datosValidos = false;
        } else if (UtilTexto.aplicarTrim(nombre).length() > 100) {
            String mensajeUsuario = "El nombre  no puede exceder los 100 caracteres!";
            respuesta.agregarMensaje(mensajeUsuario);
            datosValidos = false;
        }
    }
}
