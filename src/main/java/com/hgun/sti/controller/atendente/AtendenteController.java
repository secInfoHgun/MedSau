package com.hgun.sti.controller.atendente;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/atendente")
public class AtendenteController {
    @GetMapping
    public String form(Model model){
        return "chat-atendente.html";
    }
}
