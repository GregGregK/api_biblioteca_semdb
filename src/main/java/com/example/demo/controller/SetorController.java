package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.SetorService;
import com.example.demo.entity.Setor;

@Controller
@RequestMapping("api/setor")
public class SetorController {
    final SetorService setorService;

    public SetorController(SetorService setorService){
        this.setorService = setorService;
    }


    @GetMapping("find")
    public ResponseEntity<?> buscarSetor() {
        try {
            List<Setor> lista = setorService.buscarSetor();

            if (lista.isEmpty()){
                return new ResponseEntity<>("A lista de setores está vazia!", HttpStatus.NOT_FOUND);                
            } else {
                return new ResponseEntity(lista, HttpStatus.OK);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("Erro na requisição", HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("add")
    public ResponseEntity<?> criarSetor(@RequestBody Setor setor){
        try {
            setor = setorService.criarSetor(setor);
            return new ResponseEntity<>(setor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarSetor(@PathVariable("id") Long id) {
        try {
            setorService.deletarSetor(id);
            return new ResponseEntity<>("Setor excluido com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<?> buscarSetorPorId(@PathVariable("id") Long id) {
        try {
            Setor setor = setorService.buscarSetor(id);
            return new ResponseEntity<>(setor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}