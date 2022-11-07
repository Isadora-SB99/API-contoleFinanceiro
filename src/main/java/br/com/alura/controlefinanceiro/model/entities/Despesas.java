package br.com.alura.controlefinanceiro.model.entities;

import br.com.alura.controlefinanceiro.model.Categorias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;


@Entity
@Table(name = "despesas")
public class Despesas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter    @Setter
    private Integer id;

    @NotBlank
    @Getter    @Setter
    private String descricao;

    @Min(0)
    @Getter    @Setter
    private double valor;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Getter    @Setter
    private LocalDate data;

    @Getter    @Setter
    @Enumerated(EnumType.STRING)
    @NotNull(message = "")
    private Categorias categoria = Categorias.OUTRAS;

    public Despesas() {}

    public Despesas(String descricao, double valor, @JsonFormat(pattern = "dd/MM/yyyy") LocalDate data, String categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        Categorias cat = Categorias.valueOf(categoria);
        this.categoria = cat;
    }
}
