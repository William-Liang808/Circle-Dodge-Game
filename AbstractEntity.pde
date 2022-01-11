abstract class Entity {
  float x; // X-Coord Location
  float y; // Y-Coord Location
  abstract void draw();  //Draw function; Spawns entities
  abstract void update();  //Update Function; Moves entities
}
