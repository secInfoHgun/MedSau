package com.hgun.sti.controller;


import com.hgun.sti.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.hgun.sti.components.GetCookie.getCookie;

@Controller
public class LoginController {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }

    @GetMapping("/entrar")
    public String entra(HttpServletRequest request){
        var usuario = this.usuarioRepository.findById(
                Long.parseLong(
                        getCookie(request, "userID").getValue()
                )
        ).get();

        var roles = usuario.getRoles();

        for (var role : roles) {
            if(role.name.equals("ADMINISTRADOR")){
                return "redirect:/administrador/especialidade";
            }else if(role.name.equals("ATENDENTE")){
                return "redirect:/atendente";
            }
        }
        return "redirect:/";
    }
}
