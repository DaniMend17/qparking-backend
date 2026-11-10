package com.github.danimend17.qparking.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

}
