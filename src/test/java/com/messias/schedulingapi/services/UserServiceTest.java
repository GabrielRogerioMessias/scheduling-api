package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.Scheduling;
import com.messias.schedulingapi.domain.SchedulingEmployer;
import com.messias.schedulingapi.domain.User;
import com.messias.schedulingapi.domain.dtos.UserDTO;
import com.messias.schedulingapi.repositories.PermissionRepository;
import com.messias.schedulingapi.repositories.UserRepository;
import com.messias.schedulingapi.services.exceptionsServices.DatabaseException;
import com.messias.schedulingapi.services.exceptionsServices.ResourceAlreadyRegisteredException;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import javax.print.DocFlavor;
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
    void findAll() {
        List<User> userList = Arrays.asList();
        when(userRepository.findAll()).thenReturn(userList);
        List<User> result = userService.findAll();
        assertEquals(userList, result);
        verify(userRepository).findAll();
    }

    @Test
    @DisplayName("when findById return a user")
    void findByIdCase1() {
        Integer idUser = 1;
        User user = new User();
        when(userRepository.findById(idUser)).thenReturn(Optional.of(user));

        User result = userService.findById(idUser);

        verify(userRepository).findById(idUser);
        assertEquals(user, result);
    }

    @Test
    @DisplayName("when findById does not return a user, and throws an exception")
    void findByIdCase2() {
        Integer idUser = 1;
        when(userRepository.findById(idUser)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> userService.findById(idUser));
        verify(userRepository).findById(idUser);
    }

    @Test
    @DisplayName("When data of user is updated")
    void updateCase1() {
        Integer idUser = 1;
        User oldUser = new User();
        oldUser.setFullName("TEST");
        User updateUser = new User();
        updateUser.setFullName("TEST UPDATE");
        when(userRepository.findById(idUser)).thenReturn(Optional.of(oldUser));
        when(userRepository.save(oldUser)).thenReturn(oldUser);

        User result = userService.update(idUser, updateUser);
        verify(userRepository).findById(idUser);
        verify(userRepository).save(oldUser);
        assertEquals(updateUser.getFullName(), result.getFullName());
    }

    @Test
    @DisplayName("When insert throws a exception")
    void insertCas1() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("test");
        when(userRepository.findByUsername(userDTO.getUsername())).thenReturn(new User());

        assertThrows(ResourceAlreadyRegisteredException.class, () -> userService.insert(userDTO));
        verify(userRepository).findByUsername(userDTO.getUsername());
    }

    @Test
    @DisplayName("When the user is successfully entered")
    void insertCase2() {
        Integer idUser = 1;
        User newUser = new User();
        UserDTO newUserDTO = new UserDTO();
        newUserDTO.setPassword("TEST");
        newUserDTO.setUsername("TestUser");
        when(userRepository.findByUsername(newUserDTO.getUsername())).thenReturn(null);
        when(userRepository.save(newUser)).thenReturn(newUser);

        User result = userService.insert(newUserDTO);

        assertEquals(newUser, result);
        verify(userRepository).save(newUser);
        verify(userRepository).findByUsername(newUserDTO.getUsername());
    }

    @Test
    @DisplayName("When the method delete returns a exception")
    void deleteCase1() {
        Integer idUser = 1;
        when(userRepository.findById(idUser)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> userService.delete(idUser));
        verify(userRepository, never()).delete(any());
        verify(userRepository).findById(idUser);
    }

    @Test
    @DisplayName("When the method delete successful")
    void deleteCase2() {
        Integer idUser = 1;
        User deleteUser = new User();
        when(userRepository.findById(idUser)).thenReturn(Optional.of(deleteUser));
        userService.delete(idUser);
        doNothing().when(userRepository).delete(deleteUser);
        verify(userRepository).findById(idUser);
        verify(userRepository).delete(deleteUser);
    }

    @Test
    @DisplayName("When the delete method throws a database exception")
    void deleteCase3() {
        Integer idUser = 1;
        User deleteUser = new User();

        when(userRepository.findById(idUser)).thenReturn(Optional.of(deleteUser));
        doThrow(DatabaseException.class).when(userRepository).delete(deleteUser);

        assertThrows(DatabaseException.class, ()-> userService.delete(idUser));
        verify(userRepository).findById(idUser);
    }
}