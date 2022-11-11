package com.empiricus.statusviajante.repository;

import java.util.Optional;
import com.empiricus.statusviajante.model.CadastroUsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UsuarioLoginRepository extends JpaRepository <CadastroUsuarioModel, Long>{

    public Optional<CadastroUsuarioModel> findByUsuario(String username);

}
