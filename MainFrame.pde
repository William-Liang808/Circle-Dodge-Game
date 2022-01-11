Player player;
Projectile projectile;
public ArrayList<Projectile> projectileList;
public int d = 0;
public int seconds;
public boolean collision;
private int projectileCounter;

void setup() {
  size(1000, 800); 
  player = new Player();
  projectile = new Projectile();
  projectileList = new ArrayList<Projectile>();
}
 
void draw() {
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

void keyPressed() {
  player.keyPressed(); 
  
  // Restarts game
  if(key == ' ' && collision == true) {
    setup(); 
    loop();
    seconds = 0;
    collision = false;
  }
}

void keyReleased() {
  player.keyReleased(); 
}
