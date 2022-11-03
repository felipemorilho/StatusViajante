package com.empiricus.statusviajante.repository;

import com.empiricus.statusviajante.model.CadastroUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CadastroUsuarioRepository extends JpaRepository<CadastroUsuario, Long> {

    public List<CadastroUsuario> findAllByNomeContainingIgnoreCase(String nome);


}
