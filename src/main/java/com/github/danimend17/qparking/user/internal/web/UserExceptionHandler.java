package com.github.danimend17.qparking.user.internal.web;

import com.github.danimend17.qparking.user.internal.DuplicatedEmailException;
import com.github.danimend17.qparking.user.internal.DuplicatedPhoneNumberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = UserController.class)
public class UserExceptionHandler {

    @ExceptionHandler(DuplicatedPhoneNumberException.class)
    public ProblemDetail duplicatedPhoneNumberHandler(DuplicatedPhoneNumberException e) {
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        problemDetail.setTitle("Duplicated phone number error");
        return problemDetail;
    }

    @ExceptionHandler(DuplicatedEmailException.class)
    public ProblemDetail duplicatedEmailHandler(DuplicatedEmailException e) {
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        problemDetail.setTitle("Duplicated email error");
        return problemDetail;
    }

}
