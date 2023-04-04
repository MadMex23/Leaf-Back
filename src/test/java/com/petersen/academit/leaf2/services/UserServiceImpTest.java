package com.petersen.academit.leaf2.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petersen.academit.leaf2.data.data;
import com.petersen.academit.leaf2.entity.Account;
import com.petersen.academit.leaf2.entity.User;
import com.petersen.academit.leaf2.mapper.AccountMapper;
import com.petersen.academit.leaf2.mapper.UserMapper;
import com.petersen.academit.leaf2.repository.AccountRepository;
import com.petersen.academit.leaf2.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class UserServiceImpTest extends User {
    ObjectMapper objectMapper;
    private UserRepository uRepo;
    private UserService uService;

    private AccountRepository aRepo;
    private AccountServiceImp aService;

    @BeforeEach
    void setUp() {
        uRepo = mock(UserRepository.class);
        aRepo = mock(AccountRepository.class);
        aService = mock(AccountServiceImp.class);
        uService = new UserServiceImp(uRepo, aRepo, aService);
        objectMapper = new ObjectMapper();
    }


    @Test
    @DisplayName("Login User Correctly: ")
    void loginValidate() {
        User testUser = new User();
        testUser.setEmail("test1@leaf.com");
        testUser.setPassword("Test1234!");
        User dataUser = data.getTestUser1();
        given(uRepo.findByEmailAndPassword(testUser.getEmail(), testUser.getPassword())).willReturn(Optional.of(dataUser));
        User response = this.uService.loginValidate(testUser);
        assertThat(testUser.getEmail()).isEqualTo(response.getEmail());
        assertThat(testUser.getPassword()).isEqualTo(response.getPassword());
    }

    @Test
    @DisplayName("Login User Wrong: ")
    void loginValidateException() {
        User testUser = new User();
        testUser.setEmail("test1@leaf.com");
        testUser.setPassword("Test1234!");
        given(uRepo.findByEmailAndPassword(testUser.getEmail(), testUser.getPassword())).willReturn(Optional.empty());
        assertThatThrownBy(() -> uService.loginValidate(testUser)).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Create User:")
    void createUser() {
        User testUser = data.getTestUser1();
        this.uService.createUser(testUser);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(uRepo).save(userArgumentCaptor.capture());
        User userCaptor = userArgumentCaptor.getValue();
        assertThat(userCaptor).isEqualTo(testUser);
        verify(uRepo).save(any());
    }

    @Test
    @DisplayName("Validate Password for delete:")
    void passwordValidate() {
        Integer testId = 1;
        String testPassword = "Test1234!";
        User testUser = data.getTestUser1();
        given(uRepo.findById(testId)).willReturn(Optional.of(testUser));
        boolean response = this.uService.passwordValidate(testId, testPassword);
        assertThat(response).isTrue();

    }

    @Test
    @DisplayName("Delete User: ")
    void deleteUser() {
        Integer testId = 1;
        User testUser = data.getTestUser1();
        Account testAccount = data.getTestAccount1();
        given(aRepo.findAllByUserId(1)).willReturn(data.accountArrayList);

        given(uRepo.findById(testId)).willReturn(Optional.of(testUser));
        willDoNothing().given(uRepo).deleteById(testId);
        this.uService.deleteUser(testId);
        verify(uRepo, times(1)).deleteById(testId);
        verify(aService, times(1)).deleteAccount(testAccount.getId());
    }

    @Test
    @DisplayName("Get Correct User By Id: ")
    void getUserById() {
        Integer testId = 1;
        User testUser = data.getTestUser1();
        when(uRepo.findById(testId)).thenReturn(Optional.of(testUser));
        User response = this.uService.getUserById(testId);
        assertThat(response.getId()).isEqualTo(testId);
        assertThat(response.getEmail()).isEqualTo(testUser.getEmail());
    }

    @Test
    @DisplayName("User By Id Doesn't exist: ")
    void getUserByIdException() {
        Integer testId = 1;
        when(uRepo.findById(testId)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> uService.getUserById(testId)).isInstanceOf(RuntimeException.class);
    }


    @Test
    void getUserByEmail() {
        String testEmail = "test1@leaf.com";
        User testUser = data.getTestUser1();
        when(uRepo.findByEmail(testEmail)).thenReturn(Optional.of(testUser));
        Optional<User> response = this.uService.getUserByEmail(testEmail);
        assertThat(response.get().getEmail()).isEqualTo(testEmail);
        assertThat(response.get().getId()).isEqualTo(testUser.getId());
    }
}