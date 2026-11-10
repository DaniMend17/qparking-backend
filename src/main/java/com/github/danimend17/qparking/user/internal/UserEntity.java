package com.github.danimend17.qparking.user.internal;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 40, nullable = false)
    private String firstName;

    @Column(length = 40, nullable = false)
    private String lastName;

    @Column(length = 10, nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private BigDecimal credit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Platform platform;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAT;

    @PrePersist
    public void prePersist() {
        credit = BigDecimal.ZERO;
        createdAt = LocalDateTime.now();
        updatedAT = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAT = LocalDateTime.now();
    }

}
