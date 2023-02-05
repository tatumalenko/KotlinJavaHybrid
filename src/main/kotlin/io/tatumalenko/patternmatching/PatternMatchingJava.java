package io.tatumalenko.patternmatching;

import io.tatumalenko.model.Download;
import io.tatumalenko.model.Download.App;
import io.tatumalenko.model.Download.Movie;
import io.tatumalenko.model.Person;

public class PatternMatchingJava {
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
}
