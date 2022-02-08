package com.hgun.sti.controller.paciente;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/filadeespera")
public class FilaDeEsperaController {
    @GetMapping
    public String form(Model model){

        model.addAttribute("posicaoNaFila", 0);

        return "fila-de-espera.html";
    }

    @GetMapping("/getposicao")
    public int getPosicaoNaFila(){
        return 25;
    }
}
