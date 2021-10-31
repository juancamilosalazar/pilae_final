package test.java.com.example.multimodule.servicio.negocio;

import com.example.multimodule.entidad.EquipoEntidad;
import com.example.multimodule.entidad.TorneoEntidad;
import com.example.multimodule.infraestructura.equipo.EquipoRepositorioJpa;
import com.example.multimodule.infraestructura.posicion.PosicionRepositorioJpa;
import com.example.multimodule.infraestructura.torneo.TorneoRepositorioJpa;
import com.example.multimodule.servicio.negocio.impl.EquipoServicioImpl;
import main.com.example.multimodule.dominio.EquipoDominio;
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
public class EquipoServicioImplTest {

    @Mock
    EquipoRepositorioJpa equipoRepositorio;
    @Mock
    TorneoRepositorioJpa torneoRepositorio;
    @Mock
    PosicionRepositorioJpa posicionRepositorioJpa;

    EquipoEntidad equipoEntidad = new EquipoEntidad();
    EquipoDominio equipoDominio = new EquipoDominio();
    TorneoEntidad torneoEntidad = new TorneoEntidad();


    @InjectMocks
    EquipoServicioImpl equipoServicio;

    @Before
    public void setUp() {
        equipoEntidad.setCodigo(1);
        equipoEntidad.setNombre("equipo1");
        equipoDominio.setCodigo(1L);
        equipoDominio.setNombre("equipo1");
        equipoEntidad.setFkTorneo(new TorneoEntidad());
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnObtenerTodos() {
        List<EquipoEntidad> equipos = new ArrayList<>();
        Mockito.when(equipoRepositorio.findAll()).thenReturn(equipos);
        equipoServicio.obtenerTodos();
    }

    @Test
    public void debeRetornarEquipoDominio() {

        List<EquipoEntidad> equipoEntidadList = new ArrayList<>();
        equipoEntidadList.add(equipoEntidad);

        Mockito.when(equipoRepositorio.findAll()).thenReturn(equipoEntidadList);

        List<EquipoDominio> equipoDominio = equipoServicio.obtenerTodos();
        Mockito.verify(equipoRepositorio, Mockito.times(1)).findAll();
        Assert.assertFalse(equipoDominio.isEmpty());
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnObtenerPorId() {
        equipoServicio.obtenerPorId(1L);
    }

    @Test
    public void debeObtenerEquipoPorId() {
        Mockito.when(equipoRepositorio.findById(1L)).thenReturn(Optional.of(equipoEntidad));
        equipoServicio.obtenerPorId(1L);
        Mockito.verify(equipoRepositorio, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnCrear() {
        equipoServicio.crear(null,1L);
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnCrearPorEquipoExistente() {
        Mockito.when(equipoRepositorio.findById(1L)).thenReturn(Optional.of(equipoEntidad));
        equipoServicio.crear(equipoDominio,1L);
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarExcepcionPorTorneo() {
        Mockito.when(equipoRepositorio.findById(1L)).thenReturn(Optional.empty());
        equipoServicio.crear(equipoDominio,1L);
    }

    @Test
    public void debeCrearTorneo() {
        Mockito.when(equipoRepositorio.findById(1L)).thenReturn(Optional.empty());
        Mockito.when(torneoRepositorio.findById(1L)).thenReturn(Optional.of(torneoEntidad));
        equipoServicio.crear(equipoDominio,1L);
        Mockito.verify(equipoRepositorio,Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(torneoRepositorio,Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLevantarExcepcionEnActualizar() {
        equipoServicio.actualizar(null);
    }

    @Test
    public void debeLanzarPilaeExcepcionEnActualizar() {
        Mockito.when(equipoRepositorio.findById(1L)).thenReturn(Optional.of(equipoEntidad));
        equipoServicio.actualizar(equipoDominio);
        Mockito.verify(equipoRepositorio, Mockito.times(1)).findById(1L);
    }
    @Test
    public void debeActualizar() {
        Mockito.when(equipoRepositorio.findById(1L)).thenReturn(Optional.of(equipoEntidad));
        equipoServicio.actualizar(equipoDominio);
        Mockito.verify(equipoRepositorio, Mockito.times(1)).findById(1L);
    }

    @Test
    public void debeBorrar() {
        Mockito.when(equipoRepositorio.findById(1L)).thenReturn(Optional.of(equipoEntidad));
        equipoServicio.borrar(1L);
        Mockito.verify(equipoRepositorio, Mockito.times(1)).findById(1L);
    }
}
