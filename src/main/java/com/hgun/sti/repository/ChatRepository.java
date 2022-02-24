package com.hgun.sti.repository;


import com.hgun.sti.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository  extends JpaRepository<Chat, Long>{
}
