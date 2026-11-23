package com.github.danimend17.qparking.parking.internal.web;

import com.github.danimend17.qparking.parking.ParkingQueryService;
import com.github.danimend17.qparking.parking.ParkingDto;
import com.github.danimend17.qparking.parking.internal.application.ParkingCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "parkings")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingCommandService parkingCommandService;
    private final ParkingQueryService parkingQueryService;

    @PostMapping
    public ResponseEntity<ParkingDto> createParking(@RequestBody @Valid ParkingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingCommandService.createParking(request));
    }

    @PutMapping
    public ResponseEntity<ParkingDto> updateParking(@RequestBody @Valid ParkingRequest request) {
        return ResponseEntity.ok(parkingCommandService.updateParking(request));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteParking() {
        parkingCommandService.deleteParking();
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<ParkingDto>> getAllParkings(@RequestParam int pageNumber) {
        return ResponseEntity.ok(parkingQueryService.getParkings(pageNumber));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ParkingDto> getParkingById(@PathVariable Long id) {
        return ResponseEntity.ok(parkingQueryService.getParkingById(id));
    }
}
