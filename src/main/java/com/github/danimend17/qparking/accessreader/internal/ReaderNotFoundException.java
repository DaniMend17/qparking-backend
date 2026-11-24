package com.github.danimend17.qparking.accessreader.internal;

public class ReaderNotFoundException extends RuntimeException {
    public ReaderNotFoundException(Long readerId) {
        super("Access reader with id: " + readerId + " does not exist in parking");
    }
}
