package com.example.aop_master_project.repositories;

import com.example.aop_master_project.model.entities.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDataRepository extends JpaRepository<UserData, String> {

    @Query("select case when count(userData)> 0 then true else false end from User_Data userData where lower(userData.username) = lower(:username)")
    public boolean usernameExists(@Param("username") String username);

    @Query("select userData from User_Data userData where lower(userData.username) = lower(:username)")
    public UserData userDataForUsername(@Param("username") String username);
}
