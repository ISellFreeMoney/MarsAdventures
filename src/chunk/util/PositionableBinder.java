package chunk.util;

public class PositionableBinder {

    private Boundable boundable;

    public PositionableBinder(Boundable boundable){
        this.boundable = boundable;
    }

    public void bind(Positionable positionable, float lastX, float lastY){
        if(!boundable.contains(positionable.getX(), positionable.getY())){
            Vector3f target = new Vector3f(positionable.getX(), positionable.getY());
            Vector3f center = new Vector3f(lastX, lastY);

            if(!boundable.contains(lastX, lastY)){
                center.set((boundable.getRight() - boundable.getLeft()) / 2f,
                        (boundable.getBottom() - boundable.getTop()) / 2f);
            }

            Vector3f topLeft = new Vector3f(boundable.getLeft(), boundable.getTop());
            Vector3f topRight = new Vector3f(boundable.getRight(), boundable.getTop());
            Vector3f bottomLeft = new Vector3f(boundable.getLeft(), boundable.getBottom());
            Vector3f bottomRight = new Vector3f(boundable.getRight(), boundable.getBottom());

            Vector3f newPosition = null;

            newPosition = VectorUtils.getIntersection(topLeft, topRight, center, target);

            if(newPosition == null) {
                newPosition = VectorUtils.getIntersection(topRight, bottomRight, center, target);
            }

            if (newPosition == null){
                newPosition = VectorUtils.getIntersection(bottomRight, bottomLeft, center, target);
            }

            if (newPosition == null) {
                newPosition = VectorUtils.getIntersection(bottomLeft, topLeft, center, target);
            }

            if(newPosition != null){
                positionable.setX((newPosition.x));
                positionable.setY(newPosition.y);
            } else {
                positionable.setX(lastX);
                positionable.setY(lastY);
            }
        }
    }
}
