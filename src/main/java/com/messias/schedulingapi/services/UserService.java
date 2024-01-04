package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.User;
import com.messias.schedulingapi.repositories.UserRepository;
import com.messias.schedulingapi.services.exceptionsServices.DatabaseException;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username: " + username + " not found ");
        }

    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer idUser) {
        return userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException(User.class, idUser));
    }

    public void delete(Integer idUser) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException(User.class, idUser));
        try {
            userRepository.delete(user);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User insert(User user) {
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setPassword(encoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(Integer idUser, User updateUser) {
        User oldUser = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException(User.class, idUser));
        this.updateDate(oldUser, updateUser);
        return userRepository.save(oldUser);
    }

    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public void updateDate(User oldUser, User updateUser) {
        oldUser.setFullName(updateUser.getFullName());
    }


}
