package com.github.danimend17.qparking.parking.internal.web;

import com.github.danimend17.qparking.parking.internal.domain.DuplicatedParkingNameException;
import com.github.danimend17.qparking.parking.internal.domain.ParkingLimitExceededException;
import com.github.danimend17.qparking.parking.internal.domain.commission.InvalidCommissionRateStateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = ParkingController.class)
public class ParkingExceptionHandler {

    @ExceptionHandler(DuplicatedParkingNameException.class)
    public ProblemDetail duplicatedParkingNameHandler(DuplicatedParkingNameException e) {
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        problemDetail.setTitle("Duplicated parking name error");
        return problemDetail;
    }

    @ExceptionHandler(ParkingLimitExceededException.class)
    public ProblemDetail parkingLimitExceededHandler(ParkingLimitExceededException e) {
        final ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.UNPROCESSABLE_CONTENT, e.getMessage());
        problemDetail.setTitle("Parking limit exceeded error");
        return problemDetail;
    }

    @ExceptionHandler(InvalidCommissionRateStateException.class)
    public ProblemDetail invalidCommissionRateStateHandler(InvalidCommissionRateStateException e) {
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Invalid commission rate state error");
        return problemDetail;
    }
}
