package co.edu.uco.mensajes.api.inicializador;

import co.edu.uco.mensajes.api.controlador.MensajeControlador;
import co.edu.uco.mensajes.dto.MensajeDTO;
import co.edu.uco.mensajes.negocio.MensajeNegocio;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class MensajesApiApplicationTests {

	@InjectMocks
	MensajeControlador mensajeControlador;

	@Mock
	MensajeNegocio mensajeNegocio;



	@Test
	public void debeConsultarMensajesAplicacion(){
		List<MensajeDTO> mensajeDTOList = new ArrayList<>();
		mensajeDTOList.add(new MensajeDTO());
		Mockito.when(mensajeNegocio.consultar(Mockito.any(MensajeDTO.class))).thenReturn(mensajeDTOList);
		mensajeControlador.consultarMensajesAplicacion("");
		Mockito.verify(mensajeNegocio,Mockito.times(0)).consultar(Mockito.any());
	}
}
