package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.Bibliotecario;
import com.example.demo.entity.Livro;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    public Map<Long, Livro> livrosPorCodigo = new HashMap<>();
    private Long proximoCodigo = 1L;
    public List<Livro> buscarLivros() {
        return new ArrayList<>(livrosPorCodigo.values());
    }

    public Livro adicionarLivro(Livro livro) throws Exception {
        Long codigo = proximoCodigo++;
        livro.setCodigo(codigo);

        livrosPorCodigo.put(codigo, livro);
        return livro;
    }

    public Livro buscarLivro(Long codigo) throws Exception {
        if (livrosPorCodigo.containsKey(codigo)) {
            return livrosPorCodigo.get(codigo);
        }

        throw new Exception("Livro não encontrado.");
    }

    public void deletarLivro(Long codigo) throws Exception {
        if (livrosPorCodigo.containsKey(codigo)) {
            livrosPorCodigo.remove(codigo);
        } else {
            throw new Exception("Livro não encontrado.");
        }
    }

    public Livro updateLivro(Long codigo, Livro livroAtualizado) throws Exception {
        if(livroAtualizado == null){
            throw new Exception("Livro não encontrado");
        }

        String nome = livroAtualizado.getNome();
        String autor = livroAtualizado.getAutor();
        String desc = livroAtualizado.getDesc();
        int qntd = livroAtualizado.getQntd();
        int isbn = livroAtualizado.getISBN();

        if (nome == null || nome.isEmpty()) {
            throw new Exception("Campo de nome não pode estar vazio");
        }
        if (autor == null || autor.isEmpty()) {
            throw new Exception("Campo de autor não pode estar vazio");
        }
        if (desc == null || desc.isEmpty()) {
            throw new Exception("Campo de descrição não pode estar vazio");
        }
        if (qntd == 0 || nome.isEmpty()) {
            throw new Exception("Campo de quantidade não pode estar vazio");
        }
        if (isbn == 0 || nome.isEmpty()) {
            throw new Exception("Campo de ISBN não pode estar vazio");
        }
        if (!livrosPorCodigo.containsKey(codigo)) {
            throw new Exception("Livro não encontrado");
        }

        
        Livro livroExistente = livrosPorCodigo.get(codigo);

        livroExistente.setNome(nome);
        livroExistente.setAutor(autor);
        livroExistente.setDesc(desc);
        livroExistente.setQntd(qntd);
        livroExistente.setISBN(isbn);

        return livroExistente;
    }


}

