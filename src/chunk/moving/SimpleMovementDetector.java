package chunk.moving;

import chunk.ChunkConfiguration;
import chunk.ChunkTarget;
import util.SimpleObservable;

public class SimpleMovementDetector extends SimpleObservable<MovementListener> implements MovementDetector {

    private static final long serialVersionUID = 1L;

    private ChunkTarget target;
    private ChunkConfiguration configuration;
    private float lastX, lastY;

    public SimpleMovementDetector(ChunkTarget target, ChunkConfiguration configuration){
        this.target = target;
        this.configuration = configuration;
        updatePosition();
    }


    @Override
    public void update() {
        update(0.0f);
    }

    @Override
    public void update(float delta) {
        if (lastX != target.getX() || lastY != target.getY()){
            for (MovementListener listener : getListeners()){
                MoveEvent event = createEvent(lastX, lastY, target.getX(), target.getY());
                listener.onMove(event);
            }
        }
        updatePosition();
    }

    private MoveEvent createEvent(float lastX, float lastY, float newX, float newY){
        return new SimpleMoveEvent(target, configuration, lastX, lastY, newX, newY);
    }

    private void updatePosition(){
        if(target != null){
            lastX = target.getX();
            lastY = target.getY();
        }
    }
}
