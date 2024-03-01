package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.Permission;
import com.messias.schedulingapi.domain.User;
import com.messias.schedulingapi.domain.dtos.UserDTO;
import com.messias.schedulingapi.repositories.PermissionRepository;
import com.messias.schedulingapi.repositories.UserRepository;
import com.messias.schedulingapi.services.exceptionsServices.DatabaseException;
import com.messias.schedulingapi.services.exceptionsServices.ResourceAlreadyRegisteredException;
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
    private final PermissionRepository permissionRepository;

    public UserService(UserRepository userRepository, PermissionRepository permissionRepository) {
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
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
        return userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException(User.class, idUser));
    }

    public void delete(Integer idUser) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException(User.class, idUser));
        try {
            userRepository.delete(user);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }


    public User insert(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new ResourceAlreadyRegisteredException(User.class, userDTO.getUsername());
        } else {
            User newUser = new User();

            newUser.setPassword(this.encoder().encode(userDTO.getPassword()));
            newUser.setFullName(userDTO.getFullName());
            newUser.setUsername(userDTO.getUsername());
            newUser.setEnabled(true);
            newUser.setCredentialsNonExpired(true);
            newUser.setAccountNonExpired(true);
            newUser.setAccountNonLocked(true);
            newUser.setEnabled(true);

            List<Permission> permissionList = permissionRepository.findAllById(userDTO.getPermissionIds());
            newUser.setPermissionList(permissionList);
            return userRepository.save(newUser);
        }
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
