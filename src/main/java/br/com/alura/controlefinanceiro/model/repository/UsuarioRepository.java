package br.com.alura.controlefinanceiro.model.repository;

import br.com.alura.controlefinanceiro.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    Iterable<Usuario> findByEmailContainingIgnoreCase(String email);
}
