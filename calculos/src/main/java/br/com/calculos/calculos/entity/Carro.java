package br.com.calculos.calculos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private int anoFabricacao;



    /*@ManyToOne
    @JoinColumn(name = "concessionaria_id", nullable = false)
    private Concessionaria concessionaria;*/
}