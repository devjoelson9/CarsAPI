package br.com.calculos.calculos.repository;

import br.com.calculos.calculos.entity.Concessionaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcessionariaRepository extends JpaRepository<Concessionaria, Long> {
}
