package com.avaliacao.segundo.exercicio.services;

import com.avaliacao.segundo.exercicio.models.request.PatrimonioRequest;
import com.avaliacao.segundo.exercicio.models.response.PatrimonioResponse;

public interface IPatrimonioService {

    public PatrimonioResponse insertPatrimonio(PatrimonioRequest request);
    public PatrimonioResponse buscarTodos();
    public PatrimonioResponse buscarPorNoTombo(PatrimonioRequest request);
    public PatrimonioResponse buscarPorMarcaId(PatrimonioRequest request);
    public PatrimonioResponse apagarPatrimonioPeloNoTombo(PatrimonioRequest request);
    public PatrimonioResponse editarPatrimonio(PatrimonioRequest request);
}
