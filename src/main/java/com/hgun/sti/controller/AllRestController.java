package com.hgun.sti.controller;

import com.hgun.sti.components.FilaDeEsperaSingleton;
import com.hgun.sti.models.FilaDeEspera;
import com.hgun.sti.models.Paciente;
import org.aspectj.bridge.Message;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AllRestController {

    @RequestMapping(value = "/getFila", method = RequestMethod.GET)
    public List<FilaDeEspera> getFila() {
        var filaDeEspera = FilaDeEsperaSingleton.getInstance();
        return filaDeEspera.filaDeEspera;
    }

    @RequestMapping(value = "/getChat", method = RequestMethod.GET)
    public List<Message> getChat() {
        return new ArrayList<Message>();
    }

//    @RequestMapping(value = "/sendMensagem", method = RequestMethod.POST)
//    public void sendMensagem(@RequestBody Message message) {}

//    @RequestMapping(value = "/getPosicaoNaFila", method = RequestMethod.GET)
//    public int getPosicaoNaFila(@RequestBody Paciente paciente) { return 0; }
}
