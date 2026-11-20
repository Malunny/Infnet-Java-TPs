package br.com.infnet.missioncontrol.service;

import br.com.infnet.missioncontrol.model.Astronauta;
import br.com.infnet.missioncontrol.model.StatusAstronauta;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class AstronautaService {
    private Collection<Astronauta> astronautas;

    public AstronautaService() {
        this.astronautas = new ArrayList<>();
        this.astronautas.add(new Astronauta(1, "Jhonny", "Brasil", 19, 1, StatusAstronauta.DISPONIVEL));
        this.astronautas.add(new Astronauta(1, "Julian", "Peru", 23, 2, StatusAstronauta.EM_MISSAO));
    }

    public void cadastrarAstronauta(@NonNull Astronauta astronauta) {
        var id = astronautas.size() + 1;

        astronauta.setId(id);

        astronautas.add(astronauta);
    }

    public boolean atualizarAstronauta(@NonNull Astronauta astronauta) {
        var astronautaGuardado = pegarPorId(astronauta.getId());

        if (astronautaGuardado == null)
            return false;

        astronautaGuardado.atualizarDados(astronauta);
        return true;
    }

    public Collection<Astronauta> pegarTodos() {
        return astronautas;
    }

    public Astronauta pegarPorId(long id) {
        for (var astronauta : astronautas) {
            if (astronauta.getId() == id) {
                return astronauta;
            }
        }
        return null;
    }

    public Collection<Astronauta> pegarFiltradoPorStatus(StatusAstronauta statusAstronauta) {
        var astronautasFiltrados = astronautas
                .stream()
                .filter(astronauta -> astronauta.getStatus() == statusAstronauta)
                .toList();

        return astronautasFiltrados;
    }

    public Boolean deletarPorId(long id) {
        var astronauta = pegarPorId(id);

        if (astronauta == null)
            return false;

        astronautas.remove(astronauta);

        return true;
    }
}
