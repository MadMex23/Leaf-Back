package com.petersen.academit.leaf2.repository;

import com.petersen.academit.leaf2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByEmailAndPassword(String email, String password);
}
