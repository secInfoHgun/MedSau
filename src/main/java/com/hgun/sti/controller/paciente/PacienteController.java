package com.hgun.sti.controller.paciente;

import com.hgun.sti.components.FilaDeEsperaSingleton;
import com.hgun.sti.models.FilaDeEspera;
import com.hgun.sti.models.Paciente;
import com.hgun.sti.repository.TipoEspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @PostMapping
    public String inserirPacienteNaFilaDeEspera(@ModelAttribute Paciente paciente, HttpServletRequest request, RedirectAttributes redirectAttributes){

        var fila = FilaDeEsperaSingleton.getInstance();
        fila.filaDeEspera.add(new FilaDeEspera(paciente, false));

        HttpSession session = request.getSession();
        session.setAttribute("paciente", paciente);
//        session.setAttribute("primeiraMensagemPaciente", true);

        return "redirect:/filadeespera";
    }

    @GetMapping("/chat")
    public String getChat(Model model){

        //inserir o paciente na fila de espera e salvar no no banco de dados

        return "chat-paciente.html";
    }
}
