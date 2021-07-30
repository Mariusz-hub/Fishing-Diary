package com.fisher.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

    @Query("SELECT u FROM UserRole u WHERE u.name=?1" )
    UserRole findByName(String name);
}
