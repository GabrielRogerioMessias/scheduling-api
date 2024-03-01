package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.User;
import com.messias.schedulingapi.repositories.PermissionRepository;
import com.messias.schedulingapi.repositories.UserRepository;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PermissionRepository permissionRepository;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("when method findAll returns a list of users")
    void findAll(){
        List<User> userList = Arrays.asList();
        when(userRepository.findAll()).thenReturn(userList);
        List<User> result = userService.findAll();
        assertEquals(userList, result);
        verify(userRepository).findAll();
    }
    @Test
    @DisplayName("when findById return a user")
    void findByIdCase1(){
        Integer idUser = 1;
        User user = new User();
        when(userRepository.findById(idUser)).thenReturn(Optional.of(user));

        User result = userService.findById(idUser);

        verify(userRepository).findById(idUser);
        assertEquals(user, result);
    }

    @Test
    @DisplayName("when findById does not return a user, and throws an exception")
    void findByIdCase2(){
        Integer idUser = 1;
        when(userRepository.findById(idUser)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, ()-> userService.findById(idUser));
        verify(userRepository).findById(idUser);
    }
}