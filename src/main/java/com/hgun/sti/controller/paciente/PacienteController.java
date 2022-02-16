package com.hgun.sti.controller.paciente;

import com.hgun.sti.components.singletons.ChatSingleton;
import com.hgun.sti.components.singletons.FilaDeEsperaSingleton;
import com.hgun.sti.models.FilaDeEspera;
import com.hgun.sti.models.Mensagem;
import com.hgun.sti.models.Paciente;
import com.hgun.sti.repository.UsuarioRepository;
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
import java.util.Date;

import static com.hgun.sti.components.GetCookie.getCookie;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    public UsuarioRepository usuarioRepository;

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

        model.addAttribute("mensagem", new Mensagem());

        return "chat-paciente.html";
    }

    @PostMapping("/sendMessage")
    public String getMensagem(@ModelAttribute Mensagem mensagem, HttpServletRequest request){

        HttpSession session = request.getSession();
        var paciente = (Paciente)session.getAttribute("paciente");

        var chat = ChatSingleton.getInstance();
        chat.setMensagemPaciente(paciente, mensagem, true);

        return "redirect:/atendente";
    }
}
