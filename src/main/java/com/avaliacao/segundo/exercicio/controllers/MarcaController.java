package com.avaliacao.segundo.exercicio.controllers;

import com.avaliacao.segundo.exercicio.models.request.MarcaRequest;
import com.avaliacao.segundo.exercicio.models.response.MarcaResponse;
import com.avaliacao.segundo.exercicio.services.IMarcaService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/marcas")
@AllArgsConstructor
@Data
public class MarcaController {

    private final IMarcaService service;

    @PostMapping
    public ResponseEntity<MarcaResponse> inserirMarca(@RequestBody MarcaRequest request){
        MarcaResponse response = getService().inserirMarca(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<MarcaResponse> buscarTodasAsMarcas(){
        MarcaResponse response = getService().buscarTodasAsMarcas();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/id")
    public ResponseEntity<MarcaResponse> buscarMarcaPorId(@RequestBody MarcaRequest request){
        MarcaResponse response = getService().buscarMarcaPorId(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<MarcaResponse> deletarMarcaPorId(@RequestBody MarcaRequest request){
        MarcaResponse response = getService().apagarMarcaPeloId(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<MarcaResponse> editarMarca(@RequestBody MarcaRequest request){
        MarcaResponse response = getService().editarMarca(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
