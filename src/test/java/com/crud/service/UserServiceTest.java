package com.crud.service;
import com.crud.model.User;
import com.crud.repository.HiberUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
class UserServiceTest {

    HiberUserRepository hiberUserRepository = Mockito.mock(HiberUserRepository.class);
    UserService userService = new UserService(hiberUserRepository);

    @Test
    void getAll() {
        List<User> userList = new ArrayList();
        when(hiberUserRepository.getAll()).thenReturn(userList);
        List<User> userEntities = userService.getAll();
        assertEquals(userList, userEntities);
    }
    @Test
    void getById() {
        when(hiberUserRepository.getById(Mockito.anyInt())).thenReturn(new User());
        assertEquals(new User(), userService.getById(1));
    }
    @Test
    void create() {
        User user = new User();
        when(hiberUserRepository.create(user)).thenReturn(user);
        assertEquals(user, userService.create(user));
    }
    @Test
    void update() {
        User user = new User();
        user.setName("test");
        when(hiberUserRepository.create(user)).thenReturn(new User());
        assertEquals(new User(), userService.create(user));
    }
    //@Test
   /* void deleteById() {
        UserService userService1 = Mockito.mock(UserService.class);
        doNothing().when(userService1).deleteById(1);
    }*/
}