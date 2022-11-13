package com.empiricus.statusviajante.repository;

import com.empiricus.statusviajante.model.CategoriaGastoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaGastoRepository extends JpaRepository<CategoriaGastoModel, Long> {

    public List<CategoriaGastoModel> findAllByNomeContainingIgnoreCase (String nome);
}
