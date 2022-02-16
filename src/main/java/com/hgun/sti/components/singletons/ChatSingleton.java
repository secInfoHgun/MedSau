package com.hgun.sti.components.singletons;

import com.hgun.sti.models.Chat;
import com.hgun.sti.models.Mensagem;
import com.hgun.sti.models.Paciente;
import com.hgun.sti.models.Usuario;
import com.hgun.sti.repository.TipoEspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.hgun.sti.components.GetCookie.getCookie;

public class ChatSingleton {

    @Autowired
    private TipoEspecialidadeRepository tipoEspecialidadeRepository;

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

    public void setMensagemFuncionario(Usuario usuario,Mensagem mensagem, Boolean veioDoForm){

        mensagem.setRemetente(usuario.getLogin());
        mensagem.setHora(getHoraAtual());

        if(!veioDoForm){
            mensagem.setConteudo("Bem-vindo ao sistema de teleatendimento do Hospital De Guarnição De Natal.\nEstamos dando continuidade ao seu atendimento.");
        }

        this.mensagems.add(mensagem);
    }

    public void setMensagemPaciente(Paciente paciente, Mensagem mensagem, Boolean veioDoForm){

        var tipoEspecialidade = tipoEspecialidadeRepository.findById(paciente.getTipoEspecialidade().getId()).get();

        mensagem.setRemetente(paciente.getNome());
        mensagem.setHora(getHoraAtual());

        if(!veioDoForm){
            mensagem.setConteudo(
                    "Nome: " + paciente.getNome() +
                    "\nIdade: " + paciente.getIdade() +
                    "\nSexo: " + (paciente.getSexo().equals('M') ? "Masculino" : "Feminino") +
                    "\nTelefone: " + paciente.getTelefone() +
                    "\nPRECCP: " + paciente.getPreccp() +
                    "\nProntuário: " + paciente.getProntuario() +
                    "\nEspecialidade médica: " + tipoEspecialidade.getNome()
            );
        }

        this.mensagems.add(mensagem);
    }



}
