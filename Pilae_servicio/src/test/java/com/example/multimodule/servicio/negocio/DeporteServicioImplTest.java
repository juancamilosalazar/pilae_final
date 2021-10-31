package test.java.com.example.multimodule.servicio.negocio;

import com.example.multimodule.entidad.DeporteEntidad;
import com.example.multimodule.infraestructura.deporte.DeporteRepositorioJpa;
import com.example.multimodule.servicio.negocio.impl.DeporteServicioImpl;
import main.com.example.multimodule.dominio.DeporteDominio;
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
public class DeporteServicioImplTest {


    @Mock
    DeporteRepositorioJpa deporteRepositorio;

    DeporteEntidad deporteEntidad = new DeporteEntidad();
    DeporteDominio deporteDominio = new DeporteDominio();



    @InjectMocks
    DeporteServicioImpl deporteServicio;

    @Before
    public void setUp(){
        deporteEntidad.setNombre("futbol");
        deporteEntidad.setCodigo(1);
        deporteDominio.setCodigo(1L);
        deporteDominio.setNombre("futbol");
    }
    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnObtenerTodos(){
        List<DeporteEntidad> deportes = new ArrayList<>();
        Mockito.when(deporteRepositorio.findAll()).thenReturn(deportes);
        deporteServicio.obtenerTodos();
    }

    @Test
    public void debeRetornarDeporteDominio(){

        List<DeporteEntidad> deporteEntidadList = new ArrayList<>();
        deporteEntidadList.add(deporteEntidad);

        Mockito.when(deporteRepositorio.findAll()).thenReturn(deporteEntidadList);

        List<DeporteDominio> deporteDominios = deporteServicio.obtenerTodos();
        Mockito.verify(deporteRepositorio,Mockito.times(1)).findAll();
        Assert.assertFalse(deporteDominios.isEmpty());
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnObtenerPorId(){
        deporteServicio.obtenerPorId(1L);
    }

    @Test
    public void debeObtenerDeportePorId(){
        Mockito.when(deporteRepositorio.findById(1L)).thenReturn(Optional.of(deporteEntidad));
        deporteServicio.obtenerPorId(1L);
        Mockito.verify(deporteRepositorio,Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnCrear(){
        deporteServicio.crear(null);
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnCrearPorDeporteExistente(){
        Mockito.when(deporteRepositorio.findById(1L)).thenReturn(Optional.of(deporteEntidad));
        deporteServicio.crear(deporteDominio);
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLevantarExcepcionEnActualizar(){
        deporteServicio.actualizar(null);
    }

    @Test
    public void debeActualizar(){
        Mockito.when(deporteRepositorio.findById(1L)).thenReturn(Optional.of(deporteEntidad));
        deporteServicio.actualizar(deporteDominio);
        Mockito.verify(deporteRepositorio,Mockito.times(1)).findById(1L);
    }

    @Test
    public void debeBorrar(){
        Mockito.when(deporteRepositorio.findById(1L)).thenReturn(Optional.of(deporteEntidad));
        deporteServicio.borrar(1L);
        Mockito.verify(deporteRepositorio,Mockito.times(1)).findById(1L);
    }



}
