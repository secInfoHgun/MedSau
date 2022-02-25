package com.hgun.sti.controller.adminitrador;

import com.hgun.sti.components.ExportPDF;
import com.hgun.sti.models.Chat;
import com.hgun.sti.models.Paciente;
import com.hgun.sti.repository.ChatRepository;
import com.hgun.sti.repository.MensagemRepository;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/administrador/chat")
public class ChatController {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MensagemRepository mensagemRepository;

    @GetMapping
    public String pageListChat(Model model, HttpServletRequest request){

        if(model.getAttribute("chats") == null){
            model.addAttribute("chats", new ArrayList<Chat>());
        }

        if(model.getAttribute("paciente") == null){
            model.addAttribute("paciente", new Paciente());
        }

        return "administrador/list-chat.html";
    }

    @PostMapping("/filter")
    public String filteListChats(@ModelAttribute Paciente paciente, RedirectAttributes redirectAttributes){

        var chatsDB = chatRepository.getChatByPreccpAndData(paciente.preccp);

        redirectAttributes.addFlashAttribute("chats", chatsDB);
        redirectAttributes.addFlashAttribute("paciente", paciente);

        return "redirect:/administrador/chat";
    }

    @GetMapping("/view/{id}")
    public String pageViewChat(@PathVariable(name = "id") Long id, Model model){

        model.addAttribute("id", id);

        return "administrador/view-chat.html";
    }

    @GetMapping("/export/{id}")
    public void chatExport(@PathVariable(name = "id") Long id, HttpServletResponse response) throws DocumentException, IOException {

        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Chat_" + id +".pdf";
        response.setHeader(headerKey, headerValue);

        var chat = chatRepository.findById(id).get();
        var mensagem = mensagemRepository.getMensagensByChatId(id);

        var exporter = new ExportPDF(chat, mensagem);

        exporter.export(response);
    }
}
