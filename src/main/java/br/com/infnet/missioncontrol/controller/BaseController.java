package br.com.infnet.missioncontrol.controller;

import br.com.infnet.missioncontrol.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    public boolean validarId(long id) {
        var isLowerThanOne = id < 1;

        return !(isLowerThanOne);
    }

    public ResponseEntity respostaDeIdInvalido() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse("O id dado está inválido. Cheque se ele é 1 ou um número maior."));
    }
}
