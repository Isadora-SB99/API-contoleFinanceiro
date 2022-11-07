package br.com.alura.controlefinanceiro.controller;

import br.com.alura.controlefinanceiro.model.entities.Receitas;
import br.com.alura.controlefinanceiro.model.repository.ReceitasRepository;
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
@RequestMapping("/receitas")
public class ReceitasController {

    @Autowired
    private ReceitasRepository receitasRepository;

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeReceitas", allEntries = true)
    public @ResponseBody Receitas adicionarReceita (@Valid Receitas receita){
        receitasRepository.save(receita);
        return receita;
    }

    @GetMapping
    @Cacheable(value = "listaDeReceitas")
    public Iterable<Receitas> listarReceitas(@RequestParam(value="descricao", required = false) String descricao){
        if (descricao==null) {return receitasRepository.findAll();}
        else {return receitasRepository.findByDescricaoContainingIgnoreCase(descricao);}
    }

    @GetMapping("/{id}")
    public Optional<Receitas> obterReceitaPeloId(@PathVariable int id){
        return receitasRepository.findById(id);
    }

    @GetMapping("/{ano}/{mes}")
    public Iterable<Receitas> listarReceitasPorMes(@PathVariable int ano, @PathVariable int mes){
        LocalDate dataInicial;
        LocalDate dataFinal;

        Month mesControle = Month.of(mes);
        boolean anoLeap = Year.isLeap(ano);
        int dia = mesControle.length(anoLeap);
        dataInicial = LocalDate.of(ano, mes, 1);
        dataFinal = LocalDate.of(ano, mes, dia);

        return receitasRepository.findAllByDataBetween(dataInicial, dataFinal);

    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeReceitas", allEntries = true)
    public Receitas alterarReceita (@PathVariable int id, @Valid Receitas receita){
        receitasRepository.save(receita);
        return receita;
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeReceitas", allEntries = true)
    public void deletarReceita (@PathVariable int id){
        receitasRepository.deleteById(id);
    }

}
