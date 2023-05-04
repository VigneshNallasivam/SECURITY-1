package com.spring.basics.repository;

import com.spring.basics.entry.ERole;
import com.spring.basics.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>
{
    Optional<Role> findByName(ERole name);
}
