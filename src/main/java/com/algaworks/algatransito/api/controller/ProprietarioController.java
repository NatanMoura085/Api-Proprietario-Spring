package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
   @ResponseStatus(HttpStatus.CREATED)
   public Proprietario cadastraProprieatrio(@RequestBody Proprietario proprietario){
        return proprietarioRepository.save(proprietario);
   }



   @PutMapping("/{id}")
   public ResponseEntity<Proprietario> atualizatDados(@PathVariable Long id, @RequestBody Proprietario proprietario){
        if (!proprietarioRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        proprietario.setId(id);

        Proprietario proprietarioAtualizado = proprietarioRepository.save(proprietario);

        return ResponseEntity.ok(proprietarioAtualizado);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> remover(@PathVariable Long id){
        if (!proprietarioRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        proprietarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
   }
}
