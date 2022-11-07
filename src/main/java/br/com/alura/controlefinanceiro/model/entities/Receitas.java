package br.com.alura.controlefinanceiro.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table (name = "receitas")
public class Receitas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    @NotBlank
    @Getter @Setter
    private String descricao;

    @Min(0)
    @Getter @Setter
    private double valor;

    @Getter @Setter
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data ;

    public Receitas() {}

    public Receitas(String descricao, double valor, @JsonFormat(pattern = "dd/MM/yyyy") LocalDate data) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

}

