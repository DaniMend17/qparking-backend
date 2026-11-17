package com.github.danimend17.qparking.parking.internal.infrastructure.persistence;

import com.github.danimend17.qparking.parking.internal.domain.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

    @Query("FROM Parking p JOIN FETCH p.parkingSchedules WHERE p.parkingId = :parkingId")
    Optional<Parking> findParkingWithScheduleById(Long parkingId);

    boolean existsByUserId(Long userId);

    boolean existsByParkingName(String parkingName);

    Optional<Parking> findByUserId(Long userId);

}
