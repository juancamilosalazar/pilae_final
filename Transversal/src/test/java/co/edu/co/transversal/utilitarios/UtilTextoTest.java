package co.edu.co.transversal.utilitarios;

import co.edu.uco.transversal.utilitarios.ParametroDTO;
import co.edu.uco.transversal.utilitarios.UtilTexto;
import org.junit.Assert;
import org.junit.Test;

public class UtilTextoTest {

    @Test
    public void shouldApplyTrim(){

        String trim = UtilTexto.aplicarTrim("  aplicarTrim   ");
        Assert.assertEquals("aplicarTrim",trim);
    }

    @Test
    public void debeReemplazar(){
       String result = UtilTexto.reemplazar(" cadena", ParametroDTO.crear("key","value"));
       Assert.assertEquals(result,"cadena");
    }

    @Test
    public void contieneSoloLetras(){
        Assert.assertTrue(UtilTexto.contieneSoloLetrasEspacios("soloLetras y espacios"));
    }

    @Test
    public void contieneSoloNumeros(){
        Assert.assertTrue(UtilTexto.contieneSoloDigitos("1234567890"));
    }

    @Test
    public void obtenerValorDefecto(){
        String valorDefecto = "valorDefecto";
        Assert.assertEquals(UtilTexto.obtenerValorDefecto(null,valorDefecto),"valorDefecto");
    }

    @Test
    public void esVacia(){
        Assert.assertTrue(UtilTexto.estaVacia(""));
    }
}
