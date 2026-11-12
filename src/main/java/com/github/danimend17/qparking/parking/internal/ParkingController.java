package com.github.danimend17.qparking.parking.internal;

import com.github.danimend17.qparking.parking.ParkingDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "parking")
@RequiredArgsConstructor
public class ParkingController {

    private final IParkingCommandService parkingCommandService;

    @PostMapping
    public ResponseEntity<ParkingDTO> createParking(@RequestBody @Valid ParkingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingCommandService.createParking(request));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<ParkingDTO> updateParking(@PathVariable Long id, @RequestBody @Valid ParkingRequest request) {
        return ResponseEntity.ok(parkingCommandService.updateParking(id, request));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteParking(@PathVariable Long id) {
        parkingCommandService.deleteParking(id);
        return ResponseEntity.noContent().build();
    }


}
