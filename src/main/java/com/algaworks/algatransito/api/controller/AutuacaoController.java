package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.api.assembler.AutuacaoAssembler;
import com.algaworks.algatransito.api.dto.AutuacaoDTO;
import com.algaworks.algatransito.api.dto.inputDTO.AutuacaoInputDTO;
import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.service.RegistroAutuacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/veiculos/{veiculoId}/autuacoes")
@AllArgsConstructor
public class AutuacaoController {
    private final RegistroAutuacaoService registroAutuacaoService;
    private final AutuacaoAssembler assembler;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutuacaoDTO cadastra(@PathVariable Long veiculoId, @Valid @RequestBody AutuacaoInputDTO autuacaoInputDTO){
        Autuacao novaAtuacao = assembler.toEntity(autuacaoInputDTO);
        Autuacao registraAtuacao = registroAutuacaoService.registrar(veiculoId,novaAtuacao);
        return assembler.toModelMap(registraAtuacao);

    }
}
