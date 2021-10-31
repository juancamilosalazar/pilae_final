package test.java.com.example.multimodule.servicio.negocio;

import com.example.multimodule.entidad.PosicionEntidad;
import com.example.multimodule.infraestructura.posicion.PosicionRepositorioJpa;
import com.example.multimodule.servicio.negocio.impl.PosicionServicioImpl;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class PosicionServicioImplTest {
    @Mock
    PosicionRepositorioJpa posicionRepositorioJpa;

    @InjectMocks
    PosicionServicioImpl posicionServicio;

    @Test(expected = PILAEDominioExcepcion.class)
    public void debeLanzarPilaeExcepcionEnObtenerTodos(){
        List<PosicionEntidad> deportes = new ArrayList<>();
        Mockito.when(posicionRepositorioJpa.findAll()).thenReturn(deportes);
        posicionServicio.obtenerPorId(1L);
    }


}
