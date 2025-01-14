package chunk.io;

public class SimpleFileNameConverter implements  FileNameConverter{

    public static final String FILE_PREFIX = "chunk";

    @Override
    public String convert(int indexX, int indexY) {
        return FILE_PREFIX + '_' + indexX + '_' + indexY;
    }
}
