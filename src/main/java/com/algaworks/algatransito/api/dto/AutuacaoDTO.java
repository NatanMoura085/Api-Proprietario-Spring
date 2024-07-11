package com.algaworks.algatransito.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
@Getter
@Setter

public class AutuacaoDTO {
    private Long id;
    private String descricao;
    private BigDecimal valorMultar;
    private OffsetDateTime dataOcorrencia;

}
