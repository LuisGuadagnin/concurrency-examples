package tasks;

import java.util.concurrent.Callable;

public class SumTask implements Callable<Integer> {

    private final Integer firstNumber;
    private final Integer secondNumber;
    private final int waitTime;

    public SumTask(Integer firstNumber, Integer secondNumber, int waitTime) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.waitTime = waitTime;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(waitTime);
        return firstNumber + secondNumber;
    }
}
