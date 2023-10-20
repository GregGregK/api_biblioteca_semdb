package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private Long codigo;
    private String nome;
    private int idade;
    private List<Livro> livros; // Alterado de Livro para List<Livro>

    

    public List<Livro> getLivros() {
        if (livros == null) {
            livros = new ArrayList<>();
        }
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
