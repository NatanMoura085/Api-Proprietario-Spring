package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.api.dto.VeiculoDTO;
import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import com.algaworks.algatransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("veiculos")
public class VeiculoController {

    private VeiculoRepository veiculoRepository;
    private RegistroVeiculoService registroVeiculoService;
    @GetMapping
    public List<Veiculo> busca(){
        return veiculoRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> buscaPeloID(@PathVariable Long id){
        return veiculoRepository.findById(id).map(veiculo->{
              VeiculoDTO veiculoDTO = new VeiculoDTO();
              veiculoDTO.setId(veiculo.getId());
              veiculoDTO.setMarca(veiculo.getMarca());
              veiculoDTO.setNomeDoProprietario(veiculo.getProprietario().getNome());
              veiculoDTO.setStatus(veiculo.getStatus());
              veiculoDTO.setModelo(veiculo.getModelo());
              veiculoDTO.setPlaca(veiculo.getPlaca());
              veiculoDTO.setDataCadastro(veiculo.getDataCadastro());
              veiculoDTO.setDataApreensao(veiculo.getDataApreensao());
              return veiculoDTO;
                })

                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo cadastrarVeiculo(@Valid @RequestBody Veiculo veiculo){
        return registroVeiculoService.cadastrar(veiculo);
    }

}
