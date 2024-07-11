package com.algaworks.algatransito.api.dto.inputDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
@Getter
@Setter
public class AutuacaoInputDTO {

  @NotBlank
    private String descricao;
    @NotNull
    @Positive
    @Column(name = "valor_multa")
    private BigDecimal valorMultar;

}
