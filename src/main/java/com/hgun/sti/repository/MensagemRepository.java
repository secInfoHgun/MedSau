package com.hgun.sti.repository;

import com.hgun.sti.models.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    @Query("SELECT m FROM Mensagem m WHERE m.idChat = :idChat order by m.id asc")
    List<Mensagem> getMensagensByChatId(@Param("idChat") Long idChat);
}
