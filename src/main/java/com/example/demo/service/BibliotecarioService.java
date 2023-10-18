package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.tokens.Token.ID;

import com.example.demo.entity.Bibliotecario;
import com.example.demo.entity.Setor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BibliotecarioService {

    private Map<Long, Bibliotecario> bibliotecariosPorCodigo = new HashMap<>();
    private Long proximoCodigo = 1L;

    public List<Bibliotecario> buscarBibliotecario() {
        return new ArrayList<>(bibliotecariosPorCodigo.values());
    }

    public Bibliotecario criarBibliotecario(Bibliotecario bibliotecario) throws Exception {
        // Gere um código para o novo bibliotecário
        Long codigo = proximoCodigo++;
        bibliotecario.setCodigo(codigo);

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

