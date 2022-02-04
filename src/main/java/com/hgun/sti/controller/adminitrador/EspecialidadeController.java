package com.hgun.sti.controller.adminitrador;

import com.hgun.sti.models.Paciente;
import com.hgun.sti.models.TipoEspecialidade;
import com.hgun.sti.repository.TipoEspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrador/especialidade")
public class EspecialidadeController {

    @Autowired
    private TipoEspecialidadeRepository tipoEspecialidadeRepository;

    @GetMapping
    public String especialidadesPage(Model model) {
        var listaTipoEspecialidade = tipoEspecialidadeRepository.findAll();

        model.addAttribute("tipoEspecialidade", new TipoEspecialidade());

        model.addAttribute("listaTipoEspecialidade", listaTipoEspecialidade);

        return "administrador/especialidade.html";
    }

    @PostMapping
    public String cadastrarEspecialdade(@ModelAttribute TipoEspecialidade tipoEspecialidade) {
        return "redirect:/administrado/especialidade";
    }
}
