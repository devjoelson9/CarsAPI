package br.com.calculos.calculos.controller;

import br.com.calculos.calculos.entity.Carro;
import br.com.calculos.calculos.service.CarroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class Thyme {

    private final CarroService carroService;

    @GetMapping("/lista")
    public String listarTodosCarros(Model model) {
        try {
            List<Carro> listarCarros = this.carroService.buscarCarros();
            model.addAttribute("carros", listarCarros);
            model.addAttribute("novoCarroForm", new Carro());
            return "lista";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erro ao carregar os carros: " + e.getMessage());
            return "erro";
        }
    }

    @GetMapping("/carros/{id}/detalhes")
    public String verDetalhes(@PathVariable Long id, Model model) {
        Optional<Carro> carro = carroService.buscarCarroPorId(id);
        if (carro.isPresent()) {
            model.addAttribute("carroDetalhe", carro.get());
            return "carroDetalhes"; // novo template
        } else {
            model.addAttribute("errorMessage", "Carro não encontrado.");
            return "erro";
        }
    }

    @GetMapping("/listaid/{id}")
    public final String listarCarro(@PathVariable Long id, Model model){
        Optional<Carro> carro = this.carroService.buscarCarroPorId(id);

        if(carro.isPresent()) {
            model.addAttribute("carroDetalhe", carro.get());
            List<Carro> listarCarros = this.carroService.buscarCarros();
            model.addAttribute("carros", listarCarros);
            model.addAttribute("novoCarroForm", new Carro());
            return "lista";
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrado!!");
        }
    }

    @GetMapping("/adicionarCarro")
    public final String mostrarFormularioAdicionarCarro(Model model) {
        model.addAttribute("novoCarroForm", new Carro());
        return "lista";
    }

    @PostMapping("/adicionarCarro")
    public final String adicionarCarro(Carro carro, Model model) {
        try {
            Carro carroSalvo = this.carroService.adicionarNovoCarro(carro);
            model.addAttribute("successMessage", "Carro '" + carroSalvo.getModelo() + "' adicionado com sucesso!");
            model.addAttribute("carros", this.carroService.buscarCarros());
            model.addAttribute("novoCarroForm", new Carro());
            return "lista";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erro ao adicionar o carro: " + e.getMessage());
            return "lista";
        }
    }

    @PostMapping("/atualizarCarro/{id}")
    public final String atualizarCarro(@PathVariable long id, @ModelAttribute Carro carro, RedirectAttributes redirectAttrs){
        try{
            this.carroService.atualizarCarro(id, carro);
            redirectAttrs.addFlashAttribute("successMessage", "Carro atualizado com sucesso!");
            return "redirect:/lista";
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("errorMessage", "Erro ao atualizar o carro: " + e.getMessage());
            return "erro";
        }
    }

    @PostMapping("/excluirCarro/{id}")
    public String excluirCarro(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        try {
            carroService.deletarCarro(id);
            redirectAttrs.addFlashAttribute("successMessage", "Carro excluído com sucesso!");
            return "redirect:/lista";
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("errorMessage", "Erro ao excluir o carro: " + e.getMessage());
            return "redirect:/carros/" + id + "/detalhes";
        }
    }

    @GetMapping("/pesquisarCarro")
    public String listarCarrosFiltrados(@RequestParam(name="modelo", required = false) String modelo, Model model) {
        List<Carro> carrosFiltrados = carroService.buscarCarrosPorModelo(modelo);
        model.addAttribute("carros", carrosFiltrados);
        model.addAttribute("novoCarroForm", new Carro());
        model.addAttribute("modeloBusca", modelo);
        return "lista";
    }
}