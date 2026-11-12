package com.github.danimend17.qparking.parking.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface ParkingRepository extends JpaRepository<ParkingEntity, Long> {

    @Query("FROM ParkingEntity p JOIN FETCH p.parkingSchedules WHERE p.parkingId = :parkingId")
    Optional<ParkingEntity> findParkingWithScheduleById(Long parkingId);

}
