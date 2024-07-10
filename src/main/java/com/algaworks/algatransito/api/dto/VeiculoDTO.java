package com.algaworks.algatransito.api.dto;

import com.algaworks.algatransito.domain.model.StatusVeiculo;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class VeiculoDTO {
    private Long id;
    private String marca;
    private String modelo;
    private String placa;
    private ProprietarioResumoDTO proprietario;
    private StatusVeiculo status;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;
}
