package com.github.danimend17.qparking.parkingroles.internal;

import com.github.danimend17.qparking.shared.enums.CommissionRate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "parking_roles")
@Getter
@Setter
public class ParkingRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkingRoleId;

    @Column(nullable = false)
    private Long parkingId;

    @Column(name = "parking_role_name", length = 50, nullable = false)
    private String roleName;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private CommissionRate commissionPeriod;

    @Column(nullable = false)
    private BigDecimal commissionValue;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (Objects.isNull(createdAt)) createdAt = LocalDateTime.now();
        if (Objects.isNull(updatedAt)) updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void updateDetails(ParkingRole parkingRole) {
        this.setRoleName(parkingRole.getRoleName());
        this.setCommissionPeriod(parkingRole.getCommissionPeriod());
        this.setCommissionValue(parkingRole.getCommissionValue());
    }

    public void assignParking(Long parkingId) {
        this.parkingId = parkingId;
    }


}
