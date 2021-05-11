package tasks;

public class GenericSumTask {

    private final Integer firstNumber;
    private final Integer secondNumber;
    private final int waitTime;

    public GenericSumTask(Integer firstNumber, Integer secondNumber, int waitTime) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.waitTime = waitTime;
    }

    public Integer run() {
        try {
            Thread.sleep(waitTime);
            return firstNumber + secondNumber;
        } catch (InterruptedException e) {
            throw new RuntimeException("Could not sum " + firstNumber + " and " + secondNumber + ": thread got interrupted.");
        }
    }
}
