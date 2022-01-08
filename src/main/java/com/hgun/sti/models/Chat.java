package com.hgun.sti.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="chats")
public class Chat {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Date inicio;

    public Date fim;

    @OneToOne
    public Usuario funcionario;

    @OneToOne
    public Paciente paciente;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "chat_mensagens",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "mensagens_id")
    )
    public Set<Mensagem> mensagens;
}
