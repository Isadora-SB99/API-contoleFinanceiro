package br.com.alura.controlefinanceiro.model;

import br.com.alura.controlefinanceiro.model.Categorias;
import lombok.Getter;
import lombok.Setter;

public class GastoCategoria {

    @Getter    @Setter
    Categorias categoria;

    @Getter    @Setter
    double valorTotal;

    public GastoCategoria(Categorias categoria, double valorTotal) {
        this.categoria = categoria;
        this.valorTotal = valorTotal;
    }
}
