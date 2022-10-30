package com.empiricus.statusviajante.repository;

import com.empiricus.statusviajante.model.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CadastroRepository extends JpaRepository<Cadastro, Long> {

    public List<Cadastro> findAllByNomeContainingIgnoreCase (String nome);


}
