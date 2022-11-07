package br.com.alura.controlefinanceiro.controller;

import br.com.alura.controlefinanceiro.model.entities.Usuario;
import br.com.alura.controlefinanceiro.model.repository.PerfilRepository;
import br.com.alura.controlefinanceiro.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public @ResponseBody Usuario cadastrarUsuario (@Valid Usuario usuario){
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.getPerfis().add(perfilRepository.findByNome("USER"));
        usuarioRepository.save(usuario);
        return usuario;
    }

    @GetMapping
    public Iterable<Usuario> listarUsuarios (@RequestParam(value = "email", required = false) String email){
        if (email == null || email.isEmpty()) return usuarioRepository.findAll();
        else return usuarioRepository.findByEmailContainingIgnoreCase(email);
    }

    @PutMapping("/{id}")
    public Usuario alterarUsuario (@PathVariable int id, @Valid Usuario usuario){
        usuarioRepository.save(usuario);
        return usuario;
    }

    @DeleteMapping("/{id}")
    public void excluirUsuario (@PathVariable int id){
        usuarioRepository.deleteById(id);
    }

}
