package com.empiricus.statusviajante.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.empiricus.statusviajante.model.UsuarioLoginModel;
import com.empiricus.statusviajante.model.CadastroUsuarioModel;
import com.empiricus.statusviajante.repository.UsuarioLoginRepository;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioLoginRepository usuarioLoginRepository;

    public CadastroUsuarioModel CadastrarUsuario(CadastroUsuarioModel usuario) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String senhaEncoder = encoder.encode(usuario.getSenha());

        usuario.setSenha(senhaEncoder);
        return usuarioLoginRepository.save(usuario);
    }

    public Optional<UsuarioLoginModel> Logar(Optional<UsuarioLoginModel> user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Optional<CadastroUsuarioModel> usuario = usuarioLoginRepository.findByUsuario(user.get().getUsuario());

        if (usuario.isPresent()) {
            if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
                String auth = user.get().getUsuario() + ":" + user.get().getSenha();

                byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));

                String authHeader = "Basic " + new String(encodedAuth);

                user.get().setToken(authHeader);

                user.get().setNome(usuario.get().getNome());

                return user;
            }
        }
        return null;
    }
}