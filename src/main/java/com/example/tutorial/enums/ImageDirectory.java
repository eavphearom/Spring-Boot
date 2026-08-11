package com.example.tutorial.enums;

import lombok.Getter;

@Getter
public enum ImageDirectory {

    PROFILE("profile"),
    MEDIA("media");

    private final String directory;

    ImageDirectory(String directory) {
        this.directory = directory;
    }
}