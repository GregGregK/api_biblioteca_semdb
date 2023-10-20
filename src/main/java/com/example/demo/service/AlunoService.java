package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.el.stream.Optional;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Aluno;
import com.example.demo.entity.Livro;

@Service
public class AlunoService {
    
    private Map<Long, Aluno> alunosPorCodigo = new HashMap<>();
    private Long proximoCodigo = 1L;
    public List<Aluno> buscarAluno() {
        return new ArrayList<>(alunosPorCodigo.values());
    }

    // MÉTODO POST
    public Aluno criarAluno(Aluno aluno) throws Exception {

        // Aqui chamamos os parametros do aluno
        String nome = aluno.getNome();
        int idade = aluno.getIdade();

        //Aqui setamos o código auto incremental para o Aluno, assim nunca irá se repetir :)
        Long codigo = proximoCodigo++;
        aluno.setCodigo(codigo);

        // REGRAS DE NEGOCIO AQUI
        if (aluno == null) {
            throw new Exception("Aluno não pode estar vazio.");
        } if (nome == null || nome.isEmpty()) {
            throw new Exception("Campo de Nome não pode estar vazio");
        } if (idade == 0){
            throw new Exception("Campo Idade não pode estar vazio");
        }
        
        // Aqui iremos adicionar o codigo do aluno para um mapping, onde com ele poderemos filtrar o aluno por id unico.
        alunosPorCodigo.put(codigo, aluno);
        

        // Nao preciso nem explicar oque isso faz ne
        return aluno;
    }

    // MÉTODO GET
    public Aluno buscarAlunoPorID(Long codigo) throws Exception {
        
        //Aqui é bem simples, apenas verificamos se o código passado no path existe na nossa lista de alunos :)
        // Oque é path? é endereço, quando passamos um código no endereço da nossa requisição nesse caso localhost:8080/api/livros/findbyid/1 viu o 1 aqui no final? é o código do livro que estamos filtrando
        if (alunosPorCodigo.containsKey(codigo)) {

            // Caso encontre retorna o aluno
            return alunosPorCodigo.get(codigo);
        }
        
        // Caso não encontra, retorna um erro:
        throw new Exception("Aluno não encontrado.");
    }

    // MÉTODO DELETE
    public void deletarAluno(Long codigo) throws Exception {

        // Ele faz a mesma verificação do método get by id, ele verifica se o código passado na path existe se existir ele remove o aluno com esse código
        if (alunosPorCodigo.containsKey(codigo)) {
            alunosPorCodigo.remove(codigo);
        } else {

            //Ou também retorna um erro, caso o aluno não exista
            throw new Exception("Aluno não encontrado.");
        }
    }
    

    // MÉTODO PUT
    public Aluno atualizarAluno(Long codigo, Aluno alunoAtualizado) throws Exception {
        if (alunoAtualizado == null) {
            throw new Exception("Aluno não pode estar vazio.");
        }
    
        String nome = alunoAtualizado.getNome();
        int idade = alunoAtualizado.getIdade();
    
        if (nome == null || nome.isEmpty()) {
            throw new Exception("Campo de Nome não pode estar vazio");
        }
    
        if (idade == 0) {
            throw new Exception("Campo Idade não pode estar vazio");
        }
    
        if (!alunosPorCodigo.containsKey(codigo)) {
            throw new Exception("Aluno não encontrado.");
        }
    
        Aluno alunoExistente = alunosPorCodigo.get(codigo);
    
        // Atualize os campos do aluno com os novos valores
        alunoExistente.setNome(nome);
        alunoExistente.setIdade(idade);
        // Adicione outras atualizações de campos do aluno aqui, se necessário
    
        return alunoExistente;
    }

    
    
    public void adicionarLivrosAAluno(Long codigo, List<Livro> livros) throws Exception {
        if (livros.isEmpty()) {
            throw new Exception("A lista de livros está vazia.");
        }
    
        if (!alunosPorCodigo.containsKey(codigo)) {
            throw new Exception("Aluno não encontrado.");
        }
    
        Aluno aluno = alunosPorCodigo.get(codigo);
    
        aluno.getLivros().addAll(livros);
    }

    public void removerLivrosDeAluno(Long codigoAluno, Long codigoLivro) throws Exception {
        if (!alunosPorCodigo.containsKey(codigoAluno)) {
            throw new Exception("Aluno não encontrado.");
        }
    
        Aluno aluno = alunosPorCodigo.get(codigoAluno);
        List<Livro> livrosDoAluno = aluno.getLivros();
    
        // Encontre o livro com base no código do livro e remova-o
        Livro livroRemovido = null;
        for (Livro livro : livrosDoAluno) {
            if (livro.getCodigo().equals(codigoLivro)) {
                livroRemovido = livro;
                break;
            }
        }
    
        if (livroRemovido != null) {
            livrosDoAluno.remove(livroRemovido);
        } else {
            throw new Exception("Livro não encontrado na lista de livros do aluno.");
        }
    }
    
    
}
