package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.api.assembler.VeiculoAssembler;
import com.algaworks.algatransito.api.dto.VeiculoDTO;
import com.algaworks.algatransito.api.dto.inputDTO.VeiculoInputDTO;
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
    private  final VeiculoAssembler veiculoAssembler;
    @GetMapping
    public List<VeiculoDTO> busca(){
        return veiculoAssembler.toCollectionMap(veiculoRepository.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> buscaPeloID(@PathVariable Long id){
        return veiculoRepository.findById(id).map(veiculoAssembler::toModelMap)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoDTO cadastrarVeiculo(@Valid @RequestBody VeiculoInputDTO veiculoInputDTO){
        Veiculo novoVeiculo = veiculoAssembler.toEntity(veiculoInputDTO);
        Veiculo veiculoCadastrado = registroVeiculoService.cadastrar(novoVeiculo);
        return veiculoAssembler.toModelMap(veiculoCadastrado);
    }

}
