package com.avaliacao.segundo.exercicio.controllers;

import com.avaliacao.segundo.exercicio.models.request.UsuarioRequest;
import com.avaliacao.segundo.exercicio.models.response.UsuarioResponse;
import com.avaliacao.segundo.exercicio.services.IUsuarioService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/usuario")
@AllArgsConstructor
@Data
public class UsuarioController {

    private final IUsuarioService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody UsuarioRequest request){
        return ResponseEntity.ok(getService().cadastrarUsuario(request));
    }

    @PostMapping("/entrar")
    public ResponseEntity<UsuarioResponse> entrar(@RequestBody UsuarioRequest request){
        return ResponseEntity.ok(getService().entrar(request));
    }

    @GetMapping(value = "/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null){
            String tokenValue = authHeader.replace("Bearer", "").trim();
        }
        return ResponseEntity.noContent().build();
    }
}
