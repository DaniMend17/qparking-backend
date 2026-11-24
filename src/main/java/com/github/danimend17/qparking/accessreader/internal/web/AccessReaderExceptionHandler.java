package com.github.danimend17.qparking.accessreader.internal.web;

import com.github.danimend17.qparking.accessreader.internal.domain.DuplicatedReaderNameException;
import com.github.danimend17.qparking.accessreader.internal.domain.ReaderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = AccessReaderController.class)
public class AccessReaderExceptionHandler {

    @ExceptionHandler(DuplicatedReaderNameException.class)
    public ProblemDetail duplicatedReaderNameHandler(DuplicatedReaderNameException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        problemDetail.setTitle("Duplicated reader name error");
        return problemDetail;
    }

    @ExceptionHandler(ReaderNotFoundException.class)
    public ProblemDetail readerNotFoundHandler(ReaderNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Reader not found error");
        return problemDetail;
    }

}
