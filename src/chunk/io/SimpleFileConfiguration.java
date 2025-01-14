package chunk.io;

public class SimpleFileConfiguration implements FileConfiguration{

    private String path;
    private FileNameConverter converter;

    public SimpleFileConfiguration(String path, FileNameConverter converter){
        this.path = path;
        this.converter = converter;
    }

    public SimpleFileConfiguration(String path){
        this(path, null);
    }

    public SimpleFileConfiguration(){
        this(DEFAULT_PATH);
    }

    @Override
    public String getPath() {
        if(path.charAt(path.length() - 1) != '/'){
            path += '/';
        }
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public FileNameConverter getConverter() {
        return converter;
    }

    @Override
    public void setConverter(FileNameConverter converter) {
        this.converter = converter;
    }
}
