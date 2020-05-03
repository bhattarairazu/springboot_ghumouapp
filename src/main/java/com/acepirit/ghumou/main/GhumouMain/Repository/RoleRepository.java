package com.acepirit.ghumou.main.GhumouMain.Repository;

import com.acepirit.ghumou.main.GhumouMain.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findRoleByName(String name);
}
