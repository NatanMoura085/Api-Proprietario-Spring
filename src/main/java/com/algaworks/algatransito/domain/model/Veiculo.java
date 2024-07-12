package com.algaworks.algatransito.domain.model;

import com.algaworks.algatransito.domain.exception.NegocioException;
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
   @OneToMany(mappedBy = "veiculo",cascade = CascadeType.ALL)
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

   public Autuacao adicionarAtuacao(Autuacao autuacao){
      autuacao.setDataOcorrencia(OffsetDateTime.now());
      autuacao.setVeiculo(this);
      getAutuacaos().add(autuacao);
      return autuacao;


   }


   public void apreender(){
      if (estaApreender()){
         throw new NegocioException("veiculo ja esta apreendido");
      }

      setStatus(StatusVeiculo.APREENSAO);
      setDataApreensao(OffsetDateTime.now());
   }
   public boolean estaApreender(){
      return StatusVeiculo.APREENSAO.equals(getStatus());
   }


   public void  removerApreensao(){
      if (naoEstaApreendido()){
         throw new NegocioException("veiculo nao esta Apreendido");
      }

      setStatus(StatusVeiculo.REGULAR);
      setDataApreensao(null);



   }


   public boolean naoEstaApreendido(){
      return !estaApreender();
   }
}



