package com.demojpa.demo.service;

import com.demojpa.demo.entity.User;
import java.util.List;
public interface UserService {
    // Save operation
    User saveUser(User user);

    // Read operation
    List<User> fetchUserList();

    // Update operation
    User updateUser(User department, Long departmentId);

    // Delete operation
    void deleteUserById(Long userId);
}
