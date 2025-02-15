package sk.posam.fsa.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Concurrency {

    private static final boolean ENABLE_VIRTUAL_THREADS = false;

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

        int[] partOne = {numbers[0], numbers[1]};
        int[] partTwo = {numbers[2], numbers[3]};
        CompletableFuture<Integer> p1 = CompletableFuture.supplyAsync(() -> {
            return partOne[0] + partOne[1];
        }, executorService);
        CompletableFuture<Integer> p2 = CompletableFuture.supplyAsync(() -> {
            return partTwo[0] + partTwo[1];
        }, executorService);

        int sum = Stream.of(p1, p2)
                .map(CompletableFuture::join)
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println(sum);


//        String collect = Stream.of(a, b)
//                 .join blokuje aktualne vlakno pokial Future nie je dokoncena a vrati vysledok
//                .map(stringCompletableFuture -> stringCompletableFuture.join())
//                .collect(Collectors.joining());
//        System.out.println("Got result in thread: " + Thread.currentThread().getName());
//        System.out.println(collect);

        executorService.close();

    }

}
