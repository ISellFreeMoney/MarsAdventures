package chunk.io;

import chunk.Chunk;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class SimpleChunkSaver extends SimpleFileConfiguration implements ChunkSaver{

    private OutputStreamProvider provider;
    private boolean saving;
    private FileNameConverter nameConverter;

    public SimpleChunkSaver(OutputStreamProvider provider){
        this.provider = provider;
        nameConverter = new EncryptedFileNameConverter(new SimpleFileNameConverter());

    }

    @Override
    public void setProvider(OutputStreamProvider provider) {
        if (provider != null)
            this.provider = provider;
    }

    @Override
    public boolean isSaving() {
        return saving;
    }

    @Override
    public void save(Chunk chunk) throws IOException {
        ObjectOutputStream out = null;
        OutputStream outer = null;

        try{
            if(provider != null){
                saving = true;

                File file = new File(getPath());
                if (!file.exists()){
                    file.mkdirs();
                }
                String fileName = getPath() + nameConverter.convert(chunk.getIndexX(), chunk.getIndexY());
                outer = provider.getOutputStream(fileName);
                out = new ObjectOutputStream(outer);
                out.writeObject(chunk);
            }else {
                throw new IOException("OutputStreamProvider is not set yet");
            }
        } finally {
            saving = false;
            if(out != null) {
                out.close();
            }

            if(outer != null){
                outer.close();
            }
        }
    }
}
