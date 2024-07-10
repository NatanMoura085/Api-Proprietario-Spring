package com.algaworks.algatransito.domain.model;

import com.algaworks.algatransito.domain.validation.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Veiculo {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @EqualsAndHashCode.Include
   private Long id;
   @Valid
   @ConvertGroup(from = Default.class,to = ValidationGroups.ProprietarioId.class)
   @NotNull
   @ManyToOne
   private Proprietario proprietario;
   @OneToMany(mappedBy = "veiculo")
   private List<Autuacao> autuacaos = new ArrayList<>();
   @NotBlank
   @Size(min = 2)
   private String marca;
   @NotBlank
   private String modelo;
   @NotBlank
   @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
   private String placa;
   @Enumerated(EnumType.STRING)
   private StatusVeiculo status;
   private OffsetDateTime dataCadastro;
   private OffsetDateTime dataApreensao;


}
