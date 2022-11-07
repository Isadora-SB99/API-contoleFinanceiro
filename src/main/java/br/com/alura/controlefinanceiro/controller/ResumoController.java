package br.com.alura.controlefinanceiro.controller;

import br.com.alura.controlefinanceiro.model.Categorias;
import br.com.alura.controlefinanceiro.model.GastoCategoria;
import br.com.alura.controlefinanceiro.model.Resumo;
import br.com.alura.controlefinanceiro.model.entities.*;
import br.com.alura.controlefinanceiro.model.repository.DespesasRepository;
import br.com.alura.controlefinanceiro.model.repository.ReceitasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class ResumoController {

    private ReceitasRepository receitasRepository;
    private DespesasRepository despesasRepository;

    @Autowired
    public ResumoController(ReceitasRepository receitasRepository, DespesasRepository despesasRepository){
        this.receitasRepository = receitasRepository;
        this.despesasRepository = despesasRepository;
    }

    LocalDate dataInicial;
    LocalDate dataFinal;
    double valorTotalReceitas;
    double valorTotalDespesas;
    double valorTotalPorCategoria;
    List<GastoCategoria> totalGastoCategoria = new ArrayList<>();

    public void todosDoMes(int ano, int mes){
        Month mesControle = Month.of(mes);
        boolean anoLeap = Year.isLeap(ano);
        int dia = mesControle.length(anoLeap);
        dataInicial = LocalDate.of(ano, mes, 1);
        dataFinal = LocalDate.of(ano, mes, dia);
    }

    public double totalReceitas (int ano, int mes){
        valorTotalReceitas=0;
        todosDoMes(ano,mes);
        Iterable<Receitas> receitas = receitasRepository.findAllByDataBetween(dataInicial, dataFinal);
        for (Receitas r: receitas) {
            valorTotalReceitas+=r.getValor();
        }
        return valorTotalReceitas;
    }

    public double totalDespesas  (int ano, int mes) {
        valorTotalDespesas = 0;
        todosDoMes(ano, mes);
        Iterable<Despesas> despesas = despesasRepository.findAllByDataBetween(dataInicial,dataFinal);
        for (Despesas d: despesas) {
            valorTotalDespesas+=d.getValor();
        }
        return valorTotalDespesas;
    }

    double saldoFinal() {
        return valorTotalReceitas - valorTotalDespesas;
    }

    public List<GastoCategoria> totalPorCategoria(int ano, int mes){
        valorTotalPorCategoria = 0;
        totalGastoCategoria.clear();
        for (Categorias c: Categorias.values()) {
            todosDoMes(ano, mes);
            Iterable<Despesas> despesas = despesasRepository.findAllByCategoriaAndDataBetween(c, dataInicial,dataFinal);
            try {
                for (Despesas d : despesas) {
                    valorTotalPorCategoria+=d.getValor();
                }
                totalGastoCategoria.add(new GastoCategoria(c,valorTotalPorCategoria));
            } catch (NullPointerException e) {
                totalGastoCategoria.add(new GastoCategoria(c, 0.0));
            }
            valorTotalPorCategoria = 0;
        }
        return totalGastoCategoria;
    }

    @GetMapping("/resumo/{ano}/{mes}")
    public Resumo resumoMensal (@PathVariable int ano, @PathVariable int mes){
        Resumo r = new Resumo(totalReceitas(ano, mes), totalDespesas(ano, mes), saldoFinal(), totalPorCategoria(ano, mes));
        return r;
    }
}
