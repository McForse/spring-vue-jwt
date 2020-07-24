package com.mcforse.backend.repository;

import com.mcforse.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username) throws UsernameNotFoundException;

	Boolean existsByUsername(String username);

	@Transactional
	void deleteByUsername(String username);

}
