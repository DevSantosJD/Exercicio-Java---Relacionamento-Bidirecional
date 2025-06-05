package com.example.loja.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_funcionarios")
@Data
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricola;

    @Column(nullable = false)
    private String nome;

    private LocalDate data_nascimento;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    @JsonIgnoreProperties("funcionarios")
    private Departamento departamento;

    public Funcionario(){
    }

    public Funcionario(String nome, LocalDate data_nascimento, String email, Departamento departamento) {
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.email = email;
        this.departamento = departamento;
    }

}