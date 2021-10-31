package test.com.example.multimodule.dominio;

import main.com.example.multimodule.dominio.EquipoDominio;
import main.com.example.multimodule.dominio.reglas.ReglaValidacion;
import main.com.example.multimodule.dominio.reglas.enumerador.ReglaEnum;
import main.com.example.multimodule.dominio.reglas.implementacion.EquipoDominioReglaValidacion;
import org.junit.Test;

public class EquipoDominioReglaValidacionTest {


    @Test
    public void debeValidarRegla(){
        EquipoDominio equipoDominio = new EquipoDominio();
        equipoDominio.setNombre("equipo");
        equipoDominio.setCodigo(1L);
        ReglaValidacion equipoDominioReglaValidacion = EquipoDominioReglaValidacion.crear(equipoDominio);
        equipoDominioReglaValidacion.validar(ReglaEnum.CREAR);
    }

}
