package test.com.example.multimodule.transversal;

import main.com.example.multimodule.transversal.utilitarios.UtilTexto;
import org.junit.Assert;
import org.junit.Test;

public class UtilTextoTest {

    @Test
    public void shouldApplyTrim(){

        String trim = UtilTexto.aplicarTrim("  aplicarTrim   ");
        Assert.assertEquals("aplicarTrim",trim);
    }

    @Test
    public void contieneSoloLetras(){
        Assert.assertTrue(co.edu.uco.transversal.utilitarios.UtilTexto.contieneSoloLetrasEspacios("soloLetras y espacios"));
    }

    @Test
    public void contieneSoloNumeros(){
        Assert.assertTrue(co.edu.uco.transversal.utilitarios.UtilTexto.contieneSoloDigitos("1234567890"));
    }

    @Test
    public void obtenerValorDefecto(){
        String valorDefecto = "valorDefecto";
        Assert.assertEquals(co.edu.uco.transversal.utilitarios.UtilTexto.obtenerValorDefecto(null,valorDefecto),"valorDefecto");
    }

    @Test
    public void esVacia(){
        Assert.assertTrue(co.edu.uco.transversal.utilitarios.UtilTexto.estaVacia(""));
    }

}
