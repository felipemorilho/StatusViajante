<<<<<<< HEAD
package com.empiricus.statusviajante.service

import com.empiricus.statusviajante.dto.UsuarioDto
import com.empiricus.statusviajante.model.CadastroUsuarioModel
import com.empiricus.statusviajante.repository.CadastroUsuarioRepository
import org.apache.commons.codec.binary.Base64
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.nio.charset.Charset
import java.util.*
import java.util.regex.Pattern

//#Lógica de acesso do usuário, aqui verifica como o usuário vai se cadastrar e logar
@Service
class CadastroUsuarioService {
    @Autowired
    private val cadastroUsuarioRepository: CadastroUsuarioRepository? = null
    @Throws(Exception::class)
    fun CadastrarUsuario(usuarioDto: UsuarioDto): CadastroUsuarioModel {
        val validadorSenha = Pattern.compile(PASSWORD_PATTERN)
        val matcher = validadorSenha.matcher(usuarioDto.senha)
        if (matcher.matches() == false) {
            throw Exception("Senha inválida.")
        }
        val encoder = BCryptPasswordEncoder()
        val senhaEncoded = encoder.encode(usuarioDto.senha)
        usuarioDto.senhaEncoded = senhaEncoded
        return cadastroUsuarioRepository!!.save(usuarioDto.toModel())
    }

    val all: List<UsuarioDto>
        get() {
            val cadastroUsuarioModels = cadastroUsuarioRepository!!.findAll()
            return cadastroUsuarioModels.stream()
                .map { cadastroUsuarioModel: CadastroUsuarioModel? -> cadastroUsuarioModel!!.toDto() }.toList()
        }

    fun getById(id: Long): UsuarioDto {
        val cadastroUsuarioModel = cadastroUsuarioRepository!!.findById(id).get()
        return cadastroUsuarioModel.toDto()
    }

    fun deleteUser(idUsuario: Long) {
        cadastroUsuarioRepository!!.deleteById(idUsuario)
    }

    fun Logar(user: Optional<CadastroUsuarioModel>): Optional<CadastroUsuarioModel>? {
        val encoder = BCryptPasswordEncoder()
        val usuario = cadastroUsuarioRepository!!.findByUsuario(user.get().usuario)
        if (usuario!!.isPresent) {
            if (encoder.matches(user.get().senha, usuario.get().senha)) {
                val auth = user.get().usuario + ":" + user.get().senha
                val encodedAuth = Base64.encodeBase64(auth.toByteArray(Charset.forName("US-ASCII")))
                val authHeader = "Basic " + String(encodedAuth)
                user.get().token = authHeader
                user.get().nome = usuario.get().nome
                return user
            }
        }
        return null
    }

    companion object {
        /**
         * Password must contain at least one digit [0-9].
         * Password must contain at least one lowercase Latin character [a-z].
         * Password must contain at least one uppercase Latin character [A-Z].
         * Password must contain at least one special character like ! @ # & ( ).
         * Password must contain a length of at least 8 characters and a maximum of 20 characters.
         */
        private const val PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"
=======
package com.empiricus.statusviajante.service;

import java.nio.charset.Charset;
import java.util.List;
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

    public List<UsuarioDto> getAll() {
        List<CadastroUsuarioModel> cadastroUsuarioModels = cadastroUsuarioRepository.findAll();
        return cadastroUsuarioModels.stream()
                .map(cadastroUsuarioModel -> cadastroUsuarioModel.toDto()).toList();
    }

    public UsuarioDto getById(Long id) {
        CadastroUsuarioModel cadastroUsuarioModel = cadastroUsuarioRepository.findById(id).get();
        return cadastroUsuarioModel.toDto();
    }

    public void deleteUser(Long idUsuario){
        cadastroUsuarioRepository.deleteById(idUsuario);
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
>>>>>>> bd24619 (Rename .java to .kt)
    }
}