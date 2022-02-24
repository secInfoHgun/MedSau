package com.hgun.sti.controller;

import com.hgun.sti.components.singletons.SistemaForaDoArSingleton;
import com.hgun.sti.models.Paciente;
import com.hgun.sti.repository.TipoEspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private TipoEspecialidadeRepository tipoEspecialidadeRepository;

    @GetMapping
    public String initialPage(Model model){
        var sistemaForaDoAr = SistemaForaDoArSingleton.getInstance();
        var listaTipoEspecialidade = tipoEspecialidadeRepository.listarEspecialidadesAtivas();

        if(model.getAttribute("finalizouChat") == null){
            model.addAttribute("finalizouChat", false);
        }

        model.addAttribute("paciente", new Paciente());
        model.addAttribute("listaEspecialidades",listaTipoEspecialidade );
        model.addAttribute("sistemaForaDoAr", sistemaForaDoAr.sistemaForaDoAr);
        return "home.html";
    }

    @GetMapping("/finalizou")
    public String finalizou(RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("finalizouChat", true);

        return "redirect:/";
    }

}
