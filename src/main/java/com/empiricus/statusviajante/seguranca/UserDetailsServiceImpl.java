package com.empiricus.statusviajante.seguranca;

import java.util.Optional;

import com.empiricus.statusviajante.repository.CadastroUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.empiricus.statusviajante.model.CadastroUsuarioModel;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private CadastroUsuarioRepository cadastroUsuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CadastroUsuarioModel> usuario = cadastroUsuarioRepository.findByUsuario(username);
        if(usuario.isPresent())
            return new UserDetailsImpl(usuario.get());
        else
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
}
