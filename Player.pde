class Player extends Entity{
  private boolean up = false;
  private boolean down = false;
  private boolean left = false;
  private boolean right = false;
 
  Player() {
    x = 1000/2;
    y = 800/2;
  }
 
  void draw() {
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
  void update() { 
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
  
  void keyPressed() { // If movement keys are pressed, set boolean to true to move player
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
  
  void keyReleased() { // If movement keys are not pressed, set boolean to false to stop player movement
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
