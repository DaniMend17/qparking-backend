package com.github.danimend17.qparking.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findByEmail(String email);

}
