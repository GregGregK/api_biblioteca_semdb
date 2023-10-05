package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Aluno;
import com.example.demo.service.AlunoService;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {
    
    final AlunoService alunoService;

    public AlunoController(AlunoService alunoService){
        this.alunoService = alunoService;
    }


    @GetMapping("find")
    public ResponseEntity<?> buscarAluno(){
        try{
            List<Aluno> lista = alunoService.buscarAluno();
            
            if (lista.isEmpty()) {
                return new ResponseEntity<>("A Lista de alunos está vazia!", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity(lista, HttpStatus.OK);
            }
        } catch (Exception ex){
            return new ResponseEntity<>("Erro na requisição", HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("add")
    public ResponseEntity<?> criarAluno(@RequestBody Aluno aluno) {
        try {
            aluno = alunoService.criarAluno(aluno);
            return new ResponseEntity<>(aluno, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    

    @GetMapping("/findById/{codigo}")
    public ResponseEntity<?> buscarAluno(@PathVariable("codigo") Long codigo){
        try {
            Aluno aluno = alunoService.buscarAluno(codigo);
            return new ResponseEntity(aluno, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> deletarAluno(@PathVariable("codigo") Long codigo) {
        try {
            alunoService.deletarAluno(codigo);
            return new ResponseEntity<>("Aluno excluido com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }





























}
