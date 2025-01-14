package chunk;

import chunk.concurrent.ConcurrentMatrixList;
import chunk.io.ChunkLoader;
import chunk.io.ChunkSaver;
import chunk.moving.MovementListener;
import chunk.moving.MovementListenerBinder;
import util.*;

import java.util.Collection;

public abstract class AbstractChunkSystem extends AbstractManageable implements ChunkSystem {

    private static  final long serialVersionUID = 1L;

    private ChunkConfiguration configuration;
    private ChunkHandler chunkHandler;
    protected MatrixList<Chunk> chunks;
    protected PositionInterpreter positionInterpreter;
    private ChunkLoader chunkLoader;
    private ChunkSaver chunkSaver;
    private MovementListenerBinder movementBinder;
    private ChunkTargetBinder targetBinder;
    private Observable<ChunkSystemListener> observable;

    public AbstractChunkSystem(ChunkConfiguration configuration){
        this.configuration = configuration;
        observable = new SimpleObservable<ChunkSystemListener>();
        chunks = new ConcurrentMatrixList<Chunk>();
        positionInterpreter = new SimplePositionInterpreter(configuration);
        movementBinder = new MovementListenerBinder(this);
        targetBinder = new ChunkTargetBinder();
        addListener(movementBinder);
        addListener(targetBinder);
    }

    @Override
    public void update(){
        update(0.0f);
    }

    public ChunkConfiguration getConfiguration(){
        return configuration;
    }

    @Override
    public void setConfiguration(ChunkConfiguration configuration){
        this.configuration = configuration;
        positionInterpreter = new SimplePositionInterpreter(configuration);
    }

    @Override
    public Chunk getActiveChunk(){
        ChunkTarget focused = configuration.getFocused();

        if(focused != null){
            final int INDEX_X = positionInterpreter.translateX(focused.getX());
            final int INDEX_Y = positionInterpreter.translateY(focused.getY());

            return chunks.get(INDEX_X, INDEX_Y);
        }else{
            return null;
        }
    }

    @Override
    public Collection<Chunk> getChunks(){
        return chunks;
    }

    @Override
    public Chunk getChunk(int indexX, int indexY) {
        return chunks.get(indexX, indexY);
    }

    @Override
    public int getCurrentChunkCount() {
        return chunks.size();
    }

    @Override
    public void addListener(ChunkSystemListener listener) {
        observable.addListener(listener);
    }

    @Override
    public void removeListener(ChunkSystemListener listener) {
        observable.removeListener(listener);
    }

    @Override
    public boolean hasListener(ChunkSystemListener listener) {
        return observable.hasListener(listener);
    }

    @Override
    public void setHandler(ChunkHandler handler) {
        if(handler != null) {
            if(this.chunkHandler != null){
                movementBinder.removeListener((MovementListener) chunkHandler);
            }
            this.chunkHandler = handler;

            movementBinder.addListener((MovementListener) chunkHandler);
        }
    }

    @Override
    public ChunkHandler getHandler() {
        return chunkHandler;
    }

    @Override
    public Collection<ChunkSystemListener> getListeners() {
        return observable.getListeners();
    }

    @Override
    public void setLoader(ChunkLoader chunkLoader) {
        if(chunkLoader != null){
            this.chunkLoader = chunkLoader;
        }
    }

    @Override
    public ChunkLoader getLoader() {
        return chunkLoader;
    }

    @Override
    public void setSaver(ChunkSaver chunkSaver) {
        if(chunkSaver != null){
            this.chunkSaver = chunkSaver;
        }
    }

    @Override
    public ChunkSaver getSaver() {
        return chunkSaver;
    }
}
