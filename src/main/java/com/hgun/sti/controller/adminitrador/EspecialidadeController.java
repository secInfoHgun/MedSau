package com.hgun.sti.controller.adminitrador;

import com.hgun.sti.models.TipoEspecialidade;
import com.hgun.sti.repository.TipoEspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/administrador/especialidade")
public class EspecialidadeController {

    @Autowired
    private TipoEspecialidadeRepository tipoEspecialidadeRepository;

    @GetMapping
    public String especialidadesPage(Model model) {
        var listaTipoEspecialidade = tipoEspecialidadeRepository.listarPorOrgemAlfabetica();

        model.addAttribute("tipoEspecialidade", new TipoEspecialidade());
        model.addAttribute("listaTipoEspecialidade", listaTipoEspecialidade);

        return "administrador/especialidade.html";
    }

    @PostMapping
    public String cadastrarEspecialdade(@ModelAttribute TipoEspecialidade tipoEspecialidade) {

        tipoEspecialidade.setAtiva(true);

        tipoEspecialidadeRepository.save(tipoEspecialidade);

        return "redirect:/administrador/especialidade";
    }

    @GetMapping("/alterar/{id}")
    public String alterarStatus(@PathVariable(name = "id") Long id) {
        var tipoEspecialidade = tipoEspecialidadeRepository.findById(id).get();

        tipoEspecialidade.setAtiva(!tipoEspecialidade.ativa);

        tipoEspecialidadeRepository.save(tipoEspecialidade);

        return "redirect:/administrador/especialidade";
    }
}
