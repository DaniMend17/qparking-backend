package com.github.danimend17.qparking.accessreader.internal.domain;

public class DuplicatedReaderNameException extends RuntimeException {
    public DuplicatedReaderNameException(String readerName) {
        super("Reader with name: " + readerName + " has already been register in parking");
    }
}
