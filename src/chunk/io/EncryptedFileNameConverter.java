package chunk.io;

import util.Crypter;

public class EncryptedFileNameConverter implements FileNameConverter{

    public static final String FILE_PREFIX = "ch";
    public static final String DEFAULT_KEY = "izbvirnzo93igei923nif08";

    private FileNameConverter original;
    private Crypter crypter;

    public EncryptedFileNameConverter(FileNameConverter original, String key){
        this.original = original;
        crypter = new Crypter(key);
    }

    public EncryptedFileNameConverter(FileNameConverter original){
        this(original, DEFAULT_KEY);
    }

    @Override
    public String convert(int indexX, int indexY){
        String base = original.convert(indexX, indexY);
        return FILE_PREFIX + crypter.md5(base);
    }

}
