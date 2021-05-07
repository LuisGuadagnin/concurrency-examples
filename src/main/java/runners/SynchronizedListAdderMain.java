package runners;

import lombok.extern.slf4j.Slf4j;
import tasks.ListAdderTask;
import util.SynchronizedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
public class SynchronizedListAdderMain {

    public static void main(String[] args) {

        List<String> sharedList = new SynchronizedList<>();
        List<ListAdderTask> tasks = Arrays.asList(
                new ListAdderTask(sharedList, "Message from Task 1", 10, 100),
                new ListAdderTask(sharedList, "Message from Task 2", 10, 100),
                new ListAdderTask(sharedList, "Message from Task 3", 10, 100),
                new ListAdderTask(sharedList, "Message from Task 4", 10, 100)
        );

        ExecutorService executorService = Executors.newFixedThreadPool(tasks.size());
        List<Future<?>> futures = tasks.stream().map(executorService::submit).collect(Collectors.toList());

        futures.forEach(future -> {
            try {
                future.get(5, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                log.warn("Task timed out: " + e.getMessage());
            } catch (InterruptedException e) {
                log.warn("Task interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                log.warn("Error on task execution: " + e.getMessage());
            }
        });

        executorService.shutdown();

        log.info("Shared list: [");
        sharedList.forEach(el -> log.info("    " + el));
        log.info("]");
    }

}
