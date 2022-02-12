package com.hgun.sti.controller.paciente;

import com.hgun.sti.components.FilaDeEsperaSingleton;
import com.hgun.sti.components.SistemaForaDoArSingleton;
import com.hgun.sti.models.Paciente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/filadeespera")
public class FilaDeEsperaController {
    @GetMapping
    public String filaDeEsperaPage(Model model, HttpServletRequest request){

        HttpSession session = request.getSession();

        var filaDeEspera = FilaDeEsperaSingleton.getInstance();
        var paciente = (Paciente)session.getAttribute("paciente");
        var sistemaForaDoAr = SistemaForaDoArSingleton.getInstance();

        int posicaoNaFila = 0;

        for (var noFila: filaDeEspera.filaDeEspera) {
            posicaoNaFila++;
            if(noFila.getPaciente() == paciente){
                if(noFila.isEmAtendimento()){
                    return "redirect:/paciente/chat";
                }
                break;
            }
        }

        model.addAttribute("posicaoNaFila", posicaoNaFila);
        model.addAttribute("sistemaForaDoAr", sistemaForaDoAr.sistemaForaDoAr);

        return "fila-de-espera.html";
    }
}
