package com.avaliacao.segundo.exercicio.models.response;

import com.avaliacao.segundo.exercicio.models.Marca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcaResponse extends BaseResponse{

    private List<Marca> marcas;
}
