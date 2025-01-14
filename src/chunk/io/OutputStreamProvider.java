package chunk.io;

import java.io.IOException;
import java.io.OutputStream;

public interface OutputStreamProvider {
    OutputStream getOutputStream(String file) throws IOException;
}
