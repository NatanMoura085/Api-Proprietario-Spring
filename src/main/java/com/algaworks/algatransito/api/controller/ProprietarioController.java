package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proprietarios")
@AllArgsConstructor
public class ProprietarioController {

private final ProprietarioRepository proprietarioRepository;

    @GetMapping
    public List<Proprietario> listar() {
        return proprietarioRepository.findAll();

    }

    @GetMapping( params = "nome")
    public List<Proprietario> listarPeloNome(@RequestParam String nome){
        return proprietarioRepository.findByNome(nome);
    }

@GetMapping("/{id}")
   public ResponseEntity<Proprietario> listaPorId(@PathVariable Long id){
        return proprietarioRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
   }


   @PostMapping
   public Proprietario cadastraProprieatrio(@RequestBody Proprietario proprietario){
        return proprietarioRepository.save(proprietario);
   }
}
