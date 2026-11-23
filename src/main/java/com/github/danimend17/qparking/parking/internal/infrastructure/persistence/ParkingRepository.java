package com.github.danimend17.qparking.parking.internal.infrastructure.persistence;

import com.github.danimend17.qparking.parking.internal.domain.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

    @Query("FROM Parking p JOIN FETCH p.parkingSchedule WHERE p.userId = :userId")
    Optional<Parking> findUserParking(@Param("userId") Long userId);

    boolean existsByUserId(Long userId);

    boolean existsByParkingName(String parkingName);

    Optional<Parking> findByUserId(Long userId);

}
