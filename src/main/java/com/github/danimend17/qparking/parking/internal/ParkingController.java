package com.github.danimend17.qparking.parking.internal;

import com.github.danimend17.qparking.parking.IParkingQueryService;
import com.github.danimend17.qparking.parking.ParkingDTO;
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

    private final IParkingCommandService parkingCommandService;
    private final IParkingQueryService parkingQueryService;

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

    @GetMapping
    public ResponseEntity<Page<ParkingDTO>> getAllParkings(@RequestParam int pageNumber) {
        return ResponseEntity.ok(parkingQueryService.getParkings(pageNumber));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ParkingDTO> getParkingById(@PathVariable Long id) {
        return ResponseEntity.ok(parkingQueryService.getParkingById(id));
    }


}
