package br.com.infnet.missioncontrol.service;

import br.com.infnet.missioncontrol.model.Astronauta;
import br.com.infnet.missioncontrol.model.StatusAstronauta;
import br.com.infnet.missioncontrol.model.ValidationResponse;
import jakarta.validation.Validation;
import jakarta.validation.constraints.NotNull;
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
        cadastrarAstronauta(new Astronauta(1, "Ana Silva", "Brasil", 34, 8, StatusAstronauta.DISPONIVEL));
        cadastrarAstronauta(new Astronauta(2, "John Carter", "Estados Unidos", 41, 15, StatusAstronauta.EM_MISSAO));
        cadastrarAstronauta(new Astronauta(3, "Mei Lin", "China", 29, 5, StatusAstronauta.DISPONIVEL));
    }

    public ValidationResponse validarAstronauta(@NotNull Astronauta astronauta, boolean estaCadastrando) {
        Collection<String> messages = new ArrayList<>();
        boolean isOk = true;

        if (astronauta.getId() < 1 && !estaCadastrando) {
            messages.add("O id do astronauta não pode ser menor que um.");
            isOk = false;
        }

        if (astronauta.getIdade() < 18) {
            messages.add("A idade do astronauta não pode ser menor que 18 anos.");
            isOk = false;
        }

        if (astronauta.getNome() == null ||
                astronauta.getNome().trim().isEmpty()) {
            messages.add("O nome do astronauta não deve ser nulo ou vazio.");
            isOk = false;
        }

        if (astronauta.getPais() == null ||
                astronauta.getPais().trim().isEmpty()) {
            messages.add("O país do astronauta não deve ser nulo ou vazio.");
            isOk = false;
        }

        if (astronauta.getAnosExperiencia() < 0) {
            messages.add("Os anos de experiência do astronauta não podem ser negativos");
            isOk = false;
        }

        if (verificarAstronautaComMesmoNome(astronauta) && !estaCadastrando) {
            messages.add("Não se pode criar um astronauta com mesmo nome que o outro");
            isOk = false;
        }

        return new ValidationResponse(messages, isOk);
    }

    public void cadastrarAstronauta(@NonNull Astronauta astronauta) {
        var id = astronautas.size() + 1;

        astronauta.setId(id);

        astronautas.add(astronauta);
    }

    public boolean verificarAstronautaComMesmoNome(Astronauta astronautaAnalisado) {
        for (var astronauta : astronautas) {
            if (!astronauta.getNome().equals(astronautaAnalisado.getNome()))
                continue;

            if (astronauta.getId() != astronautaAnalisado.getId())
                return true;
        }
        return false;
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
