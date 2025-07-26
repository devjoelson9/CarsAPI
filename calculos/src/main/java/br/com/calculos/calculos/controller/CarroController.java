package br.com.calculos.calculos.controller;

import br.com.calculos.calculos.entity.Carro;
import br.com.calculos.calculos.service.CarroService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/carro")
public class CarroController {

    private final CarroService carroService;

    @GetMapping("/listaDeCarros")
    public ResponseEntity<List<Carro>> listarTodosCarros(){
        try {
            List<Carro> listarCarros = this.carroService.buscarCarros();
            return new ResponseEntity<>(listarCarros, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/carroEspecifico/{id}")
    public ResponseEntity<Carro> buscarCarroPorId(@PathVariable Long id){
        try{
            Optional<Carro> buscarCarro = this.carroService.buscarCarroPorId(id);
            if(buscarCarro.isPresent()) {
                return new ResponseEntity<>(buscarCarro.get(),HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/adicionarCarro")
    public ResponseEntity<Carro> adicionarCarro(@RequestBody Carro carro){
        try {
            Carro criarCarro = this.carroService.adicionarNovoCarro(carro);
            return ResponseEntity.status(HttpStatus.CREATED).body(criarCarro);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/atualizarCarro/{id}")
    public ResponseEntity<Carro> atualizarCarro(@PathVariable Long id, @RequestBody Carro carro){
        try{
            Carro carroAtualizado = this.carroService.atualizarCarro(id, carro);
            return new ResponseEntity<>(carroAtualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletarCarro/{id}")
    public ResponseEntity<Void> deletarCarro(@PathVariable Long id){
        try{
            this.carroService.deletarCarro(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}