package util;

import chunk.Chunk;
import chunk.ChunkListener;
import chunk.ChunkSystemListener;
import chunk.ChunkTarget;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChunkTargetBinder implements ChunkListener, ChunkSystemListener {

    private Map<Chunk, PositionableBinder> binders;

    public ChunkTargetBinder(){
        binders = new ConcurrentHashMap<Chunk, PositionableBinder>();
    }

    @Override
    public void beforeCreateChunk(int indexX, int indexY) {

    }

    @Override
    public void afterCreateChunk(Chunk chunk) {
        chunk.addListener(this);
        addBinder(chunk);
    }

    @Override
    public void beforeLoadChunk(int indexX, int indexY) {

    }

    @Override
    public void afterLoadChunk(Chunk chunk) {
        chunk.addListener(this);
        addBinder(chunk);
    }

    @Override
    public void beforeSaveChunk(Chunk chunk) {
        chunk.removeListener(this);
    }

    @Override
    public void afterSaveChunk(Chunk chunk) {
        chunk.addListener(this);
    }

    @Override
    public void beforeRemoveChunk(Chunk chunk) {
        chunk.removeListener(this);
        removeBinder(chunk);
    }

    @Override
    public void afterRemoveChunk(int indexX, int indexY) {

    }

    @Override
    public void onEnterChunk(Chunk chunk) {

    }

    @Override
    public void onLeaveChunk(Chunk chunk) {

    }

    @Override
    public void onAdd(ChunkTarget target, Chunk chunk){
        PositionableBinder binder = binders.get(chunk);

        if(binder != null){
            float lastX = chunk.getLeft() + chunk.getWidth() / 2f;
            float lastY = chunk.getTop() + chunk.getHeight() / 2f;
            binder.bind(target, lastX, lastY);
        }
    }

    @Override
    public void onRemove(ChunkTarget target, Chunk chunk) {

    }

    private void addBinder(Chunk chunk) {
        binders.put(chunk, new PositionableBinder(chunk));
    }

    private void removeBinder(Chunk chunk){
        binders.remove(chunk);
    }
}
