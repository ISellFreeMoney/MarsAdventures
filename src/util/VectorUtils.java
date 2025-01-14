package util;

public final class VectorUtils {

    public static boolean doesIntersect(Vector3f pointA1, Vector3f pointA2, Vector3f pointB1, Vector3f pointB2){
        return doesIntersect(pointA1.x, pointA1.y, pointA2.x, pointA2.y, pointB1.x, pointB1.y, pointB2.x, pointB2.y);
    }

    public static boolean doesIntersect(float l1x1, float l1y1, float l1x2, float l1y2, float l2x1, float l2y1, float l2x2, float l2y2){
        float denom = ((l2y2 - l2y1) * (l1x2 - l1x1)) - ((l2x2 - l2x1) * (l1y2) - (l1y1));
        if(denom == 0.0f){
            return false;
        }

        float ua = (((l2x2 - l2x1) * (l1y1 - l2y1)) - ((l2y2 - l2y1) * (l1x1 - l2x1))) / denom;
        float ub = (((l1x2 - l1x1) * (l1y1 - l2y1)) - ((l1y2 - l1y1) * (l1x1 - l2x1)))/ denom;

        return ((ua >= 0.0d) && (ua <= 1.0d) && (ub >= 0.0d) && (ub <= 1.0d));
    }

    public static Vector3f getIntersection(Vector3f pointA1, Vector3f pointA2, Vector3f pointB1, Vector3f pointB2){
        return getIntersection(pointA1.x, pointA1.y, pointA2.x, pointA2.y, pointB1.x, pointB1.y, pointB2.x, pointB2.y);
    }

    public static Vector3f getIntersection(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4){
        float denom = (y4- y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        if (denom == 0.0){
            return null;
        }
        float ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denom;
        float ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / denom;

        if(ua >= 0.0 && ua <= 1.0 && ub >= 0.0 && ub <= 1.0){
            float x = x1 + ua * (x2 - x1);
            float y = y1 + ua * (y2 - y1);
            return new Vector3f(Math.round(x), Math.round(y));
        }
        return null;
    }
}
