package com.avaliacao.segundo.exercicio.repositories;

import com.avaliacao.segundo.exercicio.models.Patrimonio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPatrimonioRepository extends JpaRepository<Patrimonio, Long> {

    List<Optional<Patrimonio>> findByMarcaId(Long marcaId);
}
