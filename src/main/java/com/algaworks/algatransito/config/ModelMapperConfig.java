package com.algaworks.algatransito.config;

import com.algaworks.algatransito.api.dto.VeiculoDTO;
import com.algaworks.algatransito.domain.model.Veiculo;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Veiculo.class, VeiculoDTO.class)
                .addMappings(mapper -> mapper.map(Veiculo::getProprietario,VeiculoDTO::setProprietario));
        return  modelMapper;
    }
}
