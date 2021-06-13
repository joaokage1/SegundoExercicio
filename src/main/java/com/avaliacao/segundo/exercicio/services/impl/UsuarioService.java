package com.avaliacao.segundo.exercicio.services.impl;

import com.avaliacao.segundo.exercicio.models.Status;
import com.avaliacao.segundo.exercicio.models.Usuario;
import com.avaliacao.segundo.exercicio.models.request.UsuarioRequest;
import com.avaliacao.segundo.exercicio.models.response.UsuarioResponse;
import com.avaliacao.segundo.exercicio.repositories.IUsuarioRepository;
import com.avaliacao.segundo.exercicio.services.IUsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Data
@AllArgsConstructor
public class UsuarioService implements IUsuarioService {

    private final IUsuarioRepository repository;
    private final AuthenticationManager authenticationManager;

    @Override
    public UsuarioResponse cadastrarUsuario(UsuarioRequest request) {
        UsuarioResponse response = new UsuarioResponse();

        if (request == null
                || request.getUsuario() == null
                || request.getUsuario().getEmail() == null
                || request.getUsuario().getNome() == null
                || request.getUsuario().getSenha() == null){
            applyErrorMessage(Status.VALIDATION_ERROR,response,"Verifique os dados do usuários a ser cadastrado");
            return response;
        }

        Usuario usuario = request.getUsuario();
        usuario.setNome(request.getUsuario().getNome().trim());
        usuario.setSenha((request.getUsuario().getSenha()));//TODO codificar senha

        if (getRepository().findByEmail(usuario.getEmail()).isPresent()){
            applyErrorMessage(Status.FAIL,response,"Email já cadastrado");
            return response;
        }

        try{
            usuario = getRepository().save(usuario);
        } catch (Exception e){
            applyErrorMessage(Status.VALIDATION_ERROR,response,"Usuario nao cadastrado");
        }

        response.getMessages().add("Usuario cadastrado com sucesso");
        response.setNome(usuario.getNome());
        return response;
    }

    @Override
    public UsuarioResponse entrar(UsuarioRequest request) {
        UsuarioResponse response = new UsuarioResponse();

        if (request == null
                || request.getUsuario() == null
                || request.getUsuario().getSenha() == null){
            applyErrorMessage(Status.VALIDATION_ERROR,response,"Verifique os dados do usuários para entrar");
            return response;
        }
        Optional<Usuario> usuarioOptional = getRepository().findByEmail(request.getUsuario().getEmail());
        if (usuarioOptional == null || !usuarioOptional.isPresent()){
            applyErrorMessage(Status.FAIL,response,"Usuário não existe");
            return response;
        }

        Usuario usuario = usuarioOptional.get();
        if (!usuario.getSenha().equals(request.getUsuario().getSenha())){
            applyErrorMessage(Status.FAIL, response,"Senha errada");
            return response;
        }

        String token = getJWTToken(usuario.getEmail());

        response = new UsuarioResponse(usuario.getNome(),token);

        return response;
    }

    private void applyErrorMessage(Status status, UsuarioResponse response, String message) {
        response.setStatus(status);
        response.getMessages().add(message);
    }

    private String getJWTToken(String nome) {
        String secretKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.e30.Et9HFtf9R3GEMA0IICOfFMVXY7kkTX1wr4qCyhIf58U";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_ADMIN");

        //TODO: Criar RefreshToken
        String token = Jwts
                .builder()
                .setId("navitaId")
                .setSubject(nome)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS256,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
