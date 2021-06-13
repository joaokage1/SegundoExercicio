package com.avaliacao.segundo.exercicio.models.response;

import com.avaliacao.segundo.exercicio.models.Patrimonio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatrimonioResponse extends BaseResponse{

    private List<Patrimonio> patrimonios;
}
