package com.realogs.saccoapp.service;

import com.realogs.saccoapp.model.User;

import java.util.List;


public interface UserService {
    User save(User user);
    User findByUsername(String username);

    User findByUserId(Long userId);

    List<String> findUsers(List<Long> idList);

    User getUserDetailByUsername(String username);
}
