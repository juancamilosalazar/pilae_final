package test.com.example.multimodule.dominio;

import main.com.example.multimodule.dominio.*;
import org.junit.Test;

public class DominioTest {

    @Test
    public void deporteDominioTest() {
        DeporteDominio deporteDominio = new DeporteDominio();
        deporteDominio.setNombre("nombre");
        deporteDominio.setCodigo(1L);
    }

    @Test
    public void equipoDominioTest() {
        EquipoDominio equipoDominio = new EquipoDominio();
        equipoDominio.setCodigo(1L);
        equipoDominio.setNombre("nombre");
        equipoDominio.setGenero("genero");
        equipoDominio.setLocacion("locacion");
        equipoDominio.setFkTorneo(new TorneoDominio());
    }

    @Test
    public void jugadorDominioTest() {
        JugadorDominio jugadorDominio = new JugadorDominio();
        jugadorDominio.setNombre("nombre");
        jugadorDominio.setCodigo(1L);
        jugadorDominio.setFechaNacimiento(1L);
        jugadorDominio.setFkEquipo(new EquipoDominio());
        jugadorDominio.setIdentificacion("");
    }

    @Test
    public void marcadorDominioTest() {
        MarcadorDominio marcadorDominio = new MarcadorDominio();
        marcadorDominio.setEquipoGanador("equipoGanador");
        marcadorDominio.setCodigo(1L);
        marcadorDominio.setEquipoLocalMrc(1);
        marcadorDominio.setFkPartido(new PartidoDominio());
        marcadorDominio.setEquipoVisitanteMrc(1);
    }

    @Test
    public void partidoDominio() {
        PartidoDominio partidoDominio = new PartidoDominio();
        partidoDominio.setEstadoPartido("finalizado");
        partidoDominio.setFechaDelPartido(1L);
        partidoDominio.setCodigo(1L);
        partidoDominio.setFkEquipoLocal(new EquipoDominio());
        partidoDominio.setFkTorneo(new TorneoDominio());
        partidoDominio.setFkEquipoVisitante(new EquipoDominio());
        partidoDominio.setIdaVuelta("si");
        partidoDominio.setRonda("octavos");
    }

    @Test
    public void posicionDominio() {
        PosicionDominio posicionDominio = new PosicionDominio();
        posicionDominio.setCodigo(1L);
        posicionDominio.setPartidosJugados(1);
        posicionDominio.setPartidosGanados(1);
        posicionDominio.setPartidosPerdidos(1);
        posicionDominio.setPartidosEmpatados(1);
        posicionDominio.setGolesFavor(1);
        posicionDominio.setGolesContra(1);
        posicionDominio.setGolesDiferencia(0);
        posicionDominio.setPuntos(0);
        posicionDominio.setFkEquipo(new EquipoDominio());
        posicionDominio.setFkTorneo(new TorneoDominio());
    }

    @Test
    public void torneoDominio(){
        TorneoDominio torneoDominio = new TorneoDominio();
        torneoDominio.setNombre("nombre");
        torneoDominio.setCodigo(1L);
    }
}
