import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JOptionPane;


public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static final int WIDTH = 250;
    public static final int HEIGHT = 1500;

    private Thread thread;
    private boolean running;

    private BufferedImage image;
    private Graphics2D g;

    private int FPS = 30;
    private int targetTime = 1000 / FPS;

    private TileMap tileMap;
    private Player player;

    private Monster monster;
    private Monster monster2;
    private Monster monster3;
    private Monster monster4;
    private Monster monster5;

    private FlyingMonster flying;
    private PurpleMonster flying2;
    private FlyingMonster flying3;
    private FlyingMonster flying4;
    private FlyingMonster flying5;
    private FlyingMonster flying6;
    private FlyingMonster flying7;
    private FlyingMonster flying8;
    private FlyingMonster flying9;
    private FlyingMonster flying10;
    private FlyingMonster flying11;
    private SecondFlyingMonster flying16;
    private SecondFlyingMonster flying17;

    private SecondFlyingMonster flying12;
    private SecondFlyingMonster flying13;
    private SecondFlyingMonster flying14;
    private SecondFlyingMonster flying15;

    private PurpleMonster flying18;
    private PurpleMonster flying19;





    private ArrayList<Character> characters;




    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if(thread == null) {
            thread = new Thread(this);
            thread.start();
        }
        addKeyListener(this);
    }

    public void run() {
      init();

      long startTime;
      long urdTime;
      long waitTime;

      while(running) {
          startTime = System.nanoTime();

          update();
          render();
          draw();



          urdTime = (System.nanoTime() - startTime) / 1000000;
          waitTime = targetTime - urdTime;

          try {
              Thread.sleep(waitTime);
          }
          catch(Exception e) {

          }

          if(player.getHealth() <= 0) {
            JOptionPane.showMessageDialog(null, "Game Over!", "UnderGround Escape", JOptionPane.INFORMATION_MESSAGE);
            break;
          }

      }
    }

    private void init() {
        running = true;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        tileMap = new TileMap("testmap3.txt", 32);
        tileMap.loadTiles("graphics/tileset3.gif");

        characters = new ArrayList<Character>();

        player = new Player(tileMap);
        player.setx(50);
        player.sety(50);


        monster = new Monster(tileMap);
        monster.setx(375);
        monster.sety(100);
        characters.add(monster);

        monster2 = new Monster(tileMap);
        monster2.setx(400);
        monster2.sety(500);
        characters.add(monster2);

        monster3 = new Monster(tileMap);
        monster3.setx(300);
        monster3.sety(2000);
        characters.add(monster3);

        monster4 = new Monster(tileMap);
        monster4.setx(900);
        monster4.sety(525);
        characters.add(monster4);

        monster5 = new Monster(tileMap);
        monster5.setx(725);
        monster5.sety(850);

        flying = new FlyingMonster(tileMap);
        flying.setx(800);
        flying.sety(75);
        characters.add(flying);

        flying2 = new PurpleMonster(tileMap);
        flying2.setx(350);
        flying2.sety(2480);
        characters.add(flying2);

        flying3 = new FlyingMonster(tileMap);
        flying3.setx(600);
        flying3.sety(2000);
        characters.add(flying3);

        flying4 = new FlyingMonster(tileMap);
        flying4.setx(1600);
        flying4.sety(75);
        characters.add(flying4);

        flying5 = new FlyingMonster(tileMap);
        flying5.setx(1600);
        flying5.sety(370);
        characters.add(flying5);

        flying6 = new FlyingMonster(tileMap);
        flying6.setx(2000);
        flying6.sety(140);
        characters.add(flying6);

        flying7 = new FlyingMonster(tileMap);
        flying7.setx(2000);
        flying7.sety(430);
        characters.add(flying7);

        flying8 = new FlyingMonster(tileMap);
        flying8.setx(2000);
        flying8.sety(785);
        characters.add(flying8);

        flying9 = new FlyingMonster(tileMap);
        flying9.setx(2000);
        flying9.sety(1005);
        characters.add(flying9);

        flying10 = new FlyingMonster(tileMap);
        flying10.setx(2000);
        flying10.sety(1425);
        characters.add(flying10);

        flying11 = new FlyingMonster(tileMap);
        flying11.setx(2000);
        flying11.sety(1775);
        characters.add(flying11);

        flying12 = new SecondFlyingMonster(tileMap);
        flying12.setx(1500);
        flying12.sety(2900);
        characters.add(flying12);

        flying13 = new SecondFlyingMonster(tileMap);
        flying13.setx(1550);
        flying13.sety(2800);
        characters.add(flying13);

        flying14 = new SecondFlyingMonster(tileMap);
        flying14.setx(1525);
        flying14.sety(2700);
        characters.add(flying14);

        flying15 = new SecondFlyingMonster(tileMap);
        flying15.setx(1650);
        flying15.sety(2600);
        characters.add(flying15);

        flying16 = new SecondFlyingMonster(tileMap);
        flying16.setx(1100);
        flying16.sety(2125);
        characters.add(flying16);

        flying17 = new SecondFlyingMonster(tileMap);
        flying17.setx(1100);
        flying17.sety(2250);
        characters.add(flying17);

        flying18 = new PurpleMonster(tileMap);
        flying18.setx(375);
        flying18.sety(2780);
        characters.add(flying18);

        flying19 = new PurpleMonster(tileMap);
        flying19.setx(365);
        flying19.sety(2160);
        characters.add(flying19);

    }

//////////////////////////////////////////////////////////////////////////

    private void update() {
        tileMap.update();
        player.update(characters);

        for(int i = 0; i < characters.size(); i++) {
          characters.get(i).update(player);
        }

    }

    private void render() {

      g.setColor(Color.BLUE);
      g.fillRect(0, 0, WIDTH, HEIGHT);


      tileMap.draw(g);
      player.draw(g);

      for(int i = 0; i < characters.size(); i++) {
        characters.get(i).draw(g);
      }

      g.drawString("Health %" + player.getHealth(), 75, 75);

    }

    private void draw() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }

    public void keyTyped(KeyEvent key) {}
    public void keyPressed(KeyEvent key) {

        int code = key.getKeyCode();

        if(code == KeyEvent.VK_LEFT) {
            player.setLeft(true);
        }
        if(code == KeyEvent.VK_RIGHT) {
            player.setRight(true);
        }
        if(code == KeyEvent.VK_SPACE) {
            player.setJumping(true);
        }


    }
    public void keyReleased(KeyEvent key) {
      int code = key.getKeyCode();

      if(code == KeyEvent.VK_LEFT) {
          player.setLeft(false);
      }
      if(code == KeyEvent.VK_RIGHT) {
          player.setRight(false);
      }
    }






}
