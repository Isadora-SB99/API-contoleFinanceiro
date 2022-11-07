package br.com.alura.controlefinanceiro.model.repository;

import br.com.alura.controlefinanceiro.model.Categorias;
import br.com.alura.controlefinanceiro.model.entities.Despesas;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface DespesasRepository extends CrudRepository<Despesas, Integer>{

    Iterable<Despesas> findByDescricaoContainingIgnoreCase(String descricao);

    Iterable<Despesas> findAllByDataBetween(LocalDate dataInicial, LocalDate dataFinal);

    Iterable<Despesas> findAllByCategoriaAndDataBetween (Categorias categoria, LocalDate dataInicial, LocalDate dataFinal);
}
