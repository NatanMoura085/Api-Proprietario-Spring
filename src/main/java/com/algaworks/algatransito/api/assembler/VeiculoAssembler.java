package com.algaworks.algatransito.api.assembler;

import com.algaworks.algatransito.api.dto.VeiculoDTO;
import com.algaworks.algatransito.api.dto.inputDTO.VeiculoInputDTO;
import com.algaworks.algatransito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class VeiculoAssembler {
    private final ModelMapper modelMapper;

    public Veiculo toEntity(VeiculoInputDTO veiculoInputDTO){
        return modelMapper.map(veiculoInputDTO,Veiculo.class);
    }

    public VeiculoDTO toModelMap(Veiculo veiculo){
        return modelMapper.map(veiculo,VeiculoDTO.class);

    }

    public List<VeiculoDTO> toCollectionMap(List<Veiculo> veiculos){
        return veiculos.stream().map(this::toModelMap).toList();
    }
}
