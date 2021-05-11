package runners;

import tasks.SumTask;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class SumMain {

    public static void main(String[] args) {

        System.out.println("Starting threads");
        List<SumTask> task = Arrays.asList(
                new SumTask(2, 3, 2000),
                new SumTask(3, 3, 2000),
                new SumTask(4, 3, 2000),
                new SumTask(5, 3, 2000)
        );

        ExecutorService executorService = Executors.newFixedThreadPool(task.size());
        List<Future<Integer>> resultFutures = task.stream().map(executorService::submit).collect(Collectors.toList());
        List<Integer> results = resultFutures.stream().map(future -> {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());
        executorService.shutdown();

        System.out.println("The result is " + results);

    }

}
