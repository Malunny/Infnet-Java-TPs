package br.com.infnet.missioncontrol.service;

import br.com.infnet.missioncontrol.model.Astronauta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class AstronautaService {
    private Collection<Astronauta> astronautas = new ArrayList<>();

    public void cadastrarAstronauta(Astronauta astronauta) {
        astronautas.add(astronauta);
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

    public Boolean deletarPorId(long id) {
        var astronauta = pegarPorId(id);

        if (astronauta == null)
            return false;

        astronautas.remove(astronauta);

        return true;
    }
}
