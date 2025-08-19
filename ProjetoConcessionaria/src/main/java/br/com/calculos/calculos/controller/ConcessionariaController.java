package br.com.calculos.calculos.controller;

import br.com.calculos.calculos.entity.Concessionaria;
import br.com.calculos.calculos.service.ConcessionariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/concessionaria")
public class ConcessionariaController {

    private ConcessionariaService concessionariaService;

    @GetMapping("/listaDeConcessionarias")
    public ResponseEntity<List<Concessionaria>> listarCocessionaria(){
        try{
            List<Concessionaria> concesionarias = this.concessionariaService.listarConcessionaria();
            return new ResponseEntity<>(concesionarias, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscarConcessionarias/{id}")
    public ResponseEntity<Concessionaria> concessionariaPorId(@PathVariable Long id){
        try{
            Optional<Concessionaria> buscarConcessionaria = this.concessionariaService.concessionariaPorId(id);
            return new ResponseEntity<>(buscarConcessionaria.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/adicionarConcessionaria")
    public ResponseEntity<Concessionaria> adicionarConcessionarias(@RequestBody Concessionaria concessionaria){
        try{
            Concessionaria adicionarConcessionaria = this.concessionariaService.adicionarConcessionaria(concessionaria);
            return new ResponseEntity<>(adicionarConcessionaria, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/atualizarCarro/{id}")
    public ResponseEntity<Concessionaria> atualizarCarro(@PathVariable Long id, @RequestBody Concessionaria concessionaria){
        try{
            Concessionaria atualizarCarro = this.concessionariaService.atualizarConcessionaria(id, concessionaria);
            return new ResponseEntity<>(atualizarCarro, HttpStatus.OK);
        } catch (Exception execption) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
