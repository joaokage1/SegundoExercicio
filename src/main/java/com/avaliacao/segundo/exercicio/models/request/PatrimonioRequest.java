package com.avaliacao.segundo.exercicio.models.request;

import com.avaliacao.segundo.exercicio.models.Patrimonio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatrimonioRequest {

    private Patrimonio patrimonio;
}
