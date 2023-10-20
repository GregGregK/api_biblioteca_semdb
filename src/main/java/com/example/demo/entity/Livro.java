package com.example.demo.entity;

public class Livro {
    private Long codigo;
    private int ISBN;
    private String nome;
    private String desc;
    private int qntd;
    private String autor;
    
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public int getISBN() {
        return ISBN;
    }
    public void setISBN(int iSBN) {
        ISBN = iSBN;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public int getQntd() {
        return qntd;
    }
    public void setQntd(int qntd) {
        this.qntd = qntd;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    

    
}
