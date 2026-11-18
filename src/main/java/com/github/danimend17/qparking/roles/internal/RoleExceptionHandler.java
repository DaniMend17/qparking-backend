package com.github.danimend17.qparking.roles.internal;

import com.github.danimend17.qparking.roles.internal.domain.DuplicatedRoleNameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = RoleController.class)
public class RoleExceptionHandler {

    @ExceptionHandler(DuplicatedRoleNameException.class)
    public ProblemDetail duplicatedRoleNameHandler(DuplicatedRoleNameException e) {
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        problemDetail.setTitle("Duplicated role name error");
        return problemDetail;
    }

}
