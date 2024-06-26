package com.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.model.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {
 
	List<ChatMessage> findByChatId(String chatId);
}
