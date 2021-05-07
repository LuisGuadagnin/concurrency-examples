package runners;

import tasks.PrinterTask;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrinterMain {

    public static void main(String[] args) {
        List<PrinterTask> tasks = Arrays.asList(
                new PrinterTask("Logging Task 1", 100),
                new PrinterTask("Logging Task 2", 100),
                new PrinterTask("Logging Task 3", 100),
                new PrinterTask("Logging Task 4", 100)
        );

        ExecutorService executorService = Executors.newFixedThreadPool(tasks.size());
        tasks.forEach(executorService::execute);
    }

}
