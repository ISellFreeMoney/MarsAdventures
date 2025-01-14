package chunk;

import util.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleChunk extends SimpleObservable<ChunkListener> implements Chunk {

    private static final long serialVersionUID = 1L;

    private int indexX, indexY;
    private float x, y;
    private List<ChunkTarget> targets;
    private ChunkConfiguration configuration;

    public SimpleChunk(int indexX, int indexY, ChunkConfiguration configuration){
        this.indexX = indexX;
        this.indexY = indexY;
        this.configuration = configuration;
        PositionInterpreter positionInterpreter = new SimplePositionInterpreter(configuration);
        targets = new ArrayList<ChunkTarget>();
        x = positionInterpreter.translateIndexX(indexX);
        y = positionInterpreter.translateIndexY(indexY);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getWidth() {
        return configuration.getChunkWidth();
    }

    @Override
    public float getHeight() {
        return configuration.getChunkHeight();
    }

    @Override
    public ChunkTarget retrieve() {
        if(!targets.isEmpty()){
            ChunkTarget first = targets.get(0);
            for(ChunkListener listener : getListeners()){
                synchronized (this) {
                    listener.onRemove(first, this);
                }
            }
            targets.remove(first);
            return first;
        }else {
            return null;
        }
    }

    @Override
    public void add(ChunkTarget target) {
        if(!targets.contains(target)){
            for (ChunkListener listener : getListeners()){
                listener.onAdd(target, this);
            }
            targets.add(target);
        }
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((configuration == null) ? 0 : configuration.hashCode());
        result = prime * result + indexX;
        result = prime * result + indexY;
        result = prime * result + ((targets == null) ? 0 : targets.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SimpleChunk other = (SimpleChunk) obj;
        if (configuration == null) {
            if (other.configuration != null)
                return false;
        } else if (!configuration.equals(other.configuration))
            return false;
        if (indexX != other.indexX)
            return false;
        if (indexY != other.indexY)
            return false;
        if (targets == null) {
            return other.targets == null;
        } else return targets.equals(other.targets);
    }

    @Override
    public String toString() {
        return "SimpleChunk [indexX=" + indexX + ", indexY=" + indexY
                + ", targets=" + targets + ", configuration=" + configuration
                + "]";
    }


    @Override
    public boolean contains(ChunkTarget target) {
        return targets.contains(target);
    }

    @Override
    public int size() {
        return targets.size();
    }

    @Override
    public boolean isEmpty() {
        return targets.isEmpty();
    }

    @Override
    public void clear() {
        targets.clear();
    }

    @Override
    public float getTop() {
        return getY();
    }

    @Override
    public float getBottom() {
        return getTop() + getHeight() - 1;
    }

    @Override
    public float getLeft() {
        return getX();
    }

    @Override
    public float getRight() {
        return getLeft() + getWidth() - 1;
    }

    @Override
    public boolean contains(float x, float y) {
        return BoundableUtils.contains(this, x, y);
    }

    @Override
    public int getIndexX() {
        return indexX;
    }

    @Override
    public int getIndexY() {
        return indexY;
    }

    @NotNull
    @Override
    public Iterator<ChunkTarget> iterator() {
        return targets.iterator();
    }
}
