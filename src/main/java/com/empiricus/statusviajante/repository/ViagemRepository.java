package com.empiricus.statusviajante.repository;

import com.empiricus.statusviajante.model.ViagemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ViagemRepository extends JpaRepository<ViagemModel, Long> {
    public Optional<ViagemModel> findById(Long idViagem);

}