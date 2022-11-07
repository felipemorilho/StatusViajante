package com.empiricus.statusviajante.repository;

import java.util.Optional;
import com.empiricus.statusviajante.model.CadastroUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UsuarioRepository extends JpaRepository <CadastroUsuario, Long>{

    public Optional<CadastroUsuario> findByUsuario(String username);

}
