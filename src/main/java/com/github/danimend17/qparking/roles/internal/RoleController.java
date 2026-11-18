package com.github.danimend17.qparking.roles.internal;

import com.github.danimend17.qparking.roles.RoleDto;
import com.github.danimend17.qparking.roles.RoleQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleCommandService commandService;
    private final RoleQueryService roleQueryService;

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@Valid @RequestBody RoleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commandService.createRole(request));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable Long id, @Valid @RequestBody RoleRequest request) {
        return ResponseEntity.ok(commandService.updateRole(id, request));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        commandService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(roleQueryService.getRoleById(id));
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        return ResponseEntity.ok(roleQueryService.getAllRoles());
    }

}
