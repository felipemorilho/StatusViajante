package com.empiricus.statusviajante.repository;

import com.empiricus.statusviajante.model.CadastroUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CadastroUsuarioRepository extends JpaRepository<CadastroUsuario, Long> {

    public List<CadastroUsuario> findAllByNomeContainingIgnoreCase (String nome);
}
