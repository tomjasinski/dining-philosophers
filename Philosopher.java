import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {

    private final Chopstick chopstick1, chopstick2;
    private static Semaphore doorman = new Semaphore(4);
    int levelOfHunger;

    public Philosopher(final String name, final Chopstick chopstick1, final Chopstick chopstick2) {
        super(name);
        this.chopstick1 = chopstick1;
        this.chopstick2 = chopstick2;
        levelOfHunger = 1000;
    }

    @Override
    public void run() {
        tryAcquire();
        while(levelOfHunger > 0) {
            chopstick1.take();
            chopstick2.take();
            System.out.println(getName() + " is eating with " + chopstick1.getName() + " & " + chopstick2.getName());
            levelOfHunger--;
            chopstick1.release();
            chopstick2.release();
        }
        doorman.release();
    }

    private void tryAcquire() {
        try {
            doorman.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
