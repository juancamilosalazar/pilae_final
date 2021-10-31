package test.java.com.example.multimodule.servicio.negocio;

import com.example.multimodule.entidad.JugadorEntidad;
import com.example.multimodule.infraestructura.jugador.JugadorRepositorioJpa;
import com.example.multimodule.servicio.negocio.impl.JugadorServicioImpl;
import main.com.example.multimodule.dominio.JugadorDominio;
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
public class JugadorServicioImplTest {


    @Mock
    JugadorRepositorioJpa jugadorRepositorio;

    JugadorEntidad jugadorEntidad = new JugadorEntidad();
    JugadorDominio jugadorDominio = new JugadorDominio();



    @InjectMocks
    JugadorServicioImpl jugadorServicio;

    @Before
    public void setUp(){
        jugadorDominio.setCodigo(1L);
        jugadorDominio.setNombre("jugador1");
    }
    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnObtenerTodos(){
        List<JugadorEntidad> deportes = new ArrayList<>();
        Mockito.when(jugadorRepositorio.findAll()).thenReturn(deportes);
        jugadorServicio.obtenerTodos();
    }

    @Test
    public void debeRetornarJugadorDominio(){

        List<JugadorEntidad> jugadorEntidadList = new ArrayList<>();
        jugadorEntidadList.add(jugadorEntidad);

        Mockito.when(jugadorRepositorio.findAll()).thenReturn(jugadorEntidadList);

        List<JugadorDominio> deporteDominios = jugadorServicio.obtenerTodos();
        Mockito.verify(jugadorRepositorio,Mockito.times(1)).findAll();
        Assert.assertFalse(deporteDominios.isEmpty());
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnObtenerPorId(){
        jugadorServicio.obtenerPorId(1L);
    }

    @Test
    public void debeObtenerDeportePorId(){
        Mockito.when(jugadorRepositorio.findById(1L)).thenReturn(Optional.of(jugadorEntidad));
        jugadorServicio.obtenerPorId(1L);
        Mockito.verify(jugadorRepositorio,Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnCrear(){
        jugadorServicio.crear(null,1L);
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnCrearPorDeporteExistente(){
        Mockito.when(jugadorRepositorio.findById(1L)).thenReturn(Optional.of(jugadorEntidad));
        jugadorServicio.crear(jugadorDominio,1L);
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLevantarExcepcionEnActualizar(){
        jugadorServicio.actualizar(null);
    }

    @Test
    public void debeActualizar(){
        Mockito.when(jugadorRepositorio.findById(1L)).thenReturn(Optional.of(jugadorEntidad));
        jugadorServicio.actualizar(jugadorDominio);
        Mockito.verify(jugadorRepositorio,Mockito.times(1)).findById(1L);
    }

    @Test
    public void debeBorrar(){
        Mockito.when(jugadorRepositorio.findById(1L)).thenReturn(Optional.of(jugadorEntidad));
        jugadorServicio.borrar(1L);
        Mockito.verify(jugadorRepositorio,Mockito.times(1)).findById(1L);
    }



}
