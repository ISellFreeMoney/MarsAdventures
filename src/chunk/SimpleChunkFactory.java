package chunk;

public class SimpleChunkFactory implements ChunkFactory{

    private ChunkConfiguration configuration;

    public SimpleChunkFactory(ChunkConfiguration configuration){
        this.configuration = configuration;
    }

    @Override
    public Chunk createChunk(int indexX, int indexY) {
        return new SimpleChunk(indexX, indexY, configuration);
    }

    @Override
    public void setChunkConfiguration(ChunkConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public ChunkConfiguration getChunkConfiguration() {
        return configuration;
    }
}
