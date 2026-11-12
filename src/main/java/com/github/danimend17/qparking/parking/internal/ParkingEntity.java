package com.github.danimend17.qparking.parking.internal;

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
public class ParkingEntity {

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
    private List<ParkingScheduleEntity> parkingSchedules = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (Objects.isNull(createdAt)) createdAt = LocalDateTime.now();
        if (Objects.isNull(updatedAt)) updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        if (Objects.isNull(updatedAt)) updatedAt = LocalDateTime.now();
    }

    public void updateDetails(ParkingEntity parking) {
        if (Objects.isNull(parking)) return;
        this.setParkingName(parking.getParkingName());
        this.setCommissionRate(parking.getCommissionRate());
        this.setHourlyRate(parking.getHourlyRate());
        this.setFlatRate(parking.getFlatRate());
        this.setLatitude(parking.getLatitude());
        this.setLongitude(parking.getLongitude());

        if (Objects.isNull(parking.getParkingSchedules())) return;
        parking.getParkingSchedules().forEach(this::addOrUpdateParkingSchedule);
    }


    public void addOrUpdateParkingSchedule(ParkingScheduleEntity parkingSchedule) {
        if (Objects.isNull(parkingSchedule)) return;
        Optional<ParkingScheduleEntity> optionalParkingSchedule =
                findParkingScheduleDay(parkingSchedule.getWeekday());
        if (optionalParkingSchedule.isEmpty()) {
            addNewParkingScheduleDay(parkingSchedule);
            return;
        }
        final ParkingScheduleEntity oldParkingSchedule = optionalParkingSchedule.get();
        updateParkingScheduleDay(oldParkingSchedule, parkingSchedule);
    }

    private void addNewParkingScheduleDay(ParkingScheduleEntity parkingSchedule) {
        if (Objects.isNull(parkingSchedule)) return;
        parkingSchedules.add(parkingSchedule);
        parkingSchedule.setParking(this);
    }

    private Optional<ParkingScheduleEntity> findParkingScheduleDay(OperationDay weekday) {
        for (ParkingScheduleEntity parkingSchedule : parkingSchedules) {
            if (parkingSchedule.getWeekday() == weekday) {
                return Optional.of(parkingSchedule);
            }
        }
        return Optional.empty();
    }

    private void updateParkingScheduleDay(ParkingScheduleEntity oldParkingSchedule,
                                          ParkingScheduleEntity newParkingSchedule) {
        if (Objects.isNull(newParkingSchedule)) return;
        oldParkingSchedule.setIsOpen(newParkingSchedule.getIsOpen());
        oldParkingSchedule.setOpeningTime(newParkingSchedule.getOpeningTime());
        oldParkingSchedule.setClosingTime(newParkingSchedule.getClosingTime());
    }


}
