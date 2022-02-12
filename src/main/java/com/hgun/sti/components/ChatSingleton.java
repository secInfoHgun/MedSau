package com.hgun.sti.components;

import com.hgun.sti.models.Chat;
import com.hgun.sti.models.Mensagem;

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

}
