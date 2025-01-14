package chunk;

public class SimpleConfigurationProvider implements ConfigurationProvider{

    private ChunkConfiguration configuration;

    public SimpleConfigurationProvider(ChunkConfiguration configuration){
        this.configuration = configuration;
    }

    @Override
    public ChunkConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public void setConfiguration(ChunkConfiguration configuration) {
        if(configuration != null){
            this.configuration = configuration;
        }
    }
}
