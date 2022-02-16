package com.hgun.sti.controller;

import com.hgun.sti.components.singletons.ChatSingleton;
import com.hgun.sti.components.singletons.FilaDeEsperaSingleton;
import com.hgun.sti.models.FilaDeEspera;
import com.hgun.sti.models.Mensagem;
import com.hgun.sti.models.Paciente;
import com.hgun.sti.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.hgun.sti.components.GetCookie.getCookie;

@RestController
public class AllRestController {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @RequestMapping(value = "/getFila", method = RequestMethod.GET)
    public List<FilaDeEspera> getFila() {
        var filaDeEspera = FilaDeEsperaSingleton.getInstance();
        return filaDeEspera.filaDeEspera;
    }

    @RequestMapping(value = "/getChat", method = RequestMethod.GET)
    public List<Mensagem> getChat() {
        var chat = ChatSingleton.getInstance();
        return chat.mensagems;
    }

    @RequestMapping(value = "/sendMensagem", method = RequestMethod.POST)
    public void sendMensagem(@RequestBody String conteudo, @RequestBody Boolean isPaciente, HttpServletRequest request) {

        var chat = ChatSingleton.getInstance();

        if(isPaciente){
            HttpSession session = request.getSession();
            var paciente = (Paciente)session.getAttribute("paciente");

            System.out.println(conteudo);
//            chat.setMensagemPaciente(paciente, mensagem, true);
        }else{
            var usuario = this.usuarioRepository.findById(
                    Long.parseLong(
                            getCookie(request, "userID").getValue()
                    )
            ).get();

            System.out.println(conteudo);
//            chat.setMensagemFuncionario(usuario, mensagem, true);
        }
    }

//    @RequestMapping(value = "/getPosicaoNaFila", method = RequestMethod.GET)
//    public int getPosicaoNaFila(@RequestBody Paciente paciente) { return 0; }
}
