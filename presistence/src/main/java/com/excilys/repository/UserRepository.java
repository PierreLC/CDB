package com.excilys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

//	Page<User> findByNameContaining(String searchUser, Pageable pageable);

	User findFirstByUsername(String search);
}
