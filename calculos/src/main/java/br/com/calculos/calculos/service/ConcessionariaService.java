package br.com.calculos.calculos.service;

import br.com.calculos.calculos.entity.Concessionaria;
import br.com.calculos.calculos.repository.ConcessionariaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ConcessionariaService {

    private ConcessionariaRepository concessionariaRepository;

    @Transactional
    public List<Concessionaria> listarConcessionaria (){
        return concessionariaRepository.findAll();
    }

    @Transactional
    public Optional<Concessionaria> concessionariaPorId(Long id){
        return concessionariaRepository.findById(id);
    }

    public Concessionaria adicionarConcessionaria(Concessionaria concessionaria){
        return concessionariaRepository.save(concessionaria);
    }

    public Concessionaria atualizarConcessionaria(Long id, Concessionaria concessionariaNova){
        Concessionaria concessionariaExistente = this.concessionariaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado para atualiização"));

        concessionariaExistente.setNome(concessionariaNova.getNome());
        concessionariaExistente.setEndereco(concessionariaNova.getEndereco());
        concessionariaExistente.setTelefone(concessionariaNova.getTelefone());
        concessionariaExistente.setCnpj(concessionariaNova.getCnpj());

        return concessionariaRepository.save(concessionariaExistente);
    }

}
