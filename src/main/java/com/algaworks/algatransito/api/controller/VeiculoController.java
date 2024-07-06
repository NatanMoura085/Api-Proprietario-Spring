package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import com.algaworks.algatransito.domain.service.RegistroVeiculoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("veiculos")
public class VeiculoController {

    private VeiculoRepository veiculoRepository;
    private RegistroVeiculoService registroVeiculoService;
    @GetMapping
    public List<Veiculo> busca(){
        return veiculoRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscaPeloID(@PathVariable Long id){
        return veiculoRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo cadastrarVeiculo(@RequestBody Veiculo veiculo){
        return registroVeiculoService.cadastrar(veiculo);
    }
}
