package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RegistroProprietarioService {
    private final ProprietarioRepository proprietarioRepository;


    @Transactional
    public Proprietario salvar(Proprietario proprietario){
        boolean emailEmUso = proprietarioRepository.findByEmail(proprietario.getEmail())
                .filter(p->!p.equals(proprietario))
                .isPresent();

        if (emailEmUso){
            throw new  NegocioException("Ja existir email");
        }
        return proprietarioRepository.save(proprietario);
    }
    @Transactional
    public void excluir(Long id){
        proprietarioRepository.deleteById(id);
    }
}
