package com.github.danimend17.qparking.accessreader.internal.infrastructure.persistence;

import com.github.danimend17.qparking.accessreader.internal.domain.AccessReader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessReaderRepository extends JpaRepository<AccessReader, Long> {

    Optional<AccessReader> findByReaderIdAndParkingId(Long readerId, Long parkingId);

    boolean existsByAccessNameAndParkingId(String accessName, Long parkingId);

    Page<AccessReader> findAllByParkingId(Long parkingId, Pageable pageable);

}
