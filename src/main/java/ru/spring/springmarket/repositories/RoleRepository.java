package ru.spring.springmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import ru.spring.springmarket.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
