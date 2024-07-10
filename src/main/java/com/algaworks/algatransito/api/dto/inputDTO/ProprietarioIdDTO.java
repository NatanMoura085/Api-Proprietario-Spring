package com.algaworks.algatransito.api.dto.inputDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProprietarioIdDTO {
    @NotNull
    private Long id;
}
