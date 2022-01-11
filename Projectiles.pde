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
  
  void draw() {
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
  void update() {
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
  float getX() {
    return x;  
  }
  float getY() {
   return y; 
  }
  
  // Spawns projectiles
  void spawn() {
    if(spawnTime == 15) {
      Projectile newProjectile = new Projectile();
      projectileList.add(newProjectile);
      spawnTime = 0;
    }
  }
  
  // Collision
  void collision() {
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
