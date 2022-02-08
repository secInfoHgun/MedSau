package com.hgun.sti.controller;

import com.hgun.sti.models.Paciente;
import com.hgun.sti.repository.TipoEspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private TipoEspecialidadeRepository tipoEspecialidadeRepository;

    @GetMapping
    public String initialPage(Model model){
        var listaTipoEspecialidade = tipoEspecialidadeRepository.listarEspecialidadesAtivas();

        model.addAttribute("paciente", new Paciente());
        model.addAttribute("listaEspecialidades",listaTipoEspecialidade );
        return "home.html";
    }

}
