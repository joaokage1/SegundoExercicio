package com.avaliacao.segundo.exercicio.services.impl;

import com.avaliacao.segundo.exercicio.models.Marca;
import com.avaliacao.segundo.exercicio.models.Patrimonio;
import com.avaliacao.segundo.exercicio.models.Status;
import com.avaliacao.segundo.exercicio.models.request.MarcaRequest;
import com.avaliacao.segundo.exercicio.models.request.PatrimonioRequest;
import com.avaliacao.segundo.exercicio.models.response.MarcaResponse;
import com.avaliacao.segundo.exercicio.models.response.PatrimonioResponse;
import com.avaliacao.segundo.exercicio.repositories.IPatrimonioRepository;
import com.avaliacao.segundo.exercicio.services.IMarcaService;
import com.avaliacao.segundo.exercicio.services.IPatrimonioService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Data
@AllArgsConstructor
public class PatrimonioService implements IPatrimonioService, Serializable {

    private final IPatrimonioRepository repository;
    private final IMarcaService marcaService;

    @Override
    public PatrimonioResponse insertPatrimonio(PatrimonioRequest request) {
        PatrimonioResponse response = new PatrimonioResponse();
        
        if (request.getPatrimonio() == null || request.getPatrimonio().getNome() == null || request.getPatrimonio().getMarcaId() == null){
            applyErrorMessage(Status.VALIDATION_ERROR, response, "Certifique-se de que todos os campos para patrimonio estão presentes");
            return response;
        }
        MarcaResponse marcaResponse = buscarMarcaResponse(request);

        if (marcaResponse.getMarcas() == null || marcaResponse.getMarcas().isEmpty()){
            applyErrorMessage(Status.VALIDATION_ERROR, response, "Certifique-se de que a marca do patrimonio existe");
            response.getMessages().addAll(marcaResponse.getMessages());
            return response;
        }

        Patrimonio patrimonio = getRepository().save(request.getPatrimonio());
        response.setPatrimonios(Arrays.asList(patrimonio));
        return response;
    }

    @Override
    public PatrimonioResponse buscarTodos() {
        PatrimonioResponse response = new PatrimonioResponse();
        List<Patrimonio> patrimonios = getRepository().findAll();

        if (patrimonios == null || patrimonios.isEmpty()){
            response.getMessages().add("Não existem patrimonios cadastrados");
            return response;
        }
        response.setPatrimonios(patrimonios);

        return response;
    }

    @Override
    public PatrimonioResponse buscarPorNoTombo(PatrimonioRequest request) {
        PatrimonioResponse response = new PatrimonioResponse();

        if (request.getPatrimonio() == null || request.getPatrimonio().getNTombo() == null ){
            applyErrorMessage(Status.VALIDATION_ERROR,response,"Certifique-se de que todos os campos para patrimonio estão presentes");
            return response;
        }

        Optional<Patrimonio> patrimonioOptional = getRepository().findById(request.getPatrimonio().getNTombo());
        if (!patrimonioOptional.isPresent()){
            applyErrorMessage(Status.FAIL,response,"Patrimonio requisitado não existe");
            return response;
        }

        response.setPatrimonios(Arrays.asList(patrimonioOptional.get()));
        return response;
    }

    @Override
    public PatrimonioResponse buscarPorMarcaId(PatrimonioRequest request) {
        PatrimonioResponse response = new PatrimonioResponse();

        if (request.getPatrimonio() == null || request.getPatrimonio().getMarcaId() == null ){
            applyErrorMessage(Status.VALIDATION_ERROR,response,"Certifique-se de que todos os campos para patrimonio estão presentes");
            return response;
        }
        MarcaResponse marcaResponse = buscarMarcaResponse(request);

        if (marcaResponse.getMarcas() == null || marcaResponse.getMarcas().isEmpty()){
            applyErrorMessage(Status.VALIDATION_ERROR, response, "Certifique-se de que a marca do patrimonio existe");
            response.getMessages().addAll(marcaResponse.getMessages());
            return response;
        }

        List<Optional<Patrimonio>> patrimonioOptionalList = getRepository().findByMarcaId(request.getPatrimonio().getMarcaId());
        if (patrimonioOptionalList == null || patrimonioOptionalList.isEmpty()){
            applyErrorMessage(Status.FAIL,response,"Patrimonios requisitados não existem");
            return response;
        }

        response.setPatrimonios(patrimonioOptionalList.stream().map(p -> p.get()).collect(Collectors.toList()));
        return response;
    }

    @Override
    public PatrimonioResponse apagarPatrimonioPeloNoTombo(PatrimonioRequest request) {
        PatrimonioResponse response = new PatrimonioResponse();

        if (request.getPatrimonio() == null || request.getPatrimonio().getNTombo() == null ){
            applyErrorMessage(Status.VALIDATION_ERROR,response,"Certifique-se de que todos os campos de patrimonio para ser removido estão presentes");
            return response;
        }

        response = buscarPorNoTombo(request);
        if (!response.getStatus().equals(Status.SUCCESS)){
            applyErrorMessage(Status.VALIDATION_ERROR,response,"Patrimonio requisitado para ser removido não existe");
            return response;
        }

        getRepository().deleteById(request.getPatrimonio().getNTombo());
        response.setPatrimonios(Arrays.asList(response.getPatrimonios().get(0)));
        applyErrorMessage(Status.SUCCESS,response,"Patrimonio removido");

        return response;
    }

    @Override
    public PatrimonioResponse editarPatrimonio(PatrimonioRequest request) {
        PatrimonioResponse response = new PatrimonioResponse();

        if (request.getPatrimonio() == null
                || request.getPatrimonio().getNTombo() == null
                || request.getPatrimonio().getNome() == null
                || request.getPatrimonio().getMarcaId() == null){
            applyErrorMessage(Status.VALIDATION_ERROR,response,"Certifique-se de que todos os campos de patrimonio para ser editado estão presentes");
            return response;
        }

        response = buscarPorNoTombo(request);
        if (!response.getStatus().equals(Status.SUCCESS)){
            applyErrorMessage(Status.VALIDATION_ERROR,response,"Patrimonio requisitado para ser editado não existe");
            return response;
        }

        MarcaResponse marcaResponse = buscarMarcaResponse(request);

        if (marcaResponse.getMarcas() == null || marcaResponse.getMarcas().isEmpty()){
            applyErrorMessage(Status.VALIDATION_ERROR, response, "Certifique-se de que a marca do patrimonio existe");
            response.getMessages().addAll(marcaResponse.getMessages());
            return response;
        }

        response.setPatrimonios(Arrays.asList(getRepository().save(request.getPatrimonio())));

        return response;
    }

    private void applyErrorMessage(Status status, PatrimonioResponse response, String message) {
        response.setStatus(status);
        response.getMessages().add(message);
    }

    private MarcaResponse buscarMarcaResponse(PatrimonioRequest request) {
        Marca marca = new Marca();
        marca.setMarcaId(request.getPatrimonio().getMarcaId());
        MarcaRequest marcaRequest = new MarcaRequest();
        marcaRequest.setMarca(marca);
        MarcaResponse marcaResponse = getMarcaService().buscarMarcaPorId(marcaRequest);
        return marcaResponse;
    }
}
