package com.scm.services;

import com.scm.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(String id);
    void deleteUser(String id);
    Optional<User> updateUser(User user);
    boolean isUserExist(String userId);
    boolean isUserExistByEmailId(String email);
    User getUserByEmail(String email);
    List<User> getAllUsers();

}
