package com.github.danimend17.qparking.parking.internal.web;

import com.github.danimend17.qparking.parking.ParkingQueryService;
import com.github.danimend17.qparking.parking.ParkingDTO;
import com.github.danimend17.qparking.parking.internal.application.ParkingCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingCommandService parkingCommandService;
    private final ParkingQueryService parkingQueryService;

    @PostMapping
    public ResponseEntity<ParkingDTO> createParking(@RequestBody @Valid ParkingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingCommandService.createParking(request));
    }

    @PutMapping
    public ResponseEntity<ParkingDTO> updateParking(@RequestBody @Valid ParkingRequest request) {
        return ResponseEntity.ok(parkingCommandService.updateParking(request));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteParking() {
        parkingCommandService.deleteParking();
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<ParkingDTO>> getAllParkings(@RequestParam int pageNumber) {
        return ResponseEntity.ok(parkingQueryService.getParkings(pageNumber));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ParkingDTO> getParkingById(@PathVariable Long id) {
        return ResponseEntity.ok(parkingQueryService.getParkingById(id));
    }


}
