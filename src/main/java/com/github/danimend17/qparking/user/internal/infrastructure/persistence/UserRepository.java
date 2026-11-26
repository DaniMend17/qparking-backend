package com.github.danimend17.qparking.user.internal.infrastructure.persistence;

import com.github.danimend17.qparking.user.UserSummaryDto;
import com.github.danimend17.qparking.user.internal.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    @Query("""
                SELECT DISTINCT new com.github.danimend17.qparking.user.UserSummaryDto(
                    CONCAT(u.firstName, ' ', u.lastName),
                    u.phoneNumber
                ) FROM User u
                WHERE u.userId IN :userIds
            """)
    List<UserSummaryDto> findSummariesByUserIds(@Param("userIds") Collection<Long> userIds);
}
