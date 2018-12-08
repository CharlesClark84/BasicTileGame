import java.awt.Image;
import java.awt.*;
import java.util.*;


public abstract class Character {


  protected double x;
  protected double y;
  protected double dx;
  protected double dy;

  protected int width;
  protected int height;

  protected boolean left;
  protected boolean right;
  protected boolean jumping;
  protected boolean falling;



  protected double moveSpeed;
  protected double maxSpeed;
  protected double maxFallingSpeed;
  protected double stopSpeed;
  protected double jumpStart;
  protected double gravity;
  protected int health;
  protected String Id;

  protected TileMap tileMap;

  protected boolean topLeft;
  protected boolean topRight;
  protected boolean bottomLeft;
  protected boolean bottomRight;
  protected Monster monster;


  public void setx(int i) { x = i; }
  public void sety(int i) { y = i; }
  public double getx() {
       return x;
  }


  public double gety() {
       return y;
  }

  public void setLeft(boolean b) { left = b; }
  public void setRight(boolean b) { right = b; }
  public void setJumping(boolean b) {
      if(!falling) {
          jumping = true;
      }
  }

  public void setWidth(int width) {
      this.width = width;
  }
  public int getWidth() {
      return width;
  }
  public void setHeight(int height) {
      this.height = height;
  }
  public int getHeight() {
      return height;
  }

  public abstract void draw(Graphics2D g);

  public abstract void update(Player player);
  public abstract void update(ArrayList<Character> characters);

}
