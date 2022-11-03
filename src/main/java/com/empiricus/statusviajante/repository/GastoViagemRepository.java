package com.empiricus.statusviajante.repository;

import com.empiricus.statusviajante.model.GastoViagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GastoViagemRepository extends JpaRepository<GastoViagem, Long> {

}
