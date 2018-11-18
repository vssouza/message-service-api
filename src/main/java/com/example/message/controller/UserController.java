package com.example.message.controller;

import com.example.message.configuration.MessageAPIInfo;
import com.example.message.entity.User;
import com.example.message.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = MessageAPIInfo.USER_BASE_PATH)
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping(path = MessageAPIInfo.URL_ID_PATH)
    public User updateUser(@PathVariable(value = "id") final long id,
                           @RequestBody User user) {
        return userService.replaceUser(id, user);
    }

    @PatchMapping(path = MessageAPIInfo.URL_ID_PATH)
    public User updatePartialUser(@PathVariable(value = "id") final long id,
                                  @RequestBody Map<String, Object> userPatch) {
        return userService.updatePartialUser(id, userPatch);
    }

    @GetMapping
    public List<User> listUsers() {
        return userService.listUsers();
    }

    @GetMapping(path = MessageAPIInfo.URL_ID_PATH)
    public User getUserById(@PathVariable(value = "id") final long id) {
        return userService.retrieveUser(id);
    }

}
