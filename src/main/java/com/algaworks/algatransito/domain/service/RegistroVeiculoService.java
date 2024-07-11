package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.StatusVeiculo;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class RegistroVeiculoService {
    private VeiculoRepository veiculoRepository;
    private ProprietarioRepository proprietarioRepository;
    private RegistroProprietarioService registroProprietarioService;



    public Veiculo buscar(Long id){
        return veiculoRepository.findById(id).orElseThrow(()-> new NegocioException("nao achou veiculo"));
    }
    @Transactional
    public Veiculo cadastrar(Veiculo novoveiculo){

        boolean placaEmUso = veiculoRepository.findByPlaca(novoveiculo.getPlaca())
                .filter(v->!v.equals(novoveiculo))

                .isPresent();

        if (novoveiculo.getId() !=null){
            throw new NegocioException("id esta null");

        }

        if (placaEmUso){
            throw new NegocioException("placa ja esta em uso");
        }
        if (novoveiculo.getProprietario() == null || novoveiculo.getProprietario().getId() == null) {
            throw new NegocioException("O proprietário do veículo é obrigatório e deve ter um ID válido.");
        }

        Proprietario proprietario = registroProprietarioService.buscar(novoveiculo.getProprietario().getId());


        novoveiculo.setProprietario(proprietario);
        novoveiculo.setStatus(StatusVeiculo.REGULAR);
        novoveiculo.setDataCadastro(OffsetDateTime.now());
        return veiculoRepository.save(novoveiculo);
    }
}
