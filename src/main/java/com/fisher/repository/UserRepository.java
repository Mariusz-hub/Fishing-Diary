package com.fisher.repository;

import com.fisher.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.email=?1" )
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User getUserByUsername(@Param("email") String email);

    @Modifying
    @Query(value = "delete from User b where b.id = :id")
    int deleteUserById(long id);

    @Query ("select s from User  s where s.email = ?1")
    Optional <User> findUserByEmail(String email);
}
