import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 32; // 32x32 tile
    final int scale = 2;

    final int tileSize = originalTileSize * scale; // 64x64
    final int maxScreenCol = 15;
    final int maxScreenRow = 8;
    final int screenWidth = tileSize * maxScreenCol; // 2048 pixels
    final int screenHeight = tileSize * maxScreenRow; // 1152 pixels

    //FPS


    KeyHandler keyH = new KeyHandler();
    Thread gameThread;


    // Set player default pos
    int playerX = 100;
    int playerY = 100;
    int speed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground((Color.black));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            long currentTime = System.nanoTime();

            // UPDATE: update info (character pos)
            update();
            // DRAW: draw the screen
            repaint();
        }
    }

    public void update(){
        if(keyH.upPressed){
            playerY -= speed;
        }
        if(keyH.downPressed){
            playerY += speed;
        }
        if(keyH.leftPressed){
            playerX -= speed;
        }
        if(keyH.rightPressed){
            playerX += speed;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
