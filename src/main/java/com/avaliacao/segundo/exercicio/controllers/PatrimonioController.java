package com.avaliacao.segundo.exercicio.controllers;

import com.avaliacao.segundo.exercicio.models.request.PatrimonioRequest;
import com.avaliacao.segundo.exercicio.services.IPatrimonioService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patrimonio")
@AllArgsConstructor
@Data
public class PatrimonioController {

    private final IPatrimonioService patrimonioService;

    @PostMapping()
    public ResponseEntity<Void> insertPatrimonio(@RequestBody PatrimonioRequest request) {
        getPatrimonioService().insertPatrimonio(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
