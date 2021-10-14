package com.example.multimodule.servicio.negocio.impl.utilitario;

import com.example.multimodule.entidad.EquipoEntidad;
import com.example.multimodule.entidad.PartidoEntidad;
import com.example.multimodule.entidad.TorneoEntidad;
import com.example.multimodule.infraestructura.partido.PartidoRepositorioJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class GeneracionFixtureIdaYVuelta {

    @Autowired
    private PartidoRepositorioJpa partidoRepository;

    private static Partido[][] calcularLigaNumEquiposPar(HashMap equipos) {
        int numRondas = equipos.size() - 1;
        int numPartidosPorRonda = equipos.size() / 2;

        Partido[][] rondas = new Partido[numRondas][numPartidosPorRonda];

        for (int i = 0, k = 0; i < numRondas; i++) {
            for (int j = 0; j < numPartidosPorRonda; j++) {
                rondas[i][j] = new Partido();
                rondas[i][j].local = k;
                k++;

                if (k == numRondas)
                    k = 0;
            }
        }

        for (int i = 0; i < numRondas; i++) {
            if (i % 2 == 0) {
                rondas[i][0].visitante = equipos.size() - 1;
            } else {
                rondas[i][0].visitante = rondas[i][0].local;
                rondas[i][0].local = equipos.size() - 1;
            }
        }

        int equipoMasAlto = equipos.size() - 1;
        int equipoImparMasAlto = equipoMasAlto - 1;

        for (int i = 0, k = equipoImparMasAlto; i < numRondas; i++) {
            for (int j = 1; j < numPartidosPorRonda; j++) {
                rondas[i][j].visitante = k;

                k--;

                if (k == -1)
                    k = equipoImparMasAlto;
            }
        }

        return rondas;
    }

    private static Partido[][] calcularLigaNumEquiposImpar(HashMap equipos) {
        int numRondas = equipos.size();
        int numPartidosPorRonda = equipos.size() / 2;

        Partido[][] rondas = new Partido[numRondas][numPartidosPorRonda];

        for (int i = 0, k = 0; i < numRondas; i++) {
            for (int j = -1; j < numPartidosPorRonda; j++) {
                if (j >= 0) {
                    rondas[i][j] = new Partido();
                    rondas[i][j].local = k;
                }

                k++;

                if (k == numRondas)
                    k = 0;
            }
        }

        int equipoMasAlto = equipos.size() - 1;

        for (int i = 0, k = equipoMasAlto; i < numRondas; i++) {
            for (int j = 0; j < numPartidosPorRonda; j++) {
                rondas[i][j].visitante = k;

                k--;

                if (k == -1)
                    k = equipoMasAlto;
            }
        }

        return rondas;
    }

    public Partido[][] calcularLiga(HashMap<Integer, EquipoEntidad> equipos) {

        if (equipos.size() % 2 == 0)
            return calcularLigaNumEquiposPar(equipos);
        else
            return calcularLigaNumEquiposImpar(equipos);
    }

    public List<PartidoEntidad> mostrarPartidos(Partido[][] rondas, HashMap<Integer, EquipoEntidad> equipos, TorneoEntidad id) {
        final Calendar calendar = Calendar.getInstance(Locale.getDefault());
        List<PartidoEntidad> partidoEntidades = new ArrayList<>();
        for (int i = 0; i < rondas.length; i++) {
            for (int j = 0; j < rondas[i].length; j++) {
                PartidoEntidad partidoEntidad = new PartidoEntidad();
                partidoEntidad.setRonda("Ronda " + (i + 1));
                partidoEntidad.setFechaDelpartido(calendar.getTime());
                partidoEntidad.setFkTorneo(id);
                partidoEntidad.setFkEquipoLocal(equipos.get(1 + rondas[i][j].local));
                partidoEntidad.setFkEquipoVisitante(equipos.get(1 + rondas[i][j].visitante));
                partidoEntidad.setIdaVuelta("ida");
                partidoEntidad.setEstadoPartido("sin jugar");
                partidoRepository.save(partidoEntidad);
                partidoEntidades.add(partidoEntidad);
            }


        }

        for (int i = 0; i < rondas.length; i++) {

            for (int j = 0; j < rondas[i].length; j++) {
                PartidoEntidad partidoEntidad = new PartidoEntidad();
                partidoEntidad.setRonda("Ronda " + (i + 1));
                partidoEntidad.setFechaDelpartido(calendar.getTime());
                partidoEntidad.setFkTorneo(id);
                partidoEntidad.setFkEquipoLocal(equipos.get(1 + rondas[i][j].visitante));
                partidoEntidad.setFkEquipoVisitante(equipos.get(1 + rondas[i][j].local));
                partidoEntidad.setIdaVuelta("vuelta");
                partidoEntidad.setEstadoPartido("sin jugar");
                partidoRepository.save(partidoEntidad);
                partidoEntidades.add(partidoEntidad);
            }


        }
        return partidoEntidades;
    }

    static public class Partido {
        public int local = -1, visitante = -1;
    }

}
