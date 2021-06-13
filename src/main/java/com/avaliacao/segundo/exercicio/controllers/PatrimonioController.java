package com.avaliacao.segundo.exercicio.controllers;

import com.avaliacao.segundo.exercicio.models.request.PatrimonioRequest;
import com.avaliacao.segundo.exercicio.models.response.PatrimonioResponse;
import com.avaliacao.segundo.exercicio.services.IPatrimonioService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patrimonios")
@AllArgsConstructor
@Data
public class PatrimonioController {

    private final IPatrimonioService patrimonioService;

    @PostMapping()
    public ResponseEntity<PatrimonioResponse> insertPatrimonio(@RequestBody PatrimonioRequest request) {
        return ResponseEntity.ok(getPatrimonioService().insertPatrimonio(request));
    }

    @GetMapping
    public ResponseEntity<PatrimonioResponse> buscarTodos() {
        return ResponseEntity.ok(getPatrimonioService().buscarTodos());
    }

    @GetMapping("/marcaId")
    public ResponseEntity<PatrimonioResponse> buscarPatrimonioPorMarca(@RequestBody PatrimonioRequest request) {
        return ResponseEntity.ok(getPatrimonioService().buscarPorMarcaId(request));
    }

    @GetMapping("/tombo")
    public ResponseEntity<PatrimonioResponse> buscarPatrimonioPorNoTombo(@RequestBody PatrimonioRequest request) {
        return ResponseEntity.ok(getPatrimonioService().buscarPorNoTombo(request));
    }

    @DeleteMapping
    public ResponseEntity<PatrimonioResponse> apagarPatrimonioPorNoTombo(@RequestBody PatrimonioRequest request) {
        return ResponseEntity.ok(getPatrimonioService().apagarPatrimonioPeloNoTombo(request));
    }

    @PutMapping
    public ResponseEntity<PatrimonioResponse> editarPatrimonioPorNoTombo(@RequestBody PatrimonioRequest request) {
        return ResponseEntity.ok(getPatrimonioService().editarPatrimonio(request));
    }
}
