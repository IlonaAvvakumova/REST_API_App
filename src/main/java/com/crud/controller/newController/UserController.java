package com.crud.controller.newController;

import com.crud.model.User;
import com.crud.service.UserService;

import javax.servlet.http.HttpServlet;
import java.util.List;

public class UserController extends HttpServlet {

    UserService userService;

    public UserController() {
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public List<User> getAll() {
        return userService.getAll();
    }


    public User getById(Integer id) {
        return userService.getById(id);
    }


    public User create(User user) {

        return userService.create(user);
    }


    public User update(User user) {
        return userService.update(user);
    }


    public void deleteById(Integer id) {

    }
}
