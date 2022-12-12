package com.demojpa.demo.service;

import com.demojpa.demo.entity.User;
import com.demojpa.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    // Save operation
    @Override
    public User saveUser(User user)
    {
        return userRepository.save(user);
    }

    // Read operation
    @Override
    public List<User> fetchUserList()
    {
        return (List<User>)
                userRepository.findAll();
    }

    // Update operation
    @Override

    public User updateUser(User user, Long userId)
    {
        User userDB = userRepository.findById(userId) .get();
        if (Objects.nonNull(user.getName()) && !"".equalsIgnoreCase(user.getName())) {
            userDB.setName(user.getName());
        }
        return userRepository.save(userDB);
    }

    // Delete operation
    @Override
    public void deleteUserById(Long userId)
    {
        userRepository.deleteById(userId);
    }

}
