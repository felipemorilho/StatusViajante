package com.empiricus.statusviajante.repository;

import com.empiricus.statusviajante.model.CategoriaViagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaViagemRepository extends JpaRepository<CategoriaViagem, Long> {

    public List<CategoriaViagem> findAllByNomeContainingIgnoreCase (String nome);
}
