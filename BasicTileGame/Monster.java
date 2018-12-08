import java.awt.Image;
import java.awt.*;
import java.util.*;

import javax.swing.ImageIcon;

public class Monster extends Character {

      public Player player = new Player(tileMap);
      private String Id;

      public Monster(TileMap tm) {

           tileMap  = tm;
           width = 20;
           height = 20;

           moveSpeed = 0.05;
           maxSpeed = .2;
           maxFallingSpeed = 12;
           stopSpeed = 0.50;
           jumpStart = -11.0;
           gravity = 0.55;

      }

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

      private void calculateCorners(double x, double y) {
          int leftTile = tileMap.getColTile((int) (x - width / 2));
          int rightTile = tileMap.getColTile((int) (x + width / 2) - 1);
          int topTile = tileMap.getRowTile((int) (y - height / 2));
          int bottomTile = tileMap.getRowTile((int) (y + height / 2) - 1);
          topLeft = tileMap.isBlocked(topTile, leftTile);
          topRight = tileMap.isBlocked(topTile, rightTile);
          bottomLeft = tileMap.isBlocked(bottomTile, leftTile);
          bottomRight = tileMap.isBlocked(bottomTile, rightTile);


      }



  ///////////////////////////////////////////////////////////////////////////////
      public void update(ArrayList<Character> characters) {}
      public void update(Player player) {

          //determine next position
          if(x < player.getx()) {
              dx += moveSpeed;
              if(dx < -maxSpeed) {
                  dx = maxSpeed;
              }
          }
          else if(x > player.getx()) {
              dx -= moveSpeed;
              if(dx > maxSpeed) {
              dx = -maxSpeed;
            }
          }
          else {
              if(dx > 0) {
                dx -= stopSpeed;
                if(dx < 0) {
                    dx = 0;
                }
              }
              else if(dx < 0) {
                  dx += stopSpeed;
                  if(dx > 0) {
                      dx = 0;
                  }
              }
            }



          if(falling) {
              dy += gravity;
              if(dy > maxFallingSpeed) {
                  dy = maxFallingSpeed;
              }
          }
          else {
              dy = 0;
          }

          //check collisions
          int currCol = tileMap.getColTile((int) x);
          int currRow = tileMap.getRowTile((int) y);

          double tox = x + dx;
          double toy = y + dy;

          double tempx = x;
          double tempy = y;
           //how head bumps
          calculateCorners(x, toy);
          if(dy < 0) {
              if(topLeft || topRight) {
                  dy = 0;
                  tempy  = currRow * tileMap.getTileSize() + height / 2;
              }
              else {
                  tempy += dy;
              }
          }
          if(dy > 0) {
              if(bottomLeft || bottomRight) {
                  dy = 0;
                  falling = true;
                  tempy = (currRow + 1) * tileMap.getTileSize() - height / 2;
              }
              else {
                  tempy += dy;
              }
          }

          calculateCorners(tox, y);
          if(dx < 0) {
              if(topLeft || bottomLeft) {
                  dx = 0;
                  tempx = currCol * tileMap.getTileSize() + width / 2;
              }
              else {
                  tempx += dx;
              }
          }
          if(dx > 0) {
              if(topRight || bottomRight) {
                  dx = 0;
                  tempx = (currCol + 1) * tileMap.getTileSize() - width / 2;
              }
              else {
                  tempx += dx;
              }
          }

          if(!falling) {
              calculateCorners(x, y + 1);
              if(!bottomLeft && !bottomRight) {
                  falling = true;
              }
          }

          x = tempx;
          y = tempy;



      }

      public void draw(Graphics2D g) {
           int tx = tileMap.getx();
           int ty = tileMap.gety();

           g.setColor(Color.RED);
           g.fillRect(
               (int) (tx + x - width / 2),
               (int) (ty + y - height / 2),
               width,
               height
           );



      }

      public void move(Player player) {

        /*  if(x < player.getx()) x++;
          else if(x > player.getx()) x--;

       if(y < player.gety()) y++;
          else if(y > player.gety()) y--;
           */
     }


  }
