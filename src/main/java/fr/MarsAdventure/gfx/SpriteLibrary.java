package fr.MarsAdventure.gfx;

import java.awt.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

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
        String[] folderNames = getFolderNames(path);

        for(String folderName : folderNames){
            SpriteSet spriteSet = new SpriteSet();
            String pathToFolder = path + "/" + folderName;
            String[] sheetsInFolder = getImagesInFolder(pathToFolder);
            for(String sheetName : sheetsInFolder){
                spriteSet.addSheet(
                        sheetName.substring(0, sheetName.length() - 4),
                        ImageUtils.loadImage(pathToFolder + "/" + sheetName)
                );
            }
            units.put(folderName, spriteSet);
        }
    }

    private void loadTiles(String path){
            String[] sheetsInFolder = getImagesInFolder(path);

            for(String fileName : sheetsInFolder){
                tiles.put(
                        fileName.substring(0, fileName.length() - 4),
                        ImageUtils.loadImage(path + "/" + fileName)
                );
            }
        }

    private String[] getImagesInFolder(String basePath) {
        try {
            URL resource = getClass().getResource(basePath);
            assert resource != null;
            Path folderPath = Paths.get(resource.toURI());
            return Files.walk(folderPath, 1)
                    .filter(Files::isRegularFile)
                    .skip(0)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .toArray(String[]::new);
        } catch (Exception e) {
            System.out.println("Can't find Images in this folder");
        }
        return new String[0];
    }

    private String[] getFolderNames(String basePath) {

        try {

            URL resource = getClass().getResource(basePath);
            assert resource != null;
            Path folderPath = Paths.get(resource.toURI());
            return Files.walk(folderPath, 1)
                    .filter(Files::isDirectory)
                    .skip(1)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .toArray(String[]::new);
        } catch (Exception e) {
            System.out.println("Can't find folders in this folder");
        }
        return new String[0];
    }

    public SpriteSet getUnit(String name) {
        return units.get(name);
    }

    public Image getTile(String name){
        return tiles.get(name);
    }
}
