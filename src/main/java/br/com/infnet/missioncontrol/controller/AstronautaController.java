package br.com.infnet.missioncontrol.controller;

import br.com.infnet.missioncontrol.model.Astronauta;
import br.com.infnet.missioncontrol.service.AstronautaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/astronautas")
public class AstronautaController {
    private final AstronautaService astronautaService;
    public AstronautaController(AstronautaService astronautaService) {
        this.astronautaService = astronautaService;
    }

    @PostMapping
    public void cadastrarAstronauta(@Valid @RequestBody Astronauta astronauta) {
        astronautaService.cadastrarAstronauta(astronauta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable long id) {
        var deletou = astronautaService.deletarPorId(id);

        if (deletou)
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity atualizarPorId(@Valid @RequestBody Astronauta astronauta) {
        var atualizado = astronautaService.atualizarAstronauta(astronauta);

        if (!atualizado)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity pegarTodos() {
        var astronautas = astronautaService.pegarTodos();

        return ResponseEntity.ok(astronautas);
    }

    @GetMapping("/{id}")
    public ResponseEntity pegarPorId(@PathVariable long id) {
        var astronauta = astronautaService.pegarPorId(id);

        if (astronauta == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(astronauta);
    }
}
