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
    @Min(1)
    private long id;
    @NotBlank(message = "O nome do astronauta não pode ser vazio.")
    private String nome;
    @NotBlank(message = "O país do astronauta não pode ser vazio.")
    private String pais;
    @NotNull(message = "A idade do astronauta precisa ser especificada.")
    private int idade;
    @NotNull(message = "Os anos de experiência do astronauta precisam ser especificada.")
    private int anosExperiencia;
    @NotNull(message = "O status do astronauta precisa ser especificado.")
    private StatusAstronauta status;
}
