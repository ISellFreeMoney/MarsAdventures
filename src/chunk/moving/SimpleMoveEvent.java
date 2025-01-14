package chunk.moving;

import chunk.ChunkConfiguration;
import chunk.ChunkTarget;
import util.PositionInterpreter;
import util.SimplePositionInterpreter;

public class SimpleMoveEvent implements MoveEvent{

    private ChunkTarget target;
    private float lastX, lastY, newX, newY;
    private PositionInterpreter positionInterpreter;

    SimpleMoveEvent(ChunkTarget target, ChunkConfiguration configuration, float lastX, float lastY, float newX, float newY){
        this.lastX = lastX;
        this.lastY = lastY;
        this.newX = newX;
        this.newY = newY;
        this.target = target;
        positionInterpreter = new SimplePositionInterpreter(configuration);
    }

    @Override
    public ChunkTarget getTarget() {
        return target;
    }

    @Override
    public int getLastIndexX() {
        return positionInterpreter.translateX(lastX);
    }

    @Override
    public int getLastIndexY() {
        return positionInterpreter.translateY(lastY);
    }

    @Override
    public float getLastX() {
        return lastX;
    }

    @Override
    public float getLastY() {
        return lastY;
    }

    @Override
    public int getNewIndexX() {
        return positionInterpreter.translateX(newX);
    }

    @Override
    public int getNewIndexY() {
        return positionInterpreter.translateY(newY);
    }

    @Override
    public float getNewX() {
        return newX;
    }

    @Override
    public float getNewY() {
        return newY;
    }
}
