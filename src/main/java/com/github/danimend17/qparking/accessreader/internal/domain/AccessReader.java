package com.github.danimend17.qparking.accessreader.internal.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "parking_access_readers")
@Getter
@Setter
public class AccessReader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parking_access_reader_id")
    private Long readerId;

    @Column(nullable = false)
    private Long parkingId;

    @Column(length = 25, nullable = false)
    private String accessName;

    @Column(nullable = false)
    private Boolean isEntry;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (updatedAt == null) updatedAt = LocalDateTime.now();
        if (isActive == null) isActive = false;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void assignParking(Long parkingId) {
        this.parkingId = parkingId;
    }

    public void updateDetails(AccessReader sourceAccessReader) {
        this.accessName = sourceAccessReader.accessName;
        this.isEntry = sourceAccessReader.isEntry;
        if (sourceAccessReader.isActive != null) this.isActive = sourceAccessReader.isActive;
    }

}
