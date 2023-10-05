package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Aluno;

@Service
public class AlunoService {
    
    private Map<Long, Aluno> alunosPorCodigo = new HashMap<>();

    public List<Aluno> buscarAluno() {
        return new ArrayList<>(alunosPorCodigo.values());
    }

    public Aluno criarAluno(Aluno aluno) throws Exception {
        Long codigo = aluno.getCodigo();
        
        // Verifique a idade do aluno
        if (aluno.getIdade() < 18) {
            throw new Exception("Usuário menor de idade");
        }

        if (alunosPorCodigo.containsKey(codigo)) {
            throw new Exception("Já existe um aluno com o mesmo código.");
        }
        
        // Adicione o aluno ao mapa com o código como chave
        alunosPorCodigo.put(codigo, aluno);
        
        return aluno;
    }

    public Aluno buscarAluno(Long codigo) throws Exception {
        if (alunosPorCodigo.containsKey(codigo)) {
            return alunosPorCodigo.get(codigo);
        }
        
        throw new Exception("Aluno não encontrado.");
    }

    public void deletarAluno(Long codigo) throws Exception {
        if (alunosPorCodigo.containsKey(codigo)) {
            alunosPorCodigo.remove(codigo);
        } else {
            throw new Exception("Aluno não encontrado.");
        }
    }
}
