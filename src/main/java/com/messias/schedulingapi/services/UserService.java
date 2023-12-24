package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.Branch;
import com.messias.schedulingapi.domain.User;
import com.messias.schedulingapi.repositories.UserRepository;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer idUser) {
        return userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException(User.class, idUser));
    }

    public void delete(Integer idUser) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException(User.class, idUser));
        userRepository.delete(user);
    }

    public User insert(User user) {
        return userRepository.save(user);
    }

    public User update(Integer idUser, User updateUser) {
        User oldUser = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException(User.class, idUser));
        this.updateDate(oldUser, updateUser);
        return userRepository.save(oldUser);
    }

    public void updateDate(User oldUser, User updateUser) {
        oldUser.setName(updateUser.getName());
    }
}
