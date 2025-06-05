package com.example.loja.service;

import com.example.loja.model.Departamento;
import com.example.loja.model.Funcionario;
import com.example.loja.repository.DepartamentoRepository;
import com.example.loja.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LojaService {

    private final DepartamentoRepository departamentoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public LojaService(DepartamentoRepository departamentoRepository, FuncionarioRepository funcionarioRepository) {
        this.departamentoRepository = departamentoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    // Criar departamento
    public Departamento criarDepartamento(Departamento departamento){
        return departamentoRepository.save(departamento);
    }

    // Criar funcioanrio
    public Funcionario criarFuncionario(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

    //Listar departamentos
    public List<Departamento> listarDepartamentos(){
        return departamentoRepository.findAll();
    }

    //Listar funcionarios
    public List<Funcionario> listarFuncionarios(){
        return funcionarioRepository.findAll();
    }

    // vincular funcionario a departamento
    public void vincularFuncionarioDepartamento(long id_departamento, Long matricola){
        Departamento departamento = departamentoRepository.findById(id_departamento)
                .orElseThrow(()-> new RuntimeException("Departamento não localizado"));

        Funcionario funcionario = funcionarioRepository.findById(matricola)
                .orElseThrow(()-> new RuntimeException("Funcionario não localizado"));

        funcionario.setDepartamento(departamento);
        funcionarioRepository.save(funcionario);
    }

    //Desvincular funcionario de departamento
    public void desvincularDoSetor(long id_departamento, Long matricola){
        Departamento departamento = departamentoRepository.findById(id_departamento)
                .orElseThrow(()-> new RuntimeException("Departamento não localizado"));

        Funcionario funcionario = funcionarioRepository.findById(matricola)
                .orElseThrow(()-> new RuntimeException("Funcionario não localizado"));

        if(!departamento.getFuncionarios().contains(funcionario)){
            throw new RuntimeException("Funcionario não pertencente ao setor");
        }


        funcionario.setDepartamento(null);
        funcionarioRepository.save(funcionario);
    }

    // transferir funcionario de departamento
    public void transferirFuncionario(long id_departamentoAtual, Long id_novoDepartamento, Long matriculaFuncionario){
        Departamento departamentoAtual = departamentoRepository.findById(id_departamentoAtual)
                .orElseThrow(()-> new RuntimeException("Departamento não localizado"));

        Departamento novoDepartamento = departamentoRepository.findById(id_novoDepartamento)
                .orElseThrow(()-> new RuntimeException("Departamento não localizado"));

        Funcionario funcionario = funcionarioRepository.findById(matriculaFuncionario)
                .orElseThrow(()-> new RuntimeException("Funcionario não localizado"));

        if(!departamentoAtual.getFuncionarios().contains(funcionario)){
            throw new RuntimeException("Funcionario não pertence ao setor");
        }

        funcionario.setDepartamento(novoDepartamento);
        funcionarioRepository.save(funcionario);
    }
}
