import java.util.concurrent.Semaphore;

public class Chopstick {

    private final String name;
    private Semaphore sem;

    public Chopstick(final String name) {
        this.name = name;
        sem = new Semaphore(1);
    }

    public String getName() {
        return name;
    }

    public void take() {
        try {
            sem.acquire();
        } catch (InterruptedException e) { }
    }

    public void release() {
        sem.release();
    }
}
