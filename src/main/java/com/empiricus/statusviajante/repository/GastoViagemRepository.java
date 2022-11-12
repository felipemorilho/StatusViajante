package com.empiricus.statusviajante.repository;

import com.empiricus.statusviajante.model.GastoViagem;
import com.empiricus.statusviajante.model.ViagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GastoViagemRepository extends JpaRepository<GastoViagem, Long> {
    public Optional<GastoViagem> findById(Long idGastoViagem);
    public List<GastoViagem> findByViagem_idViagem(Long idViagem);
}
