package test.java.com.example.multimodule.servicio.negocio;

import com.example.multimodule.entidad.MarcadorEntidad;
import com.example.multimodule.infraestructura.marcador.MarcadorRepositorioJpa;
import com.example.multimodule.infraestructura.partido.PartidoRepositorioJpa;
import com.example.multimodule.servicio.negocio.impl.MarcadorServicioImpl;
import main.com.example.multimodule.dominio.MarcadorDominio;
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
public class MarcadorServicioImplTest {

    @Mock
    MarcadorRepositorioJpa marcadorRepositorioJpa;

    @Mock
    PartidoRepositorioJpa partidoRepositorioJpa;

    @InjectMocks
    MarcadorServicioImpl marcadorServicio;

    MarcadorEntidad marcadorEntidad = new MarcadorEntidad();
    MarcadorDominio marcadorDominio = new MarcadorDominio();

    @Before
    public void setUp(){
        marcadorEntidad.setCodigo(1L);
        marcadorEntidad.setEquipoGanador("equipoGanador");
        marcadorDominio.setCodigo(1L);
        marcadorDominio.setEquipoGanador("equipoGanador");
    }
    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnObtenerTodos(){
        List<MarcadorEntidad> marcadorEntidadList = new ArrayList<>();
        Mockito.when(marcadorRepositorioJpa.findAll()).thenReturn(marcadorEntidadList);
        marcadorServicio.obtenerTodos();
    }

    @Test
    public void debeRetornarMarcadorDominio(){

        List<MarcadorEntidad> marcadorEntidadList = new ArrayList<>();
        marcadorEntidadList.add(marcadorEntidad);

        Mockito.when(marcadorRepositorioJpa.findAll()).thenReturn(marcadorEntidadList);

        List<MarcadorDominio> marcadorDominios = marcadorServicio.obtenerTodos();
        Mockito.verify(marcadorRepositorioJpa,Mockito.times(1)).findAll();
        Assert.assertFalse(marcadorDominios.isEmpty());
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnObtenerPorId(){
        marcadorServicio.obtenerPorId(1L);
    }

    @Test
    public void debeObtenerMarcadorPorId(){
        Mockito.when(marcadorRepositorioJpa.findById(1L)).thenReturn(Optional.of(marcadorEntidad));
        marcadorServicio.obtenerPorId(1L);
        Mockito.verify(marcadorRepositorioJpa,Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnCrear(){
        marcadorServicio.crear(null,1L);
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnCrearPorMarcadorExistente(){
        Mockito.when(marcadorRepositorioJpa.findById(1L)).thenReturn(Optional.of(marcadorEntidad));
        marcadorServicio.crear(marcadorDominio,1L);
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLevantarExcepcionEnActualizar(){
        marcadorServicio.actualizar(null);
    }

    @Test
    public void debeActualizar(){
        Mockito.when(marcadorRepositorioJpa.findById(1L)).thenReturn(Optional.of(marcadorEntidad));
        marcadorServicio.actualizar(marcadorDominio);
        Mockito.verify(marcadorRepositorioJpa,Mockito.times(1)).findById(1L);
    }

    @Test
    public void debeBorrar(){
        Mockito.when(marcadorRepositorioJpa.findById(1L)).thenReturn(Optional.of(marcadorEntidad));
        marcadorServicio.borrar(1L);
        Mockito.verify(marcadorRepositorioJpa,Mockito.times(1)).findById(1L);
    }


}
