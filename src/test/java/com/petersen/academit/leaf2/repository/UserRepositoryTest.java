package com.petersen.academit.leaf2.repository;

import com.petersen.academit.leaf2.data.data;
import com.petersen.academit.leaf2.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository uRepo;
    @Test
    @DisplayName("UserRepository: Search By E-mail")
    void findByEmail() {
        //Given:
        this.uRepo.save(data.getTestUser1());
        //When:
        Optional<User> testMethod = this.uRepo.findByEmail("test1@leaf.com");
        //Then:
        assertThat(testMethod.isPresent()).isTrue();
        assertThat(testMethod.get().getEmail()).isEqualTo("test1@leaf.com");
    }

    @Test
    @DisplayName("UserRepository: Search By E-mail and Password")
    void findByEmailAndPassword() {
        //Given:
        this.uRepo.save(data.getTestUser1());
        //When:
        Optional<User> testMethod = this.uRepo.findByEmailAndPassword("test1@leaf.com", "Test1234!");
        //Then:
        assertThat(testMethod.isPresent()).isTrue();
        assertThat(testMethod.get().getEmail()).isEqualTo("test1@leaf.com");
        assertThat(testMethod.get().getPassword()).isEqualTo("Test1234!");
    }
}