package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.api.dto.VeiculoDTO;
import com.algaworks.algatransito.config.ModelMapperConfig;
import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import com.algaworks.algatransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
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
    private ModelMapperConfig modelMapperConfig;
    @GetMapping
    public List<Veiculo> busca(){
        return veiculoRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> buscaPeloID(@PathVariable Long id){
        return veiculoRepository.findById(id).map(veiculo->
               modelMapperConfig.modelMapper().map(veiculo,VeiculoDTO.class))
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo cadastrarVeiculo(@Valid @RequestBody Veiculo veiculo){
        return registroVeiculoService.cadastrar(veiculo);
    }

}
