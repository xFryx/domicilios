package com.Backendpw.security.respository;
//tomado de https://www.bezkoder.com/spring-boot-jwt-authentication/

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Backendpw.security.models.ERole;
import com.Backendpw.security.models.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
