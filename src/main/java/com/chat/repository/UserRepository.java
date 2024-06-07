
package com.chat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.model.Status;
import com.chat.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAllByStatus(Status staus);

	Optional<User> findByFullName(String fullName);

}
