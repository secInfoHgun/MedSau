package com.hgun.sti.controller;

import com.hgun.sti.components.singletons.ChatSingleton;
import com.hgun.sti.components.singletons.FilaDeEsperaSingleton;
import com.hgun.sti.components.singletons.SistemaForaDoArSingleton;
import com.hgun.sti.models.FilaDeEspera;
import com.hgun.sti.models.Mensagem;
import com.hgun.sti.models.Paciente;
import com.hgun.sti.repository.MensagemRepository;
import com.hgun.sti.repository.TipoEspecialidadeRepository;
import com.hgun.sti.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.hgun.sti.components.GetCookie.getCookie;

@RestController
public class AllRestController {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Autowired
    public TipoEspecialidadeRepository tipoEspecialidadeRepository;

    @Autowired
    public MensagemRepository mensagemRepository;

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

    @RequestMapping(value = "/getChat/{id}", method = RequestMethod.GET)
    public List<Mensagem> getChatById(@PathVariable(name = "id") Long id) {
        var mensagens = mensagemRepository.getMensagensByChatId(id);
        return mensagens;
    }

    @RequestMapping(value = "/postMessage",  method = RequestMethod.GET)
    public boolean sendMensagem(HttpServletRequest request) {

        var chat = ChatSingleton.getInstance();
        var isPaciente = request.getHeader("ispaciente").equals("true");
        var message = request.getHeader("message");

        if(isPaciente){
            HttpSession session = request.getSession();
            var paciente = (Paciente)session.getAttribute("paciente");

            var tipoEspecialidade = tipoEspecialidadeRepository.findById(paciente.getTipoEspecialidade().getId()).get();

            chat.setMensagemPaciente(paciente, message, true, tipoEspecialidade);
        }else{
            var usuario = this.usuarioRepository.findById(
                    Long.parseLong(
                            getCookie(request, "userID").getValue()
                    )
            ).get();

            chat.setMensagemFuncionario(usuario, message, true);
        }
        return true;
    }


    @RequestMapping(value = "/setSistemaForaDoAr", method = RequestMethod.GET)
    public void setSistemaAtivo() {
        var sistemaForaDoAr = SistemaForaDoArSingleton.getInstance();
        sistemaForaDoAr.alterarSistemaForaDoAr();
    }

}
