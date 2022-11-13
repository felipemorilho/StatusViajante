package com.empiricus.statusviajante.repository;

import com.empiricus.statusviajante.model.GastoViagemModel;
import com.empiricus.statusviajante.model.ViagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ViagemRepository extends JpaRepository<ViagemModel, Long> {

    public Optional<ViagemModel> findById(Long idViagem);

    public List<ViagemModel> findByUsuario_idUsuario(Long idViagem);
}