package com.crud.controller;

import com.crud.model.User;
import com.crud.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/api/v1/users")
public class UserController extends HttpServlet {

    UserService userService = new UserService();

    public UserController() {
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = resp.getWriter();

        if (req.getQueryString().equals("getAll")) {
            List<User> files = userService.getAll();
            printWriter.format("List Users " + files.toString());
            return;
        }

        Object param = req.getParameter("byid");
        if (req.getQueryString().equals("byid=" + param)) {
            User byId = userService.getById(Integer.parseInt(param.toString()));
            printWriter.format("User " + byId);
            return;
        }
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

        if (req.getQueryString().equals("createfile")) {
            String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            User user = new ObjectMapper().readValue(json, User.class);
            userService.create(user);
            return;
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        if (req.getQueryString().equals("update")){
            String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            User user = new ObjectMapper().readValue(json, User.class);
            userService.update(user);
            return;
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)  {

        String d = req.getParameter("deleteId");
        Integer deleteId = Integer.parseInt(d);
        if (req.getQueryString().equals("deleteId=" + deleteId)) {
            userService.deleteById(deleteId);
            return;
        }
    }
}
