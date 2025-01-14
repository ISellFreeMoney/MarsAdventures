package chunk.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SimpleOutputStreamProvider implements OutputStreamProvider{
    @Override
    public OutputStream getOutputStream(String file) throws IOException {
        return new FileOutputStream(file);
    }
}
