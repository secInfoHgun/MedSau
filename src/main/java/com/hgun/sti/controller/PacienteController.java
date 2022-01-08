package com.hgun.sti.controller;

import com.hgun.sti.controller.validators.PacienteValidator;
import com.hgun.sti.models.Paciente;
import com.hgun.sti.models.erros.PacienteError;
import com.hgun.sti.repository.TipoEspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private TipoEspecialidadeRepository tipoEspecialidadeRepository;

    @GetMapping
    public String form(Model model){

        if(model.getAttribute("paciente") == null || model.getAttribute("erros") == null){
            model.addAttribute("paciente", new Paciente());
            model.addAttribute("erros", new PacienteError());
        }

        model.addAttribute("listaEspecialidades", tipoEspecialidadeRepository.listarEspecialidadesAtivas());

        return "formulario-paciente.html";
    }

    @PostMapping
    public String validar(@ModelAttribute Paciente paciente, RedirectAttributes redirectAttributes){

        var erros = PacienteValidator.validarPaciente(paciente);

        if(!erros.isEmpty()){
            redirectAttributes.addFlashAttribute("erros", erros);
            redirectAttributes.addFlashAttribute("paciente", paciente);
            return "redirect:/";
        }else{

            //inserir o paciente na fila de espera e salvar no no banco de dados

            return "redirect:/filadeespera";
        }

    }
}
