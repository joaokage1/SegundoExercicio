package com.avaliacao.segundo.exercicio.models.request;


import com.avaliacao.segundo.exercicio.models.Marca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcaRequest {

    private Marca marca;
}
