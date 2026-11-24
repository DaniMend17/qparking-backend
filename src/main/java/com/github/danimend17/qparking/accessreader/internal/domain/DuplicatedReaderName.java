package com.github.danimend17.qparking.accessreader.internal.domain;

public class DuplicatedReaderName extends RuntimeException {
    public DuplicatedReaderName(String readerName) {
        super("Reader with name: " + readerName + " has already been register in parking");
    }
}
