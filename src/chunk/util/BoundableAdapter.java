package chunk.util;

import chunk.ChunkConfiguration;

public class BoundableAdapter implements Boundable, IndexBoundable{

    private IndexBoundable indexBoundable;
    private PositionInterpreter interpreter;
    private ChunkConfiguration configuration;

    public BoundableAdapter(IndexBoundable indexBoundable, ChunkConfiguration configuration){
        this.indexBoundable = indexBoundable;
        interpreter = new SimplePositionInterpreter(configuration);
        this.configuration = configuration;
    }


    public void setConfiguration(ChunkConfiguration configuration){
        interpreter = new SimplePositionInterpreter(configuration);
    }

    @Override
    public float getTop() {
        return 0;
    }

    @Override
    public float getBottom() {
        return 0;
    }

    @Override
    public float getLeft() {
        return 0;
    }

    @Override
    public float getRight() {
        return 0;
    }

    @Override
    public int getIndexTop() {
        return 0;
    }

    @Override
    public int getIndexBottom() {
        return 0;
    }

    @Override
    public int getIndexLeft() {
        return 0;
    }

    @Override
    public int getIndexRight() {
        return 0;
    }

    @Override
    public boolean containsIndex(int indexX, int indexY) {
        return false;
    }

    @Override
    public boolean containsIndex(Indexable indexable) {
        return false;
    }

    @Override
    public boolean contains(float x, float y) {
        return false;
    }
}
