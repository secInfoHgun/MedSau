package com.hgun.sti.controller.atendente;

import com.hgun.sti.components.SistemaForaDoArSingleton;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/atendente")
public class AtendenteController {
    @GetMapping
    public String atendenteChatPage(Model model){
        var sistemaForaDoAr = SistemaForaDoArSingleton.getInstance();

        model.addAttribute("sistemaForaDoAr", sistemaForaDoAr.sistemaForaDoAr);

        return "chat-atendente.html";
    }

    @GetMapping("/sistemaForaDoAr")
    public String form(Model model){
        var sistemaForaDoAr = SistemaForaDoArSingleton.getInstance();

        sistemaForaDoAr.alterarSistemaForaDoAr();

        return "redirect:/atendente";
    }
}
