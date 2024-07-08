package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import com.algaworks.algatransito.domain.service.RegistroProprietarioService;
import jakarta.validation.Valid;
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
private final RegistroProprietarioService registroProprietarioService;

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
   public Proprietario cadastraProprieatrio(@Valid @RequestBody Proprietario proprietario){
        return registroProprietarioService.salvar(proprietario);
   }



   @PutMapping("/{id}")
   public ResponseEntity<Proprietario> atualizatDados(@PathVariable Long id, @Valid @RequestBody Proprietario proprietario){
        if (!proprietarioRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        proprietario.setId(id);

        Proprietario proprietarioAtualizado = registroProprietarioService.salvar(proprietario);

        return ResponseEntity.ok(proprietarioAtualizado);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> remover(@PathVariable Long id){
        if (!proprietarioRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        registroProprietarioService.excluir(id);
        return ResponseEntity.noContent().build();
   }


}
