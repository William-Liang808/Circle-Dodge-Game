import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class MainFrame extends PApplet {

Player player;
Projectile projectile;
public ArrayList<Projectile> projectileList;
public int d = 0;
public int seconds;
public boolean collision;
private int projectileCounter;

public void setup() {
   
  player = new Player();
  projectile = new Projectile();
  projectileList = new ArrayList<Projectile>();
}
 
public void draw() {
  smooth(); //Sets antiasliasing on for smoother gameplay
  background(255);
  player.draw(); // Continuously called on draw method in player class
  player.update();
  projectile.draw();
  projectile.update();
  projectile.spawn();
  projectile.collision();
  
  for(Projectile element: projectileList) {
     try{
       
      // Deletes projectiles no longer on screen
      if(element.x > 1000 || element.x < 0 || element.y > 800 || element.y < 0) {
        projectileList.remove(projectileCounter);
        projectileList.trimToSize();
      }
      
      projectileCounter++;
      element.draw();
      element.collision();
     }catch(Exception e){
      }
  }
 
  d++;
  if(d==60) { // Game runs 60 frames per second. every 60 frames add 1 to second count
    seconds++;
    d=0;
  }
  textSize(30);
  text("Seconds Survived: " + seconds,20,780);
}

public void keyPressed() {
  player.keyPressed(); 
  
  // Restarts game
  if(key == ' ' && collision == true) {
    setup(); 
    loop();
    seconds = 0;
    collision = false;
  }
}

public void keyReleased() {
  player.keyReleased(); 
}
abstract class Entity {
  float x; // X-Coord Location
  float y; // Y-Coord Location
  public abstract void draw();  //Draw function; Spawns entities
  public abstract void update();  //Update Function; Moves entities
}
class Player extends Entity{
  private boolean up = false;
  private boolean down = false;
  private boolean left = false;
  private boolean right = false;
 
  Player() {
    x = 1000/2;
    y = 800/2;
  }
 
  public void draw() {
    fill(0,0,255); 
    stroke(255,0,0);
    circle(x,y,50); 
    update(); 
    smooth(); //Sets antiasliasing on for smoother gameplay
    
    // Game Border
    if(x > 975) { // window width (1000) - circle radius (25)
      x = 975;
    }else if(x < 25) {
       x = 25;
    }
    
    if(y > 775) { // window height (800) - circle radius (25)
      y = 775;  
    }else if(y < 25) {
       y = 25; 
    } 
  }
  
  // Player Movement
  public void update() { 
     if (left)  {
       x -= 3;
     }
     if (right)  {
       x += 3;
     }
     if (up)  {
       y -= 3;
     }
     if (down)  {
       y += 3;
     }
  }
  
  public void keyPressed() { // If movement keys are pressed, set boolean to true to move player
    if (key == 'a' || key == 'A' || keyCode == LEFT)  {
       left = true;
     }
     else if (key == 'd' || key == 'D' || keyCode == RIGHT)  {
       right = true;
     }
     else if (key == 'w' || key == 'W' || keyCode == UP)  {
       up = true;
     }
     else if (key == 's' || key == 'S' || keyCode == DOWN)  {
       down = true;
     }
  }
  
  public void keyReleased() { // If movement keys are not pressed, set boolean to false to stop player movement
     if (key == 'a' || key =='A' || keyCode == LEFT) {
      left = false;
     }
     else if (key == 'd' || key == 'D' || keyCode == RIGHT)  {
       right = false;
     }
     else if (key == 'w' || key == 'W' || keyCode == UP)  {
       up = false;
     }
     else if (key == 's' || key == 'S' || keyCode == DOWN)  {
       down = false;
     }
  }
}
public class Projectile extends Entity {
  private int spawnTime = 0;
  private int spawnPos;
  private int counter = 0;
  private double dx;
  private double dy;
  public double projectileX;
  public double projectileY;
  public double distance;
  
  
  Projectile() {
    spawnPos = (int)random(4); 
    if(spawnPos == 0) { // Randomly picks left side y axis of border to spawn
      x=10;
      y = (int)random(781); // Random y-coord
    }
    else if(spawnPos == 1) { //Randomly picks top side of x axis to spawn
      y=10;
      x = (int)random(981); // Random x-coord
    }
    else if(spawnPos == 2) { // Randomly picks right side of y axis to spawn
      x=980;
      y = (int)random(781); // Random y-coord
    }
    else if(spawnPos == 3) { // Randomly picks bottom side of x axis to spawn
      y=780;
      x = (int)random(981); // Random x-coord
    }
    dx = 3;
    dy = 3;
  }
  
  public void draw() {
    fill(255,0,0);
    
    if(spawnPos == 0) { 
      circle(x,y,20);
    }
    else if(spawnPos == 1){ 
      circle(x,y,20);
    }
    else if(spawnPos == 2) { 
      circle(x,y,20);
    }
    else if(spawnPos == 3) { 
      circle(x,y,20);
    }
    
    
    update();
    spawnTime++;
    
    counter++;
    if(counter == 2) {
      dy++;
      dx++;
      counter = 0;
    }
  }
  
  // Projectile Movement
  public void update() {
    if(spawnPos == 0) {
      x+=dx;
    }
    else if(spawnPos == 1){ 
      y+=dy;
    }
    else if(spawnPos == 2) { 
      x-=dy;
    }
    else if(spawnPos == 3) { 
      y-=dx;
    }
  }
  
  // Coordinate Getters
  public float getX() {
    return x;  
  }
  public float getY() {
   return y; 
  }
  
  // Spawns projectiles
  public void spawn() {
    if(spawnTime == 15) {
      Projectile newProjectile = new Projectile();
      projectileList.add(newProjectile);
      spawnTime = 0;
    }
  }
  
  // Collision
  public void collision() {
    projectileX = player.x - x;
    projectileY = player.y - y;
    distance = Math.sqrt((projectileX * projectileX) + (projectileY * projectileY));
    
    if(distance < 35) {
      collision = true;
      noLoop(); // Stops draw loop
      background(0);
      textSize(100);
      text("Game Over", 235, 400);
      textSize(32);
      text("Press ESC to exit", 375, 450);
      text("Press SPACEBAR to restart", 310, 500);
    }
  }
}
  public void settings() {  size(1000, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "MainFrame" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
