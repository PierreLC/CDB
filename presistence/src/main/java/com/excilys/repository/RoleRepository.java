package com.excilys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
