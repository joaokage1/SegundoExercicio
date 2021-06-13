package com.avaliacao.segundo.exercicio.services.impl;

import com.avaliacao.segundo.exercicio.models.Marca;
import com.avaliacao.segundo.exercicio.models.Status;
import com.avaliacao.segundo.exercicio.models.request.MarcaRequest;
import com.avaliacao.segundo.exercicio.models.response.MarcaResponse;
import com.avaliacao.segundo.exercicio.repositories.IMarcaRepository;
import com.avaliacao.segundo.exercicio.services.IMarcaService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Data
@AllArgsConstructor
public class MarcaService implements IMarcaService {

    private final IMarcaRepository repository;

    @Override
    public MarcaResponse buscarTodasAsMarcas() {
        MarcaResponse response = new MarcaResponse();
        List<Marca> marcas = getRepository().findAll();

        if (marcas == null || marcas.isEmpty()){
            response.getMessages().add("Não existem marcas cadastradas");
            return response;
        }
        response.setMarcas(marcas);

        return response;
    }

    @Override
    public MarcaResponse buscarMarcaPorId(MarcaRequest request) {
        MarcaResponse response = new MarcaResponse();

        if (request.getMarca() == null || request.getMarca().getMarcaId() == null ){
            applyErrorMessage(Status.VALIDATION_ERROR,response,"Marca deve existir com um id valid");
            return response;
        }

        Optional<Marca> marcaOptional = getRepository().findById(request.getMarca().getMarcaId());
        if (!marcaOptional.isPresent()){
            applyErrorMessage(Status.FAIL,response,"Marca requisitada não existe");
            return response;
        }

        response.setMarcas(Arrays.asList(marcaOptional.get()));
        return response;
    }

    @Override
    public MarcaResponse inserirMarca(MarcaRequest request) {
        MarcaResponse response = new MarcaResponse();

        if (request.getMarca() == null || request.getMarca().getNome() == null || request.getMarca().getNome().isEmpty()){
            applyErrorMessage(Status.VALIDATION_ERROR,response,"Marca deve existir com um nome");
            return response;
        }

        if (getRepository().findByNome(request.getMarca().getNome().trim()).isPresent()){
            applyErrorMessage(Status.FAIL,response,"Já existe uma marca com esse nome");
            return response;
        }
        //Removendo inicio e final com espaço, não ter problema com inserir o mesmo nome
        request.getMarca().setNome(request.getMarca().getNome().trim());

        Marca marca = getRepository().save(request.getMarca());
        response.setMarcas(Arrays.asList(marca));

        return response;
    }

    @Override
    public MarcaResponse apagarMarcaPeloId(MarcaRequest request) {
        MarcaResponse response = new MarcaResponse();

        if (request.getMarca() == null || request.getMarca().getMarcaId() == null ){
            applyErrorMessage(Status.VALIDATION_ERROR,response,"Marca deve existir com um id valido para ser removida");
            return response;
        }

        Optional<Marca> marcaOptional = getRepository().findById(request.getMarca().getMarcaId());
        if (!marcaOptional.isPresent()){
            applyErrorMessage(Status.VALIDATION_ERROR,response,"Marca requisitada para ser removida não existe");
            return response;
        }

        getRepository().deleteById(request.getMarca().getMarcaId());
        response.setMarcas(Arrays.asList(marcaOptional.get()));
        applyErrorMessage(Status.SUCCESS,response,"Marca removida");

        return response;
    }

    @Override
    public MarcaResponse editarMarca(MarcaRequest request) {
        MarcaResponse response = new MarcaResponse();

        if (request.getMarca() == null || request.getMarca().getMarcaId() == null ){
            applyErrorMessage(Status.VALIDATION_ERROR,response,"Marca deve existir com um id valido para ser editada");
            return response;
        }

        Optional<Marca> marcaOptional = getRepository().findById(request.getMarca().getMarcaId());
        if (!marcaOptional.isPresent()){
            applyErrorMessage(Status.VALIDATION_ERROR,response,"Marca requisitada para ser editada não existe");
            return response;
        }

        response.setMarcas(Arrays.asList(getRepository().save(request.getMarca())));

        return response;
    }

    private void applyErrorMessage(Status status, MarcaResponse response, String message) {
        response.setStatus(status);
        response.getMessages().add(message);
    }
}
