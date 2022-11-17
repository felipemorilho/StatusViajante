package com.empiricus.statusviajante.service;

import java.nio.charset.Charset;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.empiricus.statusviajante.dto.UsuarioDto;
import com.empiricus.statusviajante.repository.CadastroUsuarioRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.empiricus.statusviajante.model.CadastroUsuarioModel;

//#Lógica de acesso do usuário, aqui verifica como o usuário vai se cadastrar e logar
@Service
public class CadastroUsuarioService {

    @Autowired
    private CadastroUsuarioRepository cadastroUsuarioRepository;

    /**
     * Password must contain at least one digit [0-9].
     * Password must contain at least one lowercase Latin character [a-z].
     * Password must contain at least one uppercase Latin character [A-Z].
     * Password must contain at least one special character like ! @ # & ( ).
     * Password must contain a length of at least 8 characters and a maximum of 20 characters.
     */
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    public CadastroUsuarioModel CadastrarUsuario(UsuarioDto usuarioDto) throws Exception {

        Pattern validadorSenha = Pattern.compile(PASSWORD_PATTERN);

        Matcher matcher = validadorSenha.matcher(usuarioDto.getSenha());

        if(matcher.matches() == false) {
            throw new Exception("Senha inválida.");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String senhaEncoded = encoder.encode(usuarioDto.getSenha());

        usuarioDto.setSenhaEncoded(senhaEncoded);
        return cadastroUsuarioRepository.save(usuarioDto.toModel());
    }

    public Optional<CadastroUsuarioModel> Logar(Optional<CadastroUsuarioModel> user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Optional<CadastroUsuarioModel> usuario = cadastroUsuarioRepository.findByUsuario(user.get().getUsuario());

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