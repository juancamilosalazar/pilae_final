package test.java.com.example.multimodule.servicio.negocio;

import com.example.multimodule.entidad.TorneoEntidad;
import com.example.multimodule.infraestructura.deporte.DeporteRepositorioJpa;
import com.example.multimodule.infraestructura.torneo.TorneoRepositorioJpa;
import com.example.multimodule.servicio.negocio.impl.TorneoServicioImpl;
import main.com.example.multimodule.dominio.TorneoDominio;
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
public class TorneoServicioImplTest {

    @Mock
    TorneoRepositorioJpa torneoRepositorioJpa;
    @Mock
    DeporteRepositorioJpa deporteRepositorioJpa;

    @InjectMocks
    TorneoServicioImpl torneoServicio;
    TorneoEntidad torneoEntidad = new TorneoEntidad();
    TorneoDominio torneoDominio = new TorneoDominio();



    @Before
    public void setUp() {
        torneoEntidad.setCodigo(1L);
        torneoEntidad.setNombre("nombre");
        torneoDominio.setCodigo(1L);
        torneoDominio.setNombre("nombre");
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnObtenerTodos() {
        List<TorneoEntidad> marcadorEntidadList = new ArrayList<>();
        Mockito.when(torneoRepositorioJpa.findAll()).thenReturn(marcadorEntidadList);
        torneoServicio.obtenerTodos();
    }

    @Test
    public void debeRetornarMarcadorDominio() {

        List<TorneoEntidad> torneoEntidadList = new ArrayList<>();
        torneoEntidadList.add(torneoEntidad);

        Mockito.when(torneoRepositorioJpa.findAll()).thenReturn(torneoEntidadList);

        List<TorneoDominio> marcadorDominios = torneoServicio.obtenerTodos();
        Mockito.verify(torneoRepositorioJpa, Mockito.times(1)).findAll();
        Assert.assertFalse(marcadorDominios.isEmpty());
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnObtenerPorId() {
        torneoServicio.obtenerPorId(1L);
    }

    @Test
    public void debeObtenerMarcadorPorId() {
        Mockito.when(torneoRepositorioJpa.findById(1L)).thenReturn(Optional.of(torneoEntidad));
        torneoServicio.obtenerPorId(1L);
        Mockito.verify(torneoRepositorioJpa, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnCrear() {
        torneoServicio.crear(null, 1L);
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnCrearPorMarcadorExistente() {
        Mockito.when(torneoRepositorioJpa.findById(1L)).thenReturn(Optional.of(torneoEntidad));
        torneoServicio.crear(torneoDominio, 1L);
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLevantarExcepcionEnActualizar() {
        torneoServicio.actualizar(null);
    }

    @Test
    public void debeActualizar() {
        Mockito.when(torneoRepositorioJpa.findById(1L)).thenReturn(Optional.of(torneoEntidad));
        torneoServicio.actualizar(torneoDominio);
        Mockito.verify(torneoRepositorioJpa, Mockito.times(1)).findById(1L);
    }

    @Test
    public void debeBorrar() {
        Mockito.when(torneoRepositorioJpa.findById(1L)).thenReturn(Optional.of(torneoEntidad));
        torneoServicio.borrar(1L);
        Mockito.verify(torneoRepositorioJpa, Mockito.times(1)).findById(1L);
    }

}
