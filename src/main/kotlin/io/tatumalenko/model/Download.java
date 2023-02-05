package io.tatumalenko.model;

public sealed interface Download {
    record App(String name, Person developer) implements Download {}
    record Movie(String title, Person director) implements Download {}
}
