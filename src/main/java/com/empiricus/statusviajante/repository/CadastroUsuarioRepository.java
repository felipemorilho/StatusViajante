package com.empiricus.statusviajante.repository;

import com.empiricus.statusviajante.model.CadastroUsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CadastroUsuarioRepository extends JpaRepository<CadastroUsuarioModel, Long> {
    public List<CadastroUsuarioModel> findAllByNomeContainingIgnoreCase (String nome);
}
