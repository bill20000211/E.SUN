package com.javabycode.springboot.repository;

import com.javabycode.springboot.model.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<users, Long> {
    
    @Query("SELECT u FROM users u WHERE u.user_id = :userId")
    users findByuser_id(@Param("userId") int userid);
    @Query("SELECT u FROM users u WHERE u.user_name = :userName")
    users findByuser_name(@Param("userName") String username);
    @Query("SELECT u FROM users u WHERE u.password_hash = :passwordHash")
    users findBypassword_hash(@Param("passwordHash") String passwordhash);
    @Query("SELECT u FROM users u WHERE u.phone_number = :phoneNumber")
    users findByphone_number(@Param("phoneNumber") String phonenumber);
    
}