package chunk.io;

import chunk.Chunk;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class SimpleChunkLoader extends SimpleFileConfiguration implements ChunkLoader{

    private InputStreamProvider provider;
    private  boolean loading;
    private FileNameConverter nameConverter;

    public SimpleChunkLoader(InputStreamProvider provider){
        this.provider = provider;
        nameConverter = new EncryptedFileNameConverter(new SimpleFileNameConverter());
    }

    @Override
    public void setProvider(InputStreamProvider provider) {
        this.provider = provider;
    }

    @Override
    public boolean isLoading() {
        return loading;
    }

    @Override
    public Chunk load(int indexX, int indexY) throws IOException {
        ObjectInputStream in = null;
        InputStream inner = null;
        try {
            if(provider != null){
                String fileName = getPath() + nameConverter.convert(indexX, indexY);
                inner = provider.getInputStream(fileName);
                in = new ObjectInputStream(inner);
                Chunk chunk = (Chunk)in.readObject();
                return chunk;
            } else {
                throw new IOException("InputStreamProvider is not set yet");
            }
        } catch (ClassNotFoundException e) {
            throw new IOException("Target file does not contain any chunk instance");
        } finally {
            loading = false;
            if( in != null){
                in.close();
            }

            if(inner != null){
                inner.close();
            }
        }
    }
}
