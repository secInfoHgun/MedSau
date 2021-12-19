package com.hgun.sti.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="chats")
public class Chat {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Date inicio;

    public Date fim;

    @OneToOne
    public Usuario funcionario;

    @OneToOne
    public Paciente paciente;

//    public ArrayList<String> mensagens;
}
