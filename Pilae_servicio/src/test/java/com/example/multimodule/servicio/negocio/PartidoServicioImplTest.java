package test.java.com.example.multimodule.servicio.negocio;

import com.example.multimodule.entidad.PartidoEntidad;
import com.example.multimodule.infraestructura.marcador.MarcadorRepositorioJpa;
import com.example.multimodule.infraestructura.partido.PartidoRepositorioJpa;
import com.example.multimodule.infraestructura.torneo.TorneoRepositorioJpa;
import com.example.multimodule.servicio.negocio.impl.PartidoServicioImpl;
import com.example.multimodule.servicio.negocio.impl.utilitario.GeneracionFixtureSoloIda;
import main.com.example.multimodule.dominio.PartidoDominio;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
public class PartidoServicioImplTest {

    @Mock
    PartidoRepositorioJpa partidoRepositorioJpa;
    @Mock
    com.example.multimodule.infraestructura.equipo.EquipoRepositorioJpa equipoRepositorioJpa;
    @Mock
    TorneoRepositorioJpa torneoRepositorioJpa;
    @Mock
    com.example.multimodule.infraestructura.posicion.PosicionRepositorioJpa posicionRepositorioJpa;
    @Mock
    MarcadorRepositorioJpa marcadorRepositorioJpa;
    @Mock
    com.example.multimodule.servicio.negocio.impl.utilitario.GeneracionFixtureIdaYVuelta generacionFixtureIdaYVuelta;
    @Mock
    GeneracionFixtureSoloIda generacionFixtureSoloIda;

    @InjectMocks
    PartidoServicioImpl partidoServicio;

    PartidoEntidad partidoEntidad = new PartidoEntidad();
    PartidoDominio partidoDominio = new PartidoDominio();

    @Before
    public void setUp(){
        partidoEntidad.setEstadoPartido("estado");
        partidoEntidad.setCodigo(1L);
        partidoDominio.setCodigo(1L);
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnObtenerTodos() {
        List<PartidoEntidad> partidoEntidadList = new ArrayList<>();
        Mockito.when(partidoRepositorioJpa.findAll()).thenReturn(partidoEntidadList);
        partidoServicio.obtenerTodos();
    }

    @Test
    public void debeRetornarMarcadorDominio() {

        List<PartidoEntidad> partidoEntidadList = new ArrayList<>();
        partidoEntidadList.add(partidoEntidad);

        Mockito.when(partidoRepositorioJpa.findAll()).thenReturn(partidoEntidadList);

        List<PartidoDominio> marcadorDominios = partidoServicio.obtenerTodos();
        Mockito.verify(partidoRepositorioJpa, Mockito.times(1)).findAll();
        Assert.assertFalse(marcadorDominios.isEmpty());
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnObtenerPorId() {
        partidoServicio.obtenerPorId(1L);
    }

    @Test
    public void debeObtenerMarcadorPorId() {
        Mockito.when(partidoRepositorioJpa.findById(1L)).thenReturn(Optional.of(partidoEntidad));
        partidoServicio.obtenerPorId(1L);
        Mockito.verify(partidoRepositorioJpa, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnCrear() {
        partidoServicio.crear(null, 1L,1L,1L);
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnCrearPorMarcadorExistente() {
        Mockito.when(partidoRepositorioJpa.findById(1L)).thenReturn(Optional.of(partidoEntidad));
        partidoServicio.crear(partidoDominio, 1L,1L,1L);
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLevantarExcepcionEnActualizar() {
        partidoServicio.actualizar(null);
    }

    @Test
    public void debeActualizar() {
        Mockito.when(partidoRepositorioJpa.findById(1L)).thenReturn(Optional.of(partidoEntidad));
        partidoServicio.actualizar(partidoDominio);
        Mockito.verify(partidoRepositorioJpa, Mockito.times(1)).findById(1L);
    }

    @Test
    public void debeBorrar() {
        Mockito.when(partidoRepositorioJpa.findById(1L)).thenReturn(Optional.of(partidoEntidad));
        partidoServicio.borrar(1L);
        Mockito.verify(partidoRepositorioJpa, Mockito.times(1)).findById(1L);
    }


}
