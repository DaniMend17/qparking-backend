package com.github.danimend17.qparking.parkingroles.internal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = ParkingRoleController.class)
public class ParkingRoleExceptionHandler {

    @ExceptionHandler(DuplicatedParkingRoleNameException.class)
    public ProblemDetail duplicateParkingRoleNameHandler(DuplicatedParkingRoleNameException e) {
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        problemDetail.setTitle("Duplicated parking role name error");
        return problemDetail;
    }

    @ExceptionHandler(InvalidParkingRoleAccessException.class)
    public ProblemDetail invalidParkingRoleAccessHandler(InvalidParkingRoleAccessException e) {
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
        problemDetail.setTitle("Invalid parking role access error");
        return problemDetail;
    }

}
