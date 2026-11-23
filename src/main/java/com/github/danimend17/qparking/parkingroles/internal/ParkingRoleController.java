package com.github.danimend17.qparking.parkingroles.internal;


import com.github.danimend17.qparking.parkingroles.ParkingRoleDto;
import com.github.danimend17.qparking.shared.identity.CurrentUserProvider;
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
    private final CurrentUserProvider userProvider;

    @PostMapping
    public ResponseEntity<ParkingRoleDto> createParkingRole(@Valid @RequestBody ParkingRoleRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(parkingRoleCommandService.createParkingRole(userProvider.getUserId(), request));
    }

    @PatchMapping(path = "{roleId}")
    public ResponseEntity<ParkingRoleDto> updateParkingRole(
            @PathVariable Long roleId,
            @Valid @RequestBody ParkingRoleRequest request
    ) {
        return ResponseEntity
                .ok(parkingRoleCommandService.updateParkingRole(userProvider.getUserId(), roleId, request));
    }

    @DeleteMapping(path = "{roleId}")
    public ResponseEntity<?> deleteParkingRole(@PathVariable Long roleId) {
        parkingRoleCommandService.deleteParkingRole(userProvider.getUserId(), roleId);
        return ResponseEntity.noContent().build();
    }

}
