package com.example.demo.Repositorys;

import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<Users, String> {
    // list all users
    @Query(value = "select * from users", nativeQuery = true)
    List<Users> listOfTheUsers();
}
