package br.com.alura.controlefinanceiro.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Resumo {

    @Getter    @Setter
    double valorTotalReceitas;

    @Getter    @Setter
    double valorTotalDespesas;

    @Getter    @Setter
    double saldoFinal;

    @Getter    @Setter
    List<GastoCategoria> valorTotalPorCategoria;

    public Resumo(double valorTotalReceitas, double valorTotalDespesas, double saldoFinal, List<GastoCategoria> valorTotalPorCategoria) {
        this.valorTotalReceitas = valorTotalReceitas;
        this.valorTotalDespesas = valorTotalDespesas;
        this.saldoFinal = saldoFinal;
        this.valorTotalPorCategoria = valorTotalPorCategoria;
    }
}
