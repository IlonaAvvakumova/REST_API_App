package com.crud.controller;

import com.crud.model.User;
import com.crud.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/api/v1/users/*")
public class UserController extends HttpServlet {

    UserService userService;

    public UserController() {
        userService = new UserService();
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = resp.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        int index = req.getRequestURI().lastIndexOf("/");
        String idAsString = req.getRequestURI().substring(index + 1);
        if (idAsString != null && !idAsString.isEmpty()) {
            Integer id = Integer.parseInt(idAsString);
            User byId = userService.getById(id);
            String jsonString = objectMapper.writeValueAsString(byId);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            printWriter.print(jsonString);
            printWriter.flush();
        } else {
            List<User> userEntities = userService.getAll();
            for (User u : userEntities
            ) {
                String jsonString = objectMapper.writeValueAsString(u);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                printWriter.print(jsonString);
                printWriter.flush();
            }
        }
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getRequestURI().equals("/REST_API_App/api/v1/users/create")) {
            String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            User users = new ObjectMapper().readValue(json, User.class);
            userService.create(users);
            return;
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getRequestURI().equals("/REST_API_App/api/v1/users/update")) {
            String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            User user = new ObjectMapper().readValue(json, User.class);
            userService.update(user);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        int index = req.getRequestURI().lastIndexOf("/");
        String s = req.getRequestURI().substring(index + 1);
        Integer id = Integer.parseInt(s);
        if (req.getRequestURI().equals("/REST_API_App/api/v1/users/delete/" + id)) {
            userService.deleteById(id);
        }
    }
}
