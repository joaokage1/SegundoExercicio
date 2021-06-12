package com.avaliacao.segundo.exercicio.repositories;

import com.avaliacao.segundo.exercicio.models.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Marca, Long> {
}
