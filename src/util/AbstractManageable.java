package util;

public abstract class AbstractManageable implements Manageable{
    private boolean running = false;

    @Override
    public void start(){
        running = true;
    }

    @Override
    public void shutdown(){
        running = false;
    }

    @Override
    public boolean isRunning(){
        return running;
    }
}
