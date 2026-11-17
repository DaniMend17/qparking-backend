package com.github.danimend17.qparking.parkingroles.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParkingRoleRepository extends JpaRepository<ParkingRole, Long> {

    boolean existsByRoleName(String roleName);

    @Query("""
             SELECT COUNT(pr) > 0 FROM ParkingRole pr \s
                                     WHERE pr.parkingId = :parkingId AND pr.roleName = :roleName
            """)
    boolean roleNameExistsInParking(String roleName, Long parkingId);

}
