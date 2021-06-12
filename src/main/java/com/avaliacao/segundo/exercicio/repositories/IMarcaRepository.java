package com.avaliacao.segundo.exercicio.repositories;

import com.avaliacao.segundo.exercicio.models.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMarcaRepository extends JpaRepository<Marca, Long> {

    Optional<Marca> findByNome(String nome);
    void deleteById(Long id);
}
