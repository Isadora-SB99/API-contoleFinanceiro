package br.com.alura.controlefinanceiro.controller;

import br.com.alura.controlefinanceiro.model.entities.Despesas;
import br.com.alura.controlefinanceiro.model.repository.DespesasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Optional;

@RestController
@RequestMapping("/despesas")
public class DespesasController {

    @Autowired
    private DespesasRepository despesasRepository;

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeDespesas", allEntries = true)
    public @ResponseBody Despesas adicionarDespesa(@Valid Despesas despesa){
        despesasRepository.save(despesa);
        return despesa;
    }

    @GetMapping
    @Cacheable(value = "listaDeDespesas")
    public Iterable<Despesas> listarDespesas(@RequestParam(value = "descricao", required = false) String descricao){
        if (descricao==null) return despesasRepository.findAll();
        else return despesasRepository.findByDescricaoContainingIgnoreCase(descricao);
    }

    @GetMapping("/{id}")
    public Optional<Despesas> obterDespesaPeloId(@PathVariable int id){
        return despesasRepository.findById(id);
    }

    @GetMapping("/{ano}/{mes}")
    public Iterable<Despesas> listarDespesasPorMes (@PathVariable int ano, @PathVariable int mes){
        LocalDate dataInicial;
        LocalDate dataFinal;

        Month mesControle = Month.of(mes);
        boolean anoLeap = Year.isLeap(ano);
        int dia = mesControle.length(anoLeap);
        dataInicial = LocalDate.of(ano, mes, 1);
        dataFinal = LocalDate.of(ano, mes, dia);

        return despesasRepository.findAllByDataBetween(dataInicial, dataFinal);
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeDespesas", allEntries = true)
    public Despesas alterarDespesa (@PathVariable int id, @Valid Despesas despesa){
        despesasRepository.save(despesa);
        return despesa;
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeDespesas", allEntries = true)
    public void deletarDespesa (@PathVariable int id){
        despesasRepository.deleteById(id);
    }
}
