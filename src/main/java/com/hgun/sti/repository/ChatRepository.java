package com.hgun.sti.repository;


import com.hgun.sti.models.Chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository  extends JpaRepository<Chat, Long>{
    @Query("SELECT c FROM Chat c WHERE c.paciente.preccp = :preccp order by c.inicio asc")
    List<Chat> getChatByPreccpAndData(@Param("preccp") String preccp);
}
