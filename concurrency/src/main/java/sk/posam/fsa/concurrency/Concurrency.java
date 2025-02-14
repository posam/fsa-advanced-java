package sk.posam.fsa.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Concurrency {

    private static final boolean ENABLE_VIRTUAL_THREADS = true;

    public static void main(String[] args) {
        ExecutorService executorService =
                ENABLE_VIRTUAL_THREADS
                        ? Executors.newVirtualThreadPerTaskExecutor()
                        : Executors.newFixedThreadPool(2);

        CompletableFuture<String> a = CompletableFuture.supplyAsync(() -> {
            System.out.println("executing task from thread: " + Thread.currentThread());
            return "a";
        }, executorService);
        CompletableFuture<String> b = CompletableFuture.supplyAsync(() -> {
            System.out.println("executing task from thread: " + Thread.currentThread());
            return "b";
        }, executorService);

        int[] numbers = {1, 2, 3, 4};

        String collect = Stream.of(a, b)
                // .join blokuje aktualne vlakno pokial Future nie je dokoncena a vrati vysledok
                .map(CompletableFuture::join)
                .collect(Collectors.joining());
        System.out.println("Got result in thread: " + Thread.currentThread().getName());
        System.out.println(collect);

        executorService.close();

    }

}
