package com.ing.technicaltest.dao;


import com.ing.technicaltest.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
