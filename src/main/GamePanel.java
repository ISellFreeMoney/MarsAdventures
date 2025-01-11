package main;

import entity.Player;
import tile.TileManager;
import world.World;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    public final int originalTileSize = 32; // 32x32 tile
    final int scale = 2;

    public final int tileSize = originalTileSize * scale; // 64x64
    public final int maxScreenCol = 30;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol; // 2048 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 1152 pixels

    //WORLD SETTINGS
    public final int mapHeight = 500;
    public final int mapWidth = 500;
    public final int seed = 10;

    //FPS
    final double FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyH);
    TileManager tileM = new TileManager(this);
    public World world;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground((Color.black));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.world = new World(this, tileM);
        world.generateWorld();
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        player.draw(g2);
        g2.setBackground(Color.CYAN);
        g2.dispose();
    }
}
