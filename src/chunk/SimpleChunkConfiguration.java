package chunk;

public class SimpleChunkConfiguration implements ChunkConfiguration{

    public static final long serialVersionUID = 1L;

    private int chunkWidth, chunkHeight;
    private transient ContentProvider contentProvider;
    private transient ChunkTarget focused;

    public SimpleChunkConfiguration(int chunkWidth, int chunkHeight, ContentProvider contentProvider, ChunkTarget focused){
        super();
        this.chunkWidth = chunkWidth;
        this.chunkHeight = chunkHeight;
        this.contentProvider = contentProvider;
        this.focused = focused;
    }

    public SimpleChunkConfiguration(int chunkWidth, int chunkHeight, ContentProvider contentProvider){
        this(chunkWidth, chunkHeight, contentProvider, null);
    }

    public SimpleChunkConfiguration(int chunkWidth, int chunkHeight){
        this(chunkWidth, chunkHeight, null, null);
    }

    public SimpleChunkConfiguration(){
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT, null, null);
    }

    @Override
    public int getChunkWidth() {
        return chunkWidth;
    }

    @Override
    public int getChunkHeight() {
        return chunkHeight;
    }

    @Override
    public void setChunkWidth(int width) {
        this.chunkWidth = width;
    }

    @Override
    public void setChunkHeight(int height) {
        this.chunkHeight = height;
    }

    @Override
    public void setChunkSize(int size) {
        this.setChunkWidth(size);
        this.setChunkHeight(size);
    }

    @Override
    public void setContentProvider(ContentProvider contentProvider) {
        if(contentProvider != null){
            this.contentProvider = contentProvider;
        }
    }

    @Override
    public ContentProvider getContentProvider() {
        return contentProvider;
    }

    @Override
    public void setFocused(ChunkTarget target) {
        this.focused = target;
    }

    @Override
    public ChunkTarget getFocused() {
        return focused;
    }
}
