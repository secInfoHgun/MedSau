package com.hgun.sti.controller.paciente;

import com.hgun.sti.models.Paciente;
import com.hgun.sti.repository.TipoEspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private TipoEspecialidadeRepository tipoEspecialidadeRepository;

    @PostMapping
    public String validar(@ModelAttribute Paciente paciente, RedirectAttributes redirectAttributes){

        //inserir o paciente na fila de espera e salvar no no banco de dados

        return "redirect:/filadeespera";
    }
}
