package com.avaliacao.segundo.exercicio.services;

import com.avaliacao.segundo.exercicio.models.request.MarcaRequest;
import com.avaliacao.segundo.exercicio.models.response.MarcaResponse;

public interface IMarcaService {
    public MarcaResponse buscarTodasAsMarcas();
    public MarcaResponse buscarMarcaPorId(MarcaRequest request);
    public MarcaResponse inserirMarca(MarcaRequest request);
    public MarcaResponse apagarMarcaPeloId(MarcaRequest request);
    public MarcaResponse editarMarca(MarcaRequest request);
}
