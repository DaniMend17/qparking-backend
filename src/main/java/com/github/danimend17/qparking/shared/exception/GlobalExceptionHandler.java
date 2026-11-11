package com.github.danimend17.qparking.shared.exception;

import com.github.danimend17.qparking.user.DuplicatedPhoneNumberException;
import com.github.danimend17.qparking.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail userNotFoundHandler(UserNotFoundException e) {
        final ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("User not founded");
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail validationHandler(MethodArgumentNotValidException e) {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_CONTENT);
        problemDetail.setTitle("Request fields validation error");

        List<String> errors = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        problemDetail.setProperty("errors", errors);
        return problemDetail;
    }

    @ExceptionHandler(DuplicatedPhoneNumberException.class)
    public ProblemDetail duplicatedPhoneNumberHandler(DuplicatedPhoneNumberException e) {
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        problemDetail.setTitle("Duplicated phone number error");
        return problemDetail;
    }


    @ExceptionHandler(Exception.class)
    public ProblemDetail generalHandler(Exception e) {
        final ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        problemDetail.setTitle("Internal error");
        return problemDetail;
    }

}
