package br.com.infnet.missioncontrol.controller;

import br.com.infnet.missioncontrol.model.Astronauta;
import br.com.infnet.missioncontrol.model.ErrorResponse;
import br.com.infnet.missioncontrol.model.StatusAstronauta;
import br.com.infnet.missioncontrol.service.AstronautaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/astronautas")
public class AstronautaController extends BaseController {
    private final AstronautaService astronautaService;
    public AstronautaController(AstronautaService astronautaService) {
        this.astronautaService = astronautaService;
    }

    @PostMapping
    public ResponseEntity cadastrarAstronauta(@RequestBody Astronauta astronauta) {
        var validacaoAstronauta = astronautaService.validarAstronauta(astronauta, true);

        if (!validacaoAstronauta.isOk())
            return ResponseEntity.badRequest().body(validacaoAstronauta);

        astronautaService.cadastrarAstronauta(astronauta);

        return ResponseEntity.ok(astronauta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable long id) {
        var idValido = validarId(id);

        if (!idValido)
            return respostaDeIdInvalido();

        var deletou = astronautaService.deletarPorId(id);

        if (!deletou)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Não encontrado astronauta com tal id."));

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity atualizarPorId(@RequestBody Astronauta astronauta) {
        var validacaoAstronauta = astronautaService.validarAstronauta(astronauta, false);

        if (!validacaoAstronauta.isOk())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(validacaoAstronauta);

        var atualizado = astronautaService.atualizarAstronauta(astronauta);

        if (!atualizado)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().build();
    }

    @GetMapping(params = "status")
    public ResponseEntity pegarFiltradoPorStatus(@RequestParam(name = "status", required = true) StatusAstronauta statusAstronauta) {
        var astronautas = astronautaService.pegarFiltradoPorStatus(statusAstronauta);

        return ResponseEntity.ok(astronautas);
    }

    @GetMapping
    public ResponseEntity pegarTodos() {
        var astronautas = astronautaService.pegarTodos();

        return ResponseEntity.ok(astronautas);
    }

    @GetMapping("/{id}")
    public ResponseEntity pegarPorId(@PathVariable long id) {
        var idValido = validarId(id);

        if (!idValido)
            return respostaDeIdInvalido();

        var astronauta = astronautaService.pegarPorId(id);

        if (astronauta == null)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Não encontrado astronauta com tal id."));

        return ResponseEntity.ok(astronauta);
    }
}