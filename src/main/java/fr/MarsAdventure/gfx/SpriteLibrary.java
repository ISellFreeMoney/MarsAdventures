package fr.MarsAdventure.gfx;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class SpriteLibrary {

    private final Map<String, SpriteSet> units;
    private final Map<String, Image> tiles;

    public SpriteLibrary(){
        units = new HashMap<>();
        tiles = new HashMap<>();
        loadSpritesFromDisk();
    }

    public void loadSpritesFromDisk(){
        loadUnits("/sprites/units");
        loadTiles("/sprites/tiles");
    }

    private void loadUnits(String path){
        String[] imagePaths = getImagesInFolder(path);
        String imageName = "";
        String precedentFolder = "";
        String folderName = "";
        SpriteSet spriteSet = new SpriteSet();
        for(String imagePath : imagePaths){
            imagePath = imagePath.replace('\\', '/');
            imagePath = imagePath.substring(imagePath.indexOf("/") + 1);
            if(imagePath.contains("/")) {
                imageName = imagePath.substring(imagePath.lastIndexOf("/"));
                folderName = imagePath.substring(0, imagePath.lastIndexOf('/'));
            } else {
                imageName = imagePath;
                folderName = "";
            }
            imageName = imageName.substring(1, imageName.length() - 4);
            if(!folderName.equals(precedentFolder)){
                spriteSet = new SpriteSet();
            }
            spriteSet.addSheet(
                    imageName,
                    ImageUtils.loadImage(path + "/" + imagePath)
            );
            units.put(folderName, spriteSet);
            precedentFolder = folderName;
        }
    }

    private void loadTiles(String path){
        String[] imagesPaths = getImagesInFolder(path);
        for(String imagePath : imagesPaths){
            imagePath = imagePath.replace('\\', '/');
            imagePath = imagePath.substring(imagePath.indexOf("/") + 1);
            tiles.put(
                    imagePath.substring(0, imagePath.length() - 4),
                    ImageUtils.loadImage(path + "/" + imagePath)
            );
        }
    }

    private String[] getImagesInFolder(String basePath) {
        basePath = basePath.replace('/', '\\');
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/files.txt"), StandardCharsets.UTF_8));
        List<String> lines = new ArrayList<>(bufferedReader.lines().toList());
        String finalBasePath1 = basePath;
        lines.removeIf(line -> !line.contains(finalBasePath1.substring(finalBasePath1.lastIndexOf('\\') + 1)));
        return lines.toArray(String[]::new);
    }

    public SpriteSet getUnit(String name) {
        return units.get(name);
    }

    public Image getTile(String name){
        return tiles.get(name);
    }
}
