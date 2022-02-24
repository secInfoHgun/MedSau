package com.hgun.sti.controller.atendente;

import com.hgun.sti.components.singletons.ChatSingleton;
import com.hgun.sti.components.singletons.FilaDeEsperaSingleton;
import com.hgun.sti.components.singletons.SistemaForaDoArSingleton;
import com.hgun.sti.models.Mensagem;
import com.hgun.sti.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.hgun.sti.components.GetCookie.getCookie;

@Controller
@RequestMapping("/atendente")
public class AtendenteController {

    @Autowired
    public UsuarioRepository usuarioRepository;

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
    public String getPacienteDaFilaDeEspera(HttpServletRequest request, Model model, @ModelAttribute Mensagem mensagem, RedirectAttributes redirectAttributes){
        var chat = ChatSingleton.getInstance();
        var filaDeEspera = FilaDeEsperaSingleton.getInstance();

        filaDeEspera.filaDeEspera.get(0).setEmAtendimento(true);

        var usuario = this.usuarioRepository.findById(
                Long.parseLong(
                        getCookie(request, "userID").getValue()
                )
        ).get();

        chat.setMensagemFuncionario(usuario, "", false);
        redirectAttributes.addFlashAttribute("pacienteNome", filaDeEspera.filaDeEspera.get(0).getPaciente().getNome());

        return "redirect:/atendente";
    }

    @RequestMapping(value = "/finalizarChat", method = RequestMethod.GET)
    public String finalizarChat(){
        var chat = ChatSingleton.getInstance();
        var filaDeEspera = FilaDeEsperaSingleton.getInstance();

        filaDeEspera.filaDeEspera.remove(0);
        chat.clearChat();

        //salvar todos os dados no sistema

        return "redirect:/atendente";
    }
}
