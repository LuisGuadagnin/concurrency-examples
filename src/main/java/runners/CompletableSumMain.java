package runners;

import tasks.GenericSumTask;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableSumMain {

    public static void main(String[] args) {

        System.out.println("Starting threads");
        List<GenericSumTask> tasks = Arrays.asList(
                new GenericSumTask(2, 3, 2400),
                new GenericSumTask(3, 3, 2300),
                new GenericSumTask(4, 3, 2200),
                new GenericSumTask(5, 3, 2100)
        );

        ExecutorService executorService = Executors.newFixedThreadPool(tasks.size());

        tasks.forEach(
                task -> CompletableFuture.supplyAsync(task::run, executorService)
                        .thenAccept(result -> {
                            System.out.println("The result is " + result);
                            executorService.shutdownNow();
                        })
                        .exceptionally(error -> {
                            System.out.println("An exception was thrown: " + error.getMessage());
                            return null;
                        })
        );

        executorService.shutdown();

    }

}
