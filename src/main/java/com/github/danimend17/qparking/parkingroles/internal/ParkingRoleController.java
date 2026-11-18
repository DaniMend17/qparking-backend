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

    @PostMapping(path = "user/{id}")
    public ResponseEntity<ParkingRoleDto> createParkingRole(
            @PathVariable Long id,
            @Valid @RequestBody ParkingRoleRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(parkingRoleCommandService.createParkingRole(id, request));
    }

    @PatchMapping(path = "{roleId}/user/{userId}")
    public ResponseEntity<ParkingRoleDto> updateParkingRole(
            @PathVariable Long roleId,
            @PathVariable Long userId,
            @Valid @RequestBody ParkingRoleRequest request
    ) {
        return ResponseEntity.ok(parkingRoleCommandService.updateParkingRole(userId, roleId, request));
    }

    @DeleteMapping(path = "{roleId}/user/{userId}")
    public ResponseEntity<?> deleteParkingRole(@PathVariable Long roleId, @PathVariable Long userId) {
        parkingRoleCommandService.deleteParkingRole(userId, roleId);
        return ResponseEntity.noContent().build();
    }

}
