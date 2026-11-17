package com.github.danimend17.qparking.parkingroles.internal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParkingRoleRepository extends JpaRepository<ParkingRole, Long> {

    @Query("""
             SELECT COUNT(pr) > 0 FROM ParkingRole pr \s
                                     WHERE pr.parkingId = :parkingId AND pr.roleName = :roleName
            """)
    boolean roleNameExistsInParking(@Param("roleName") String roleName, @Param("parkingId") Long parkingId);

    Page<ParkingRole> findAllByParkingId(Long parkingId, Pageable pageable);

    @Query("""
            SELECT pr.roleName FROM ParkingRole pr WHERE pr.parkingId = :parkingId
            """)
    Page<String> findAllRoleNamesByParkingId(@Param("parkingId") Long parkingId, Pageable pageable);

    boolean existsByParkingIdAndParkingRoleId(Long parkingId, Long parkingRoleId);
}
