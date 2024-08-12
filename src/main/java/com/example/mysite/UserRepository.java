package com.example.mysite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    // 根据用户名查询用户
    User findByUsername(String username);

    // 根据用户名和邮箱查询用户
    User findByUsernameAndEmail(String username, String email);

    // 如果需要使用原生SQL查询，可以使用 @Query 注解
    // 例如，查询用户名为指定名称的用户数量
    @Query(value = "SELECT COUNT(*) FROM users WHERE username = :username", nativeQuery = true)
    int countByUsername(@Param("username") String username);

}