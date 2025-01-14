package chunk.util;

public interface Manageable extends Updatable{
    void start();
    void shutdown();
    void update();
    boolean isRunning();
}
