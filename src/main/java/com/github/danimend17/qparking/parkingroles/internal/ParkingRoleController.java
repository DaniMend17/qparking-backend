package com.github.danimend17.qparking.parkingroles.internal;


import com.github.danimend17.qparking.parkingroles.ParkingRoleDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "parking-role")
@RequiredArgsConstructor
public class ParkingRoleController {

    private final ParkingRoleCommandService parkingRoleCommandService;

    @PostMapping
    public ResponseEntity<ParkingRoleDto> createParkingRole(@Valid @RequestBody ParkingRoleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(parkingRoleCommandService.createParkingRole(request));
    }

    @PatchMapping(path = "{id}")
    public ResponseEntity<ParkingRoleDto> updateParkingRole(
            @PathVariable Long id,
            @Valid @RequestBody ParkingRoleRequest request
    ) {
        return ResponseEntity.ok(parkingRoleCommandService.updateParkingRole(id, request));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteParkingRole(@PathVariable Long id) {
        parkingRoleCommandService.deleteParkingRole(id);
        return ResponseEntity.noContent().build();
    }

}
