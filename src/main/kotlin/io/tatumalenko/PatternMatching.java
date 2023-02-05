package io.tatumalenko;

import io.tatumalenko.model.Person;

import java.util.concurrent.Executors;

sealed interface Download {}

record App(String name, Person developer) implements Download {}

record Movie(String title, Person director) implements Download {}

public class PatternMatching {
    public static void main(String[] args) {
        Download download = new App("Netflix", new Person("Alice", 18));
        System.out.println(patternMatching(download));
    }

    private static String patternMatching(Download download) {
        return switch (download) {
            case App(var appName, Person(var name, var age)) when "Alice".equals(name) -> "Alice's app " + appName;
            case Movie(var movieName, Person(var name, var age)) when "Alice".equals(name) -> "Alice's movie " + movieName;
            case App ignored -> "Not Alice's app";
            case Movie ignored -> "Not Alice's movie";
        };
    }

    private static void virtualThreads() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (var count = 10; count > 0; count--) {
                var finalCount = count;
                executor.submit(() -> {
                    try {
                        Thread.sleep(100L * finalCount);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(finalCount);
                });
            }
        }
    }
}
