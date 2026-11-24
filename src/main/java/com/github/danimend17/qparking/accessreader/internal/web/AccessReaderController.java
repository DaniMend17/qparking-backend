package com.github.danimend17.qparking.accessreader.internal.web;

import com.github.danimend17.qparking.accessreader.AccessReaderDto;
import com.github.danimend17.qparking.accessreader.AccessReaderQueryService;
import com.github.danimend17.qparking.accessreader.internal.AccessReaderRequest;
import com.github.danimend17.qparking.accessreader.internal.application.AccessReaderCommandService;
import com.github.danimend17.qparking.shared.models.PageResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "access-readers")
@RequiredArgsConstructor
public class AccessReaderController {

    private final AccessReaderCommandService readerCommandService;
    private final AccessReaderQueryService readerQueryService;

    @PostMapping
    public ResponseEntity<AccessReaderDto> createAccessReader(@Valid @RequestBody AccessReaderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(readerCommandService.createAccessReader(request));
    }

    @PatchMapping(path = "{id}")
    public ResponseEntity<AccessReaderDto> updateAccessReader(
            @PathVariable Long id,
            @Valid @RequestBody AccessReaderRequest request
    ) {
        return ResponseEntity.ok(readerCommandService.updateAccessReader(id, request));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteAccessReader(@PathVariable Long id) {
        readerCommandService.deleteAccessReader(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<PageResult<AccessReaderDto>> getParkingAccessReaders(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
        return ResponseEntity.ok(readerQueryService.getParkingReaders(pageNumber));
    }

}
