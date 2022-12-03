package com.crud.service;

import com.crud.model.User;
import com.crud.repository.HiberUserRepository;

import java.util.List;

public class UserService {

    HiberUserRepository hiberUserRepository = new HiberUserRepository();

    public UserService() {
    }

    public UserService(HiberUserRepository hiberUserRepository) {
        this.hiberUserRepository = hiberUserRepository;
    }

    public List<User> getAll() {
        return hiberUserRepository.getAll();
    }


    public User getById(Integer id) {
        return hiberUserRepository.getById(id);
    }


    public User create(User user) {
        return hiberUserRepository.create(user);
    }


    public User update(User user) {
        return hiberUserRepository.update(user);
    }


    public void deleteById(Integer id) {
hiberUserRepository.deleteById(id);
    }
}
