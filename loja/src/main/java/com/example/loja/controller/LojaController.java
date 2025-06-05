package com.example.loja.controller;

import com.example.loja.model.Departamento;
import com.example.loja.model.Funcionario;
import com.example.loja.service.LojaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loja")
public class LojaController {

    private final LojaService lojaService;

    public LojaController(LojaService lojaService){
        this.lojaService = lojaService;
    }

    @PostMapping("/cadastroDepartamento")
    public Departamento addDepartamento(@RequestBody Departamento departamento){
        return lojaService.criarDepartamento(departamento);
    }


    @PostMapping("/cadastroFuncionário")
    public Funcionario addFuncionario(@RequestBody Funcionario funcionario){
        return lojaService.criarFuncionario(funcionario);
    }


    @GetMapping("/listarDepartamentos")
    public List<Departamento> departamentos(){
        return lojaService.listarDepartamentos();
    }

    @GetMapping("/listarFuncionarios")
    public List<Funcionario> funcionarios(){
        return lojaService.listarFuncionarios();
    }

    @PostMapping("/addFuncionarioDepartamento/departamento={id_departamento}/matricola={id_matricola}")
    public ResponseEntity<?> addFuncionarioDepartamento(@PathVariable Long id_departamento, @PathVariable Long id_matricola){
        lojaService.vincularFuncionarioDepartamento(id_departamento, id_matricola);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro finalizado");
    }

    @PostMapping("/atualizar/departamentoAtual={id_departamentoAtual}/novoDepartamento={id_novoDepartamento}/matricola={matricola}")
    public ResponseEntity<?> transferirDepartamento(@PathVariable Long id_departamentoAtual, @PathVariable Long id_novoDepartamento, @PathVariable Long matricola){
        lojaService.transferirFuncionario(id_departamentoAtual, id_novoDepartamento, matricola);
        return ResponseEntity.status(HttpStatus.CREATED).body("Transferencia realizada com sucesso");
    }
}
