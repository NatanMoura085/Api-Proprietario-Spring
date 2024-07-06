package com.algaworks.algatransito.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Veiculo {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @EqualsAndHashCode.Include
   private Long id;
   @NotNull
   @ManyToOne
   private Proprietario proprietario;
   @NotBlank
   private String marca;
   @NotBlank
   private String modelo;
   @NotBlank
   @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
   private String placa;
   @Enumerated(EnumType.STRING)
   private StatusVeiculo status;
   private LocalDateTime dataCadastro;
   private LocalDateTime dataApreensao;


}
