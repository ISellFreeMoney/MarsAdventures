package game;

public class GameLoop implements Runnable{

    private final Game game;

    private boolean running;

    private long nextStatTime;
    private int fps, ups;

    public GameLoop(Game game){
        this.game = game;
    }

    @Override
    public void run() {
        running = true;
        double accumulator = 0;
        long currentTime, lastUpdate = System.nanoTime();
        nextStatTime = System.nanoTime() + 1000000000;

        while (running){
            currentTime = System.nanoTime();
            double lastRendererTimeInSeconds = (currentTime - lastUpdate) / 1000000000d;
            accumulator += lastRendererTimeInSeconds;
            lastUpdate = currentTime;

            double updateRate = 1.0d / 60.0d;
            if(accumulator >= updateRate){
                while (accumulator >= updateRate) {
                    update();
                    accumulator -= updateRate;
                }
                render();
            }
            printStats();
        }
    }

    private void printStats(){
        if(System.nanoTime() > nextStatTime){
            System.out.printf("FPS: %d, UPS: %d%n", fps, ups);
            fps = 0;
            ups = 0;
            nextStatTime = System.nanoTime() + 1000000000;
        }
    }

    private void render(){
        game.render();
        fps++;
    }

    private void update(){
        game.update();
        ups++;
    }

    public boolean isRunning() {
        return running;
    }
}
