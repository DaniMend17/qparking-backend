package com.github.danimend17.qparking.parking.internal;

import org.springframework.data.jpa.repository.JpaRepository;

interface ParkingRepository extends JpaRepository<ParkingEntity, Long> {
}
