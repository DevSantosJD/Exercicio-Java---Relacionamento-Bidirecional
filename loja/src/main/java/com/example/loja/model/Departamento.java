package com.example.loja.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tb_departamento")
@Data
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_departamento;

    private String nome;

    @OneToMany(mappedBy = "departamento")
    @JsonManagedReference
    private List<Funcionario> funcionarios;

    public Departamento(){}

    public Departamento(List<Funcionario> funcionarios, String nome) {
        this.nome = nome;
        this.funcionarios = funcionarios;
    }



}
