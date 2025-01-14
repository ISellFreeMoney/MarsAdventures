package chunk.io;

public interface FileConfiguration {
    public static final String DEFAULT_PATH = "chunks/";

    String getPath();
    void setPath(String file);
    FileNameConverter getConverter();
    void setConverter(FileNameConverter converter);
}
