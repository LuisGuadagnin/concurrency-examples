package tasks;

import lombok.SneakyThrows;

import java.util.List;

public class ListAdderTask implements Runnable {

    private final List<String> list;

    private final String message;

    private final int timeInterval;

    private final int elementCount;

    public ListAdderTask(List<String> list, String message, int elementCount, int timeInterval) {
        this.list = list;
        this.message = message;
        this.timeInterval = timeInterval;
        this.elementCount = elementCount;
    }

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < elementCount; i++) {
            Thread.sleep(timeInterval);
            list.add(String.format("%s (%s)", message, i + 1));
        }
    }
}
