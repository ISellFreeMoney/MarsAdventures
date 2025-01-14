package chunk.concurrent;

import chunk.*;
import chunk.io.ChunkLoader;
import chunk.io.ChunkSaver;
import chunk.moving.MovementListener;

import java.util.Collection;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ConcurrentChunkSystem implements ChunkSystem, Runnable {


    private static final long serialVersionUID = 1L;

    public static final long DEFAULT_INTERVAL = 20;

    private ChunkSystem system;
    private ScheduledThreadPoolExecutor executor;
    private ScheduledFuture<?> currentFuture;
    private long interval;

    public ConcurrentChunkSystem(ChunkSystem system) {
        this.system = system;
        this.interval = DEFAULT_INTERVAL;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public long getInterval() {
        return interval;
    }

    @Override
    public void start() {
        system.start();
        if(currentFuture != null){
            currentFuture.cancel(true);
        }

        this.executor = new ScheduledThreadPoolExecutor(1);
        currentFuture = executor.scheduleAtFixedRate(this, 0, getInterval(), TimeUnit.MILLISECONDS);
    }

    @Override
    public void shutdown() {
        system.shutdown();

        if(currentFuture != null){
            currentFuture.cancel(false);
        }
        executor.shutdown();
    }

    @Override
    public void update() {
        system.update();
    }

    @Override
    public boolean isRunning() {
        return system.isRunning();
    }

    @Override
    public void update(float delta) {
        system.update(delta);
    }

    @Override
    public ChunkConfiguration getConfiguration() {
        return system.getConfiguration();
    }

    @Override
    public void setConfiguration(ChunkConfiguration configuration) {
        system.setConfiguration(configuration);
    }

    @Override
    public Chunk getActiveChunk() {
        return system.getActiveChunk();
    }

    @Override
    public Collection<Chunk> getChunks() {
        return system.getChunks();
    }

    @Override
    public Chunk getChunk(int indexX, int indexY) {
        return system.getChunk(indexX, indexY);
    }

    @Override
    public int getCurrentChunkCount() {
        return system.getCurrentChunkCount();
    }

    @Override
    public void addListener(ChunkSystemListener listener) {
        system.addListener(listener);
    }

    @Override
    public void removeListener(ChunkSystemListener listener) {
        system.removeListener(listener);
    }

    @Override
    public Collection<ChunkSystemListener> getListeners() {
        return system.getListeners();
    }

    @Override
    public boolean hasListener(ChunkSystemListener listener) {
        return system.hasListener(listener);
    }

    @Override
    public void setHandler(ChunkHandler handler) {
        system.setHandler(handler);
    }

    @Override
    public ChunkHandler getHandler() {
        return system.getHandler();
    }

    @Override
    public void setLoader(ChunkLoader chunkLoader) {
        system.setLoader(chunkLoader);
    }

    @Override
    public ChunkLoader getLoader() {
        return system.getLoader();
    }

    @Override
    public void setSaver(ChunkSaver chunkSaver) {
        system.setSaver(chunkSaver);
    }

    @Override
    public ChunkSaver getSaver() {
        return system.getSaver();
    }

    @Override
    public void run() {
        update();
    }
}