package com.scm.services.Implementations;

import com.scm.entities.User;
import com.scm.helper.ResourceNotFoundException;
import com.scm.repository.UserRepository;
import com.scm.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of("USER"));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(String id) {
        User newUser = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with this id does not exist"));
        userRepository.delete(newUser);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User newUser = userRepository.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User with this id does not exist"));
        newUser.setUserName(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setAbout(user.getAbout());
        newUser.setEnabled(user.isEnabled());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setProvider(user.getProvider());
        newUser.setProviderUserId(user.getProviderUserId());
        newUser.setEmailVerified(user.isEmailVerified());
        newUser.setPhoneVerified(user.isPhoneVerified());
        newUser.setProfilePic(user.getProfilePic());
        newUser.setEmail(user.getEmail());

        User saved = userRepository.save(newUser);
        return Optional.ofNullable(saved);

    }

    @Override
    public boolean isUserExist(String userId) {
        User newUser = userRepository.findById(userId).orElse(null);
        return newUser!=null ? true : false;

    }

    @Override
    public boolean isUserExistByEmailId(String email) {
        User newUser = userRepository.findByEmail(email).orElse(null);
        return newUser!=null ? true : false;

    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
