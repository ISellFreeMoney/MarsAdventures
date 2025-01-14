package chunk.util;

import java.util.Vector;

public class Vector3f {

    public static Vector3f tmp = new Vector3f();
    public float x,y,z;

    public Vector3f(){
        this(0f,0f);
    }

    public Vector3f(float x, float y, float z){
        set(x,y,z);
    }

    public Vector3f(float x, float y){
        this(x,y,0f);
    }

    public Vector3f(Vector3f original) {
        this(original.x, original.y, original.z);
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(x);
        result = prime * result + Float.floatToIntBits(y);
        result = prime * result + Float.floatToIntBits(z);

        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(this == null) return false;
        if(getClass() != obj.getClass()) return false;
        Vector3f other = (Vector3f) obj;
        if(Float.floatToIntBits(x) != Float.floatToIntBits(other.x)) return false;
        if(Float.floatToIntBits(y) != Float.floatToIntBits(other.y)) return false;
        if(Float.floatToIntBits(z) != Float.floatToIntBits(other.z)) return false;
        return true;
    }

    public Vector3f copy(){
        return new Vector3f(this);
    }

    public Vector3f tmp() {
        return tmp.set(this);
    }

    public Vector3f set(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vector3f set(float x, float y){
        return set(x,y,0f);
    }

    public Vector3f set(Vector3f vector){
        return set(vector.x, vector.y, vector.z);
    }

    public float length(){
        return (float)Math.sqrt(x*x + y*y + z*z);
    }

    public Vector3f add(float x, float y, float z){
        return set(this.x + x, this.y + y, this.z + z);
    }

    public Vector3f add(float x, float y){
        return add(x,y,0f);
    }

    public Vector3f add(Vector3f vector){
        return add(vector.x, vector.y, vector.z);
    }

    public Vector3f substract(float x, float y, float z){
        return set(this.x - x, this.y - y, this.z - z);
    }

    public Vector3f substract(float x, float y){
        return substract(x,y,0f);
    }

    public Vector3f substract(Vector3f vector){
        return substract(vector.x, vector.y, vector.z);
    }

    public Vector3f multiply(float factor){
        return set(x*factor, y*factor, z*factor);
    }

    public Vector3f divide(float factor){
        float d = 1 / factor;
        return set(this.x * d, this.y * d, this.z * d);
    }

    public float distance(Vector3f vector){
        float a = vector.x - x;
        float b = vector.y - y;
        float c = vector.z - z;

        a *= a;
        b *= b;
        c *= c;

        return (float) Math.sqrt(a + b + c);
    }

    public float distance(float x1, float y1, float z1, float x2, float y2, float z2){
        return tmp.set(x2 - x1, y2 - y1, z2 - z1).length();
    }

    public float distance(float x1, float x2, float y1, float y2){
        return distance(x1, y1, 0f, x2, y2, 0f);
    }

    public Vector3f normalize(){
        if(x == 0 && y == 0 && z == 0) return this;
        return this.divide(this.length());
    }

    public float dot(Vector3f vector){
        return x * vector.x + y * vector.y + z * vector.z;
    }

    public Vector3f cross(Vector3f vector){
        return set(y * vector.z - z * vector.y, z * vector.x - x * vector.z, x * vector.y - y * vector.x);
    }

    public boolean isUnit(){
        return length() == 1;
    }

    public boolean isZero(){
        return x == 0 && y == 0 && z == 0;
    }

    public Vector3f interpolate(Vector3f target, float alpha) {
        Vector3f r = this.multiply(1.0f - alpha);
        r.add(target.tmp().multiply(alpha));
        return r;
    }

    public Vector3f invert() {
        x = -x;
        y = -y;
        z = -z;
        return this;
    }
}
