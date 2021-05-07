package tasks;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrinterTask implements Runnable {

    private final String message;
    private final int timeInterval;
    private boolean shouldStop;

    public PrinterTask(String message, int timeInterval) {
        this.message = message;
        this.timeInterval = timeInterval;
        shouldStop = false;
    }

    public void stop() {
        shouldStop = true;
    }

    @SneakyThrows
    public void run() {
        while (!shouldStop) {
            Thread.sleep(timeInterval);
            log.info(message);
        }
    }
}
