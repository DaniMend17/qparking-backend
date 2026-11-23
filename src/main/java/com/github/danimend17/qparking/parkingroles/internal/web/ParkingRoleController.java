package com.github.danimend17.qparking.parkingroles.internal.web;


import com.github.danimend17.qparking.parkingroles.ParkingRoleDto;
import com.github.danimend17.qparking.parkingroles.ParkingRoleQueryService;
import com.github.danimend17.qparking.parkingroles.internal.application.ParkingRoleCommandService;
import com.github.danimend17.qparking.shared.identity.CurrentUserProvider;
import com.github.danimend17.qparking.shared.models.PageResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "parking-roles")
@RequiredArgsConstructor
public class ParkingRoleController {

    private final ParkingRoleCommandService parkingRoleCommandService;
    private final ParkingRoleQueryService parkingRoleQueryService;
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

    @GetMapping
    public ResponseEntity<PageResult<ParkingRoleDto>> getUserParkingRoles(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber
    ) {
        return ResponseEntity.ok(parkingRoleQueryService
                .getUserParkingRoles(userProvider.getUserId(), pageNumber));
    }

}
