package com.github.danimend17.qparking.parking.internal.domain;

import com.github.danimend17.qparking.shared.enums.CommissionRate;
import com.github.danimend17.qparking.shared.enums.OperationDay;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "parkings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkingId;

    @Column(unique = true, nullable = false)
    private Long userId;

    @Column(length = 120, unique = true, nullable = false)
    private String parkingName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommissionRate commissionRate;

    private BigDecimal hourlyRate;

    private BigDecimal flatRate;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "parking", cascade = CascadeType.ALL)
    private List<ParkingSchedule> parkingSchedule = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (Objects.isNull(createdAt)) createdAt = LocalDateTime.now();
        if (Objects.isNull(updatedAt)) updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        if (Objects.isNull(updatedAt)) updatedAt = LocalDateTime.now();
    }

    public void updateDetails(Parking parking) {
        if (Objects.isNull(parking)) return;
        this.setParkingName(parking.getParkingName());
        this.setCommissionRate(parking.getCommissionRate());
        this.setHourlyRate(parking.getHourlyRate());
        this.setFlatRate(parking.getFlatRate());
        this.setLatitude(parking.getLatitude());
        this.setLongitude(parking.getLongitude());

        if (Objects.isNull(parking.getParkingSchedule())) return;
        parking.getParkingSchedule().forEach(this::addOrUpdateParkingSchedule);
    }


    private void addOrUpdateParkingSchedule(ParkingSchedule parkingSchedule) {
        if (Objects.isNull(parkingSchedule)) return;
        Optional<ParkingSchedule> optionalParkingSchedule =
                findParkingScheduleDay(parkingSchedule.getWeekday());
        if (optionalParkingSchedule.isEmpty()) {
            addNewParkingScheduleDay(parkingSchedule);
            return;
        }
        final ParkingSchedule oldParkingSchedule = optionalParkingSchedule.get();
        updateParkingScheduleDay(oldParkingSchedule, parkingSchedule);
    }

    private void addNewParkingScheduleDay(ParkingSchedule newParkingSchedule) {
        if (Objects.isNull(newParkingSchedule)) return;
        this.parkingSchedule.add(newParkingSchedule);
        newParkingSchedule.setParking(this);
    }

    private Optional<ParkingSchedule> findParkingScheduleDay(OperationDay weekday) {
        for (ParkingSchedule parkingSchedule : parkingSchedule) {
            if (parkingSchedule.getWeekday() == weekday) {
                return Optional.of(parkingSchedule);
            }
        }
        return Optional.empty();
    }

    private void updateParkingScheduleDay(ParkingSchedule oldParkingSchedule,
                                          ParkingSchedule newParkingSchedule) {
        if (Objects.isNull(newParkingSchedule)) return;
        oldParkingSchedule.setIsOpen(newParkingSchedule.getIsOpen());
        oldParkingSchedule.setOpeningTime(newParkingSchedule.getOpeningTime());
        oldParkingSchedule.setClosingTime(newParkingSchedule.getClosingTime());
    }

    public void assignUser(Long userId) {
        this.userId = userId;
    }


}
