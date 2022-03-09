package com.hgun.sti.components.singletons;

import com.hgun.sti.models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChatSingleton {

    private static ChatSingleton chatSingleton;
    public ArrayList<Mensagem> mensagems;
    public Chat chat;

    private ChatSingleton() {
        this.mensagems = new ArrayList<Mensagem>();
        this.chat = new Chat();
    }

    public static ChatSingleton getInstance() {
        if (chatSingleton == null)
            chatSingleton = new ChatSingleton();
        return chatSingleton;
    }

    public void clearChat(){
        this.mensagems = new ArrayList<Mensagem>();
        this.chat = new Chat();
    }

    private String getHoraAtual(){
        var horaAtual = LocalDateTime.now();
        String horaFormatada = new String();

        var horas = horaAtual.getHour();
        var minutos = horaAtual.getMinute();

        if(horas < 10){
            horaFormatada += "0" + horas;
        }else{
            horaFormatada += horas;
        }

        horaFormatada += ":";

        if(minutos < 10){
            horaFormatada += "0" + minutos;
        }else{
            horaFormatada += minutos;
        }

       return  horaFormatada;
    }

    public void setMensagemFuncionario(Usuario usuario,String conteudo){

        var mensagem = new Mensagem();
        mensagem.setRemetente(usuario.getLogin());
        mensagem.setConteudo(conteudo);
        mensagem.setHora(getHoraAtual());

        this.mensagems.add(mensagem);
    }

    public void setMensagemPaciente(Paciente paciente, String conteudo){

        var mensagem = new Mensagem();

        mensagem.setRemetente(paciente.getNome());
        mensagem.setConteudo(conteudo);
        mensagem.setHora(getHoraAtual());

        this.mensagems.add(mensagem);
    }



}
