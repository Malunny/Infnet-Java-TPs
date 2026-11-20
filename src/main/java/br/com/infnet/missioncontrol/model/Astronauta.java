package br.com.infnet.missioncontrol.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Astronauta {
    private long id;
    @NotBlank(message = "O nome do astronauta não pode ser vazio.")
    private String nome;
    @NotBlank(message = "O país do astronauta não pode ser vazio.")
    private String pais;
    @Min(18)
    @NotNull(message = "A idade do astronauta precisa ser especificada.")
    private int idade;
    @Min(0)
    @NotNull(message = "Os anos de experiência do astronauta precisam ser especificada.")
    private int anosExperiencia;
    @NotNull(message = "O status do astronauta precisa ser especificado.")
    private StatusAstronauta status;

    public void atualizarDados(Astronauta astronauta) {
        nome = astronauta.nome;
        pais = astronauta.pais;
        idade = astronauta.idade;
        anosExperiencia = astronauta.anosExperiencia;
        status = astronauta.status;
    }

    public Astronauta() {

    }

    public Astronauta(int id, String nome, String pais, int idade, int anosExperiencia, StatusAstronauta status) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
        this.idade = idade;
        this.anosExperiencia = anosExperiencia;
        this.status = status;
    }
}
