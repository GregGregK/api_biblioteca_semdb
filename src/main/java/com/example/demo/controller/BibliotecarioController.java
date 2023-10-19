package com.example.demo.controller;

import com.example.demo.entity.Bibliotecario;
import com.example.demo.service.BibliotecarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bibliotecario")
public class BibliotecarioController {

    final BibliotecarioService bibliotecarioService;

    public BibliotecarioController(BibliotecarioService bibliotecarioService) {
        this.bibliotecarioService = bibliotecarioService;
    }

    @GetMapping("find")
    public ResponseEntity<?> buscarBibliotecario() {
        try {
            List<Bibliotecario> lista = bibliotecarioService.buscarBibliotecario();

            if (lista.isEmpty()) {
                return new ResponseEntity<>("A Lista de bibliotecários está vazia!", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity(lista, HttpStatus.OK);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("Erro na requisição", HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("add")
    public ResponseEntity<?> criarBibliotecario(@RequestBody Bibliotecario bibliotecario) {
        try {
            bibliotecario = bibliotecarioService.criarBibliotecario(bibliotecario);
            return new ResponseEntity<>(bibliotecario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{codigo}")
    public ResponseEntity<?> buscarBibliotecario(@PathVariable("codigo") Long codigo) {
        try {
            Bibliotecario bibliotecario = bibliotecarioService.buscarBibliotecario(codigo);
            return new ResponseEntity(bibliotecario, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletar/{codigo}")
    public ResponseEntity<?> deletarBibliotecario(@PathVariable("codigo") Long codigo) {
        try {
            bibliotecarioService.deletarBibliotecario(codigo);
            return new ResponseEntity<>("Bibliotecário excluído com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
