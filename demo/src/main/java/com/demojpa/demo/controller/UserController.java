package com.demojpa.demo.controller;

import com.demojpa.demo.entity.User;
import com.demojpa.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")

public class UserController {
    @Autowired
    private UserService userService;

    // Save operation
    @PostMapping("/users")
    public User saveUser(@Valid @RequestBody User user)
    {
        return userService.saveUser(user);
    }

    // Read operation
    @GetMapping("/users")
    public List<User> fetchDepartmentList()
    {
        return userService.fetchUserList();
    }

    // Update operation
    @PutMapping("/users/{id}")
    public User  updateDepartment(@RequestBody User user, @PathVariable("id") Long departmentId)
    {
        return userService.updateUser(user, departmentId);
    }

    // Delete operation
    @DeleteMapping("/users/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long userId)
    {
        userService.deleteUserById(userId);
        return "Deleted Successfully";
    }

}
