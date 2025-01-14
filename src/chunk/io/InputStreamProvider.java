package chunk.io;

import java.io.IOException;
import java.io.InputStream;

public interface InputStreamProvider {
    InputStream getInputStream(String file) throws IOException;
}
