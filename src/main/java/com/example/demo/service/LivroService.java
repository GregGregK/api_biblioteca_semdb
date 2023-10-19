package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.Livro;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    private Map<Long, Livro> livrosPorISBN = new HashMap<>();

    public List<Livro> buscarLivros() {
        return new ArrayList<>(livrosPorISBN.values());
    }

    public Livro adicionarLivro(Livro livro) throws Exception {
        Long ISBN = livro.getISBN();

        if (livrosPorISBN.containsKey(ISBN)) {
            throw new Exception("Já existe um livro com o mesmo ISBN.");
        }

        

        livrosPorISBN.put(ISBN, livro);

        return livro;
    }

    public Livro buscarLivro(Long ISBN) throws Exception {
        if (livrosPorISBN.containsKey(ISBN)) {
            return livrosPorISBN.get(ISBN);
        }

        throw new Exception("Livro não encontrado.");
    }

    public void deletarLivro(Long ISBN) throws Exception {
        if (livrosPorISBN.containsKey(ISBN)) {
            livrosPorISBN.remove(ISBN);
        } else {
            throw new Exception("Livro não encontrado.");
        }
    }
}
