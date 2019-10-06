package com.geekbrains.septembermarket.repositories;

import com.geekbrains.septembermarket.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findOneByName(String name);
}
