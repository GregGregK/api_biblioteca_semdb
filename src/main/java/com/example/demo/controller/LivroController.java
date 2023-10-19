package com.example.demo.controller;

import com.example.demo.entity.Livro;
import com.example.demo.service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livro")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping("find")
    public ResponseEntity<?> buscarLivros() {
        try {
            List<Livro> lista = livroService.buscarLivros();

            if (lista.isEmpty()) {
                return new ResponseEntity<>("A lista de livros está vazia!", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity(lista, HttpStatus.OK);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("Erro na requisição", HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("add")
    public ResponseEntity<?> adicionarLivro(@RequestBody Livro livro) {
        try {
            livro = livroService.adicionarLivro(livro);
            return new ResponseEntity<>(livro, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByISBN/{ISBN}")
    public ResponseEntity<?> buscarLivro(@PathVariable("ISBN") Long ISBN) {
        try {
            Livro livro = livroService.buscarLivro(ISBN);
            return new ResponseEntity(livro, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletar/{ISBN}")
    public ResponseEntity<?> deletarLivro(@PathVariable("ISBN") Long ISBN) {
        try {
            livroService.deletarLivro(ISBN);
            return new ResponseEntity<>("Livro excluído com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{ISBN}")
    public ResponseEntity<?> atualizarQuantidadeLivro(@PathVariable("ISBN") Long ISBN, @RequestParam int novaQuantidade) {
        try {
            Livro livro = livroService.buscarLivro(ISBN);
            livro.setQntd(novaQuantidade);
            return new ResponseEntity<>(livroService.adicionarLivro(livro), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
