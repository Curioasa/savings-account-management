package com.ing.technicaltest.services;

import com.ing.technicaltest.dao.UserDao;
import com.ing.technicaltest.domain.User;
import com.ing.technicaltest.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User retrieveUser(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("The user with the specified username is not present in the DB.");
        }
        return user;
    }
}
