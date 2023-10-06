package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Bibliotecario;

@Service
public class BibliotecarioService {
    
    private Map<Long, Bibliotecario> bibliotecariosPorCodigo = new HashMap<>();

    public List<Bibliotecario> buscarBibliotecario() {
        return new ArrayList<>(bibliotecariosPorCodigo.values());
    }

    public Bibliotecario criarBibliotecario(Bibliotecario bibliotecario) throws Exception {
        Long codigo = bibliotecario.getCodigo();
    
        if (bibliotecariosPorCodigo.containsKey(codigo)) {
            throw new Exception("Já existe um bibliotecario com o mesmo código.");
        }
        
        bibliotecariosPorCodigo.put(codigo, bibliotecario);
        
        return bibliotecario;
    }

    public Bibliotecario buscarBibliotecario(Long codigo) throws Exception {
        if (bibliotecariosPorCodigo.containsKey(codigo)) {
            return bibliotecariosPorCodigo.get(codigo);
        }
        
        throw new Exception("Bibliotecario não encontrado.");
    }

    public void deletarBibliotecario(Long codigo) throws Exception {
        if (bibliotecariosPorCodigo.containsKey(codigo)) {
            bibliotecariosPorCodigo.remove(codigo);
        } else {
            throw new Exception("Bibliotecario não encontrado.");
        }
    }
}
