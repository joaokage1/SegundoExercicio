package com.avaliacao.segundo.exercicio.repositories;

import com.avaliacao.segundo.exercicio.models.Patrimonio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatrimonioRepository extends JpaRepository<Patrimonio, Long> {
}
