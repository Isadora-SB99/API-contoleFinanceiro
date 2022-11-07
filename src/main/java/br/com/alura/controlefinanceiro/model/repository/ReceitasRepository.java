package br.com.alura.controlefinanceiro.model.repository;

import br.com.alura.controlefinanceiro.model.entities.Receitas;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface ReceitasRepository  extends CrudRepository<Receitas, Integer> {

    Iterable<Receitas> findByDescricaoContainingIgnoreCase(String descricao);

    Iterable<Receitas> findAllByDataBetween(LocalDate dataInicial, LocalDate dataFinal);

}
