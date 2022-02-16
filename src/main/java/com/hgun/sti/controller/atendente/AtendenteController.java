package com.hgun.sti.controller.atendente;

import com.hgun.sti.components.singletons.ChatSingleton;
import com.hgun.sti.components.singletons.SistemaForaDoArSingleton;
import com.hgun.sti.models.Mensagem;
import com.hgun.sti.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

        model.addAttribute("sistemaForaDoAr", sistemaForaDoAr.sistemaForaDoAr);
        model.addAttribute("mensagem", new Mensagem());

        return "chat-atendente.html";
    }

    @GetMapping("/sistemaForaDoAr")
    public String form(){
        var sistemaForaDoAr = SistemaForaDoArSingleton.getInstance();

        sistemaForaDoAr.alterarSistemaForaDoAr();

        return "redirect:/atendente";
    }

    @PostMapping("/sendMessage")
    public String getMensagem(@ModelAttribute Mensagem mensagem, HttpServletRequest request){

        var usuario = this.usuarioRepository.findById(
                Long.parseLong(
                        getCookie(request, "userID").getValue()
                )
        ).get();

        var chat = ChatSingleton.getInstance();

        chat.setMensagemFuncionario(usuario, mensagem, true);

        return "redirect:/atendente";
    }
}
