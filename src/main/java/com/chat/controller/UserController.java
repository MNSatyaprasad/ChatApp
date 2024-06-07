
package com.chat.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.chat.model.User;
import com.chat.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@MessageMapping("/user.addUser")
	@SendTo("/user/topic")
	public User adduser(@Payload User user) {
	User saveUser= 	userService.saveUser(user);
		return saveUser;

	}

	@MessageMapping("/user.disconnectUser")
	@SendTo("/user/public")
	public User disconnectUser(@Payload User user) {
		userService.disConnect(user);
		return user;
	}

	@GetMapping("/users")
	public ResponseEntity<Set<User>> findConnectedUsers() {
		List<User> list =userService.findConnectUsers();
	Set<User> set =	list.stream().filter(c -> c.getFullName().equals(c.getFullName())).collect(Collectors.toSet());
		
		return ResponseEntity.ok(set);
	}

}
