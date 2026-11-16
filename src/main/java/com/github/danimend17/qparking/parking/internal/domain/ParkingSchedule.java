package com.github.danimend17.qparking.parking.internal.domain;

import com.github.danimend17.qparking.shared.enums.OperationDay;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "parking_schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ParkingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkingScheduleId;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private OperationDay weekday;

    @Column(nullable = false)
    private Boolean isOpen;

    private LocalTime openingTime;

    private LocalTime closingTime;

    @ManyToOne
    @JoinColumn(name = "parking_id")
    @ToString.Exclude
    private Parking parking;

}
