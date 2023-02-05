package io.tatumalenko.concurrency;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class ConcurrencyJava {
    public static void run() {
        try {
            var text = downloadTextWithRetries(3).get();
            System.out.println(text);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void run2() {
        try (var executor = Executors.newSingleThreadExecutor()) {
            executor.submit(() -> {
                try {
                    var text = downloadTextWithRetries(3).get();
                    System.out.println(text);
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private static CompletableFuture<String> downloadText() {
        return CompletableFuture.supplyAsync(() -> "Hey!");
    }

    public static CompletableFuture<String> downloadTextWithRetries(int retryCount) {
        return downloadText().handle((String text, Throwable error) -> {
            if (error != null) {
                if (error instanceof IOException && retryCount > 1) {
                    // ignore or log
                    return downloadTextWithRetries(retryCount - 1);
                } else {
                    throw (RuntimeException) error;
                }
            } else {
                return CompletableFuture.completedFuture(text);
            }
        }).thenCompose(Function.identity());
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
