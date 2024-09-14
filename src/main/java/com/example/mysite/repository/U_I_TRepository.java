package com.example.mysite.repository;

import com.example.mysite.classes.U_I_T;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface U_I_TRepository extends JpaRepository<U_I_T, Integer> {
    @Query("SELECT u FROM U_I_T u WHERE u.user.user_id = ?1")
    List<U_I_T> findByUser_id(Integer user_id);

}
