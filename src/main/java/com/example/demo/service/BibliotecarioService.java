package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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

    // MÉTODO POST
    public Bibliotecario criarBibliotecario(Bibliotecario bibliotecario) throws Exception {
        Long codigo = proximoCodigo++;
        String nome = bibliotecario.getNome();

        bibliotecario.setCodigo(codigo);

        if (bibliotecario == null) {
            throw new Exception("Bibliotecario vazio.");
        } if (nome == null || nome.isEmpty()) {
            throw new Exception("Nome não pode estar vazio");
        } 
        bibliotecariosPorCodigo.put(codigo, bibliotecario);

        return bibliotecario;
    }


    //MÉTODO GET 
    public Bibliotecario buscarBibliotecario(Long codigo) throws Exception {
        if (bibliotecariosPorCodigo.containsKey(codigo)) {
            return bibliotecariosPorCodigo.get(codigo);
        }

        throw new Exception("Bibliotecário não encontrado.");
    }


    //MÉTODO DELETE
    public void deletarBibliotecario(Long codigo) throws Exception {
        if (bibliotecariosPorCodigo.containsKey(codigo)) {
            bibliotecariosPorCodigo.remove(codigo);
        } else {
            throw new Exception("Bibliotecário não encontrado.");
        }
    }

    public void adicionarSetor(Long codigo, Setor setor) throws Exception{
        if(setor == null){
            throw new Exception("O Setor não pode ficar vazio!");
        }

        if(!bibliotecariosPorCodigo.containsKey(codigo)){
            throw new Exception("Bibliotecario nao encontrado");
        }

        Bibliotecario bibliotecario = bibliotecariosPorCodigo.get(codigo);

        bibliotecario.setSetor(setor);
    }
    
    public void removerSetor(Long codigo, Long codigoSetor) throws Exception {
        if (!bibliotecariosPorCodigo.containsKey(codigo)) {
            throw new Exception("Bibliotecário não encontrado");
        }
    
        Bibliotecario bibliotecario = bibliotecariosPorCodigo.get(codigo);
        
        Setor setor = bibliotecario.getSetor();
        
        if (setor != null && setor.getId().equals(codigoSetor)) {
            // Se o bibliotecário possui o setor com o código correspondente, remove o setor
            bibliotecario.setSetor(null);
        } else {
            throw new Exception("Setor não encontrado no bibliotecário.");
        }
    }

    public Bibliotecario atualizarBibliotecario(Long codigo, Bibliotecario bibliotecarioAtualizado) throws Exception{
        if(bibliotecarioAtualizado == null) {
            throw new Exception("Bibliotecario não pode estar vazio");
        }

        String nome = bibliotecarioAtualizado.getNome();
    

        if (nome == null || nome.isEmpty()) {
            throw new Exception("Campo de nome não pode estar vazio");
        }

        if(!bibliotecariosPorCodigo.containsKey(codigo)) {
            throw new Exception("Bibliotecario não encontrado");
        }

        Bibliotecario bibliotecarioExistente = bibliotecariosPorCodigo.get(codigo);

        bibliotecarioExistente.setNome(nome);

        return bibliotecarioExistente;



    }
    
    
}
