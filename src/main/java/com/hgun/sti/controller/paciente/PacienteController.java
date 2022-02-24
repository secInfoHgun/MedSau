package com.hgun.sti.controller.paciente;

import com.hgun.sti.components.singletons.ChatSingleton;
import com.hgun.sti.components.singletons.FilaDeEsperaSingleton;
import com.hgun.sti.models.FilaDeEspera;
import com.hgun.sti.models.Paciente;
import com.hgun.sti.repository.TipoEspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    public TipoEspecialidadeRepository tipoEspecialidadeRepository;

    @PostMapping
    public String inserirPacienteNaFilaDeEspera(@ModelAttribute Paciente paciente, HttpServletRequest request){
        var session = request.getSession();
        var fila = FilaDeEsperaSingleton.getInstance();

        fila.filaDeEspera.add(new FilaDeEspera(paciente, false));
        session.setAttribute("paciente", paciente);

        return "redirect:/filadeespera";
    }

    @GetMapping("/chat")
    public String getChat(HttpServletRequest request){

        var chat = ChatSingleton.getInstance();
        var session = request.getSession();
        var paciente = (Paciente)session.getAttribute("paciente");

        if(paciente == null){
            return "redirect:/";
        }

        var tipoEspecialidade = tipoEspecialidadeRepository.findById(paciente.getTipoEspecialidade().getId()).get();

        chat.setMensagemPaciente(paciente, "", false, tipoEspecialidade);

        return "chat-paciente.html";
    }
}
