package br.com.alura.controlefinanceiro.model.repository;

import br.com.alura.controlefinanceiro.model.entities.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository <Perfil, Integer> {

    Perfil findByNome (String nome);
}
