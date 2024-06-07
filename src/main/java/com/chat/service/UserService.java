
package com.chat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chat.model.Status;
import com.chat.model.User;
import com.chat.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepo;
	@Transactional
	public User saveUser(User user) {
	Optional<User> opt	= userRepo.findByFullName(user.getFullName());
	if(!opt.isPresent()) {
		user.setStatus(Status.ONLINE);
		userRepo.save(user);
		return user;
	}else {
		User u = opt.get();
		u.setStatus(Status.ONLINE);
		return u;
	}
	
	}
	@Transactional
	public void disConnect(User user) {
		Optional<User> opt= userRepo.findByFullName(user.getFullName());
		if (!opt.isPresent()) {
			var storedUser = opt.get();
			storedUser.setStatus(Status.OFFLINE);
			userRepo.save(storedUser);
		}
	}
	public List<User> findConnectUsers() {
		
		return userRepo.findAllByStatus(Status.ONLINE);
	}
}
