package com.algaworks.algatransito.api.assembler;

import com.algaworks.algatransito.api.dto.AutuacaoDTO;
import com.algaworks.algatransito.api.dto.inputDTO.AutuacaoInputDTO;
import com.algaworks.algatransito.domain.model.Autuacao;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AutuacaoAssembler {

    private final ModelMapper modelMapper;

    public Autuacao toEntity(AutuacaoInputDTO autuacaoInputDTO){
        return modelMapper.map(autuacaoInputDTO,Autuacao.class);
    }

    public AutuacaoDTO toModelMap(Autuacao autuacao){
        return modelMapper.map(autuacao,AutuacaoDTO.class);
    }


    public List<AutuacaoDTO> toCollectionMap(List<Autuacao> autuacaos){
        return  autuacaos.stream().map(this::toModelMap).toList();
    }
}
