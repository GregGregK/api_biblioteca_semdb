package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.entity.Setor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SetorService {
    private final Map<Long, Setor> setores = new ConcurrentHashMap<>();
    private long proximoId = 1;

    public List<Setor> buscarSetor() {
        return new ArrayList<>(setores.values());
    }

    public Setor criarSetor(Setor setor) throws Exception {
        // Atribuir um ID único ao setor
        long id = proximoId++;
        setor.setId(id);

        // Armazenar o setor no mapa usando o ID como chave
        setores.put(id, setor);

        return setor;
    }

    public void deletarSetor(long id) throws Exception {
        if (setores.containsKey(id)) {
            setores.remove(id);
        } else {
            throw new Exception("Setor não encontrado.");
        }
    }

    public Setor buscarSetor(long id) throws Exception {
        if (setores.containsKey(id)) {
            return setores.get(id);
        } else {
            throw new Exception("Setor não encontrado.");
        }
    }
}
