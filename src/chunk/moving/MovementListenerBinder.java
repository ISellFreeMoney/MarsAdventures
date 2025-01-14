package chunk.moving;

import chunk.Chunk;
import chunk.ChunkSystem;
import chunk.ChunkSystemListener;
import chunk.ChunkTarget;
import util.SimpleObservable;

public class MovementListenerBinder extends SimpleObservable<MovementListener> implements ChunkSystemListener {

    private ChunkSystem system;

    public MovementListenerBinder(ChunkSystem system){
        this.system = system;
    }

    @Override
    public void beforeCreateChunk(int indexX, int indexY) {

    }

    @Override
    public void afterCreateChunk(Chunk chunk) {
        add(chunk);
    }

    @Override
    public void beforeLoadChunk(int indexX, int indexY) {

    }

    @Override
    public void afterLoadChunk(Chunk chunk) {
        add(chunk);
    }

    @Override
    public void beforeSaveChunk(Chunk chunk) {
        remove(chunk);
    }

    @Override
    public void afterSaveChunk(Chunk chunk) {
        add(chunk);
    }

    @Override
    public void beforeRemoveChunk(Chunk chunk) {
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

    private void remove(Chunk chunk){
        for (ChunkTarget target : chunk){
            if(target instanceof MoveableChunkTarget){
                MoveableChunkTarget moveable = (MoveableChunkTarget)target;
                MovementDetector detector = moveable.getMovementDetector();

                for (MovementListener listener : getListeners()){
                    detector.removeListener(listener);
                }

                moveable.setMovementDetector(null);
             }
        }
    }

    private void add(Chunk chunk){
        for(ChunkTarget target : chunk){
            if(target instanceof MoveableChunkTarget) {
                MoveableChunkTarget moveable = (MoveableChunkTarget)target;
                MovementDetector detector = new SimpleMovementDetector(target, system.getConfiguration());
                for (MovementListener listener : getListeners()) {
                    detector.addListener(listener);
                }
                moveable.setMovementDetector(detector);
            }
        }
    }
}

