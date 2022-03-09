package com.hgun.sti.controller.atendente;

import com.hgun.sti.components.singletons.ChatSingleton;
import com.hgun.sti.components.singletons.FilaDeEsperaSingleton;
import com.hgun.sti.components.singletons.SistemaForaDoArSingleton;
import com.hgun.sti.models.Mensagem;
import com.hgun.sti.repository.ChatRepository;
import com.hgun.sti.repository.MensagemRepository;
import com.hgun.sti.repository.PacienteRepository;
import com.hgun.sti.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import static com.hgun.sti.components.GetCookie.getCookie;

@Controller
@RequestMapping("/atendente")
public class AtendenteController {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Autowired
    public ChatRepository chatRepository;

    @Autowired
    public MensagemRepository mensagemRepository;

    @Autowired
    public PacienteRepository pacienteRepository;

    @GetMapping
    public String atendenteChatPage(Model model){
        var sistemaForaDoAr = SistemaForaDoArSingleton.getInstance();

        if(model.getAttribute("pacienteNome") == null){
            model.addAttribute("pacienteNome", " ");
        }

        model.addAttribute("sistemaForaDoAr", sistemaForaDoAr.sistemaForaDoAr);

        return "chat-atendente.html";
    }

    @RequestMapping(value = "/getPaciente", method = RequestMethod.GET)
    public String getPacienteDaFilaDeEspera(HttpServletRequest request, RedirectAttributes redirectAttributes){
        var chat = ChatSingleton.getInstance();
        var filaDeEspera = FilaDeEsperaSingleton.getInstance();

        filaDeEspera.filaDeEspera.get(0).setEmAtendimento(true);

        var usuario = this.usuarioRepository.findById(
                Long.parseLong(
                        getCookie(request, "userID").getValue()
                )
        ).get();

        chat.chat.inicio =  new Date();
        chat.chat.funcionario = usuario;
        chat.chat.paciente = filaDeEspera.filaDeEspera.get(0).getPaciente();

        chat.setMensagemFuncionario(usuario, "Bem-vindo ao sistema de teleatendimento do Hospital De Guarnição De Natal.\nEstamos dando continuidade ao seu atendimento.");

        redirectAttributes.addFlashAttribute("pacienteNome", filaDeEspera.filaDeEspera.get(0).getPaciente().getNome());

        return "redirect:/atendente";
    }

    @RequestMapping(value = "/finalizarChat", method = RequestMethod.GET)
    public String finalizarChat(){
        var chat = ChatSingleton.getInstance();
        var filaDeEspera = FilaDeEsperaSingleton.getInstance();

        if(chat.mensagems.size() > 1){
            chat.chat.fim = new Date();
            var auxPaciente = pacienteRepository.save(chat.chat.paciente);
            chat.chat.paciente = auxPaciente;
            var auxChat = chatRepository.save(chat.chat);

            for (var mensagem : chat.mensagems) {
                mensagem.idChat = auxChat.id;
                mensagemRepository.save(mensagem);
            }
        }

        if(filaDeEspera.filaDeEspera.size() != 0){
            filaDeEspera.filaDeEspera.remove(0);
        }

        chat.clearChat();

        return "redirect:/atendente";
    }
}
