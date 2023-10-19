package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.Bibliotecario;
import com.example.demo.entity.Setor;

import org.springframework.stereotype.Service;

@Service
public class BibliotecarioService {

    private Map<Long, Bibliotecario> bibliotecariosPorCodigo = new HashMap<>();
    private Long proximoCodigo = 1L;
    public List<Bibliotecario> buscarBibliotecario() {
        return new ArrayList<>(bibliotecariosPorCodigo.values());
    }

    public Bibliotecario criarBibliotecario(Bibliotecario bibliotecario) throws Exception {
        Long codigo = proximoCodigo++;
        String nome = bibliotecario.getNome();
        Setor setor = bibliotecario.getSetor();

        bibliotecario.setCodigo(codigo);

        if (bibliotecario == null) {
            throw new Exception("Bibliotecario vazio.");
        } if (nome == null || nome.isEmpty()) {
            throw new Exception("Nome não pode estar vazio");
        } if (setor == null){
            throw new Exception("Setor não pode estar vazio");
        }
        bibliotecariosPorCodigo.put(codigo, bibliotecario);

        return bibliotecario;
    }

    public Bibliotecario buscarBibliotecario(Long codigo) throws Exception {
        if (bibliotecariosPorCodigo.containsKey(codigo)) {
            return bibliotecariosPorCodigo.get(codigo);
        }

        throw new Exception("Bibliotecário não encontrado.");
    }

    public void deletarBibliotecario(Long codigo) throws Exception {
        if (bibliotecariosPorCodigo.containsKey(codigo)) {
            bibliotecariosPorCodigo.remove(codigo);
        } else {
            throw new Exception("Bibliotecário não encontrado.");
        }
    }
}
