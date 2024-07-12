package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.api.assembler.AutuacaoAssembler;
import com.algaworks.algatransito.api.dto.AutuacaoDTO;
import com.algaworks.algatransito.api.dto.inputDTO.AutuacaoInputDTO;
import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.service.RegistroAutuacaoService;
import com.algaworks.algatransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos/{veiculoId}/autuacoes")
@AllArgsConstructor
public class AutuacaoController {
    private final RegistroAutuacaoService registroAutuacaoService;
    private final RegistroVeiculoService registroVeiculoService;
    private final AutuacaoAssembler assembler;

    @GetMapping
    public List<AutuacaoDTO> listar(@PathVariable Long veiculoId){
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
        return assembler.toCollectionMap(veiculo.getAutuacaos());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutuacaoDTO cadastra(@PathVariable Long veiculoId, @Valid @RequestBody AutuacaoInputDTO autuacaoInputDTO){
        Autuacao novaAtuacao = assembler.toEntity(autuacaoInputDTO);
        Autuacao registraAtuacao = registroAutuacaoService.registrar(veiculoId,novaAtuacao);
        return assembler.toModelMap(registraAtuacao);

    }
}
