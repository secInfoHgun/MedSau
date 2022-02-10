package com.hgun.sti.controller;

import org.aspectj.bridge.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AllRestController {

    @RequestMapping(value = "/getFila", method = RequestMethod.GET)
    public List<Message> getFila() {
        return new ArrayList<Message>();
    }

    @RequestMapping(value = "/getChat", method = RequestMethod.GET)
    public List<Message> getChat() {
        return new ArrayList<Message>();
    }

    @RequestMapping(value = "/sendMensagem", method = RequestMethod.POST)
    public List<Message> sendMensagem() {
        return new ArrayList<Message>();
    }

}
