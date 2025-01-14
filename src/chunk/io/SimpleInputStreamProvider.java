package chunk.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SimpleInputStreamProvider implements InputStreamProvider {
    @Override
    public InputStream getInputStream(String file) throws IOException {
        return new FileInputStream(file);
    }
}
