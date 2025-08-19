package br.com.calculos.calculos.service;

import br.com.calculos.calculos.entity.Carro;
import br.com.calculos.calculos.repository.CarroRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CarroService {

    private final CarroRepository carroRepository;

    @Transactional
    public List<Carro> buscarCarros(){
        return carroRepository.findAll();
    }

    @Transactional
    public Optional<Carro> buscarCarroPorId(long id){
        return carroRepository.findById(id);
    }

    @Transactional
    public Carro adicionarNovoCarro(Carro carro){
        return carroRepository.save(carro);
    }

    @Transactional
    public Carro atualizarCarro(Long id, Carro novoCarro){
        Carro carroExistente = carroRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Carro com ID " + id + " não encontrado."));

        carroExistente.setModelo(novoCarro.getModelo());
        carroExistente.setMarca(novoCarro.getMarca());
        carroExistente.setAnoFabricacao(novoCarro.getAnoFabricacao());

        return carroRepository.save(carroExistente);
    }

    @Transactional
    public void deletarCarro(Long id){
        Carro deletarCarro = carroRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Carro com ID " + id + " não encontrado para exclusão."));
        carroRepository.delete(deletarCarro);
    }

    public List<Carro> buscarCarrosPorModelo(String modelo) {
        if (modelo == null || modelo.isBlank()) {
            return buscarCarros(); // retorna todos se não passou filtro
        }
        return carroRepository.findByModeloContainingIgnoreCase(modelo);
    }
}