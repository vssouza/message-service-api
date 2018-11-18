package com.example.message.service;

import com.example.message.common.NullAwareBeanUtilsBean;
import com.example.message.entity.User;
import com.example.message.exception.PartialUpdateOperationException;
import com.example.message.exception.UserNotFoundException;
import com.example.message.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private UserRepository userRepository;
    private NullAwareBeanUtilsBean nullAwareBeanUtils;

    @Autowired
    public void setNullAwareBeanUtils(NullAwareBeanUtilsBean nullAwareBeanUtils) {
        this.nullAwareBeanUtils = nullAwareBeanUtils;
    }

    @Autowired
    public void setUserRepository(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(final User user) {
        return userRepository.save(user);
    }

    public User retrieveUser(final long id) {
        if(userRepository.existsById(id)) {
            return userRepository.findById(id).get();
        }
        throw new UserNotFoundException("The user could not be found.");
    }

    public User replaceUser(final long id, final User newUser) {
        return userRepository.findById(id)
            .map(user -> {
                 user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                })
            .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    public User updatePartialUser(final long id, Map<String, Object> attributesToPatch) {
        User user = retrieveUser(id);
        try {
            attributesToPatch.forEach((key, value) -> nullAwareBeanUtils.copyProperty(user, key, value));
        } catch(UnsupportedOperationException ex) {
            throw new PartialUpdateOperationException("There was a problem patching the object.");
        }
        return userRepository.save(user);
    }

    public List<User> listUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
}
