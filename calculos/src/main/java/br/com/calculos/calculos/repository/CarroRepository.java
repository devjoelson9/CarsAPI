package br.com.calculos.calculos.repository;

import br.com.calculos.calculos.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
    List<Carro> findByModeloContainingIgnoreCase(String modelo);
}