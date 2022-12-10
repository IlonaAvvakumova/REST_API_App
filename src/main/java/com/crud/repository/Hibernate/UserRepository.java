package com.crud.repository.Hibernate;

import com.crud.model.User;

import java.util.List;

public interface UserRepository {
    List<User> getAll();

    User getById(Integer id);

    User create(User t);

    User update(User t);

    void deleteById(Integer id);
}
