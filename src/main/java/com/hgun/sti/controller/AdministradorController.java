package com.hgun.sti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    @GetMapping
    public String homeadministrador(){
        return "dashboard.html";
    }

}
