package com.github.danimend17.qparking.accessreader.internal;

import com.github.danimend17.qparking.accessreader.internal.domain.AccessReader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessReaderRepository extends JpaRepository<AccessReader, Long> {

    Optional<AccessReader> findByReaderIdAndParkingId(Long readerId, Long parkingId);

    boolean existsByAccessNameAndParkingId(String accessName, Long parkingId);

}
