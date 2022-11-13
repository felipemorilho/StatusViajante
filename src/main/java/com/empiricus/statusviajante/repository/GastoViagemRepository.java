package com.empiricus.statusviajante.repository;


import com.empiricus.statusviajante.model.GastoViagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GastoViagemRepository extends JpaRepository<GastoViagemModel, Long> {
    public Optional<GastoViagemModel> findById(Long idGastoViagem);
    public List<GastoViagemModel> findByViagem_idViagem(Long idViagem);
}


