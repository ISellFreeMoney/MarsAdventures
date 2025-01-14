package util;

import chunk.ChunkConfiguration;

public class SimplePositionInterpreter implements PositionInterpreter{

    private ChunkConfiguration configuration;

    public SimplePositionInterpreter(ChunkConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public float translateIndexX(int indexX) {
        return translateIndex(indexX, configuration.getChunkWidth());
    }

    @Override
    public float translateIndexY(int indexY) {
        return translateIndex(indexY, configuration.getChunkHeight());
    }

    @Override
    public int translateX(float x) {
        return translatePosition(x, configuration.getChunkWidth());
    }

    @Override
    public int translateY(float y) {
        return translatePosition(y, configuration.getChunkHeight());
    }


    private float translateIndex(int index, int size){
        return index * size;
    }

    private int translatePosition(float value, int size){
        return (int) Math.floor(value / (float)size );
    }
}
