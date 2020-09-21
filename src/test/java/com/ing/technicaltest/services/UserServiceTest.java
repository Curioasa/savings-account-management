package com.ing.technicaltest.services;

import com.ing.technicaltest.dao.UserDao;
import com.ing.technicaltest.domain.User;
import com.ing.technicaltest.exceptions.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @InjectMocks
    private UserService victim;

    @Mock
    private UserDao userDao;

    @Test
    public void retrieveUser() {
        when(userDao.findByUsername(anyString())).thenReturn(new User());

        victim.retrieveUser("test_user");

        verify(userDao).findByUsername(anyString());
        verifyNoMoreInteractions(userDao);
    }

    @Test(expected = UserNotFoundException.class)
    public void retrieveUserNoResultFound() {
        when(userDao.findByUsername(anyString())).thenReturn(null);

        victim.retrieveUser("test_user");
    }
}