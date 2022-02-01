package com.hgun.sti.controller;

import com.hgun.sti.models.Paciente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String initialPage(Model model){
        model.addAttribute("paciente", new Paciente());
        return "home.html";
    }

}
