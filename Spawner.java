import greenfoot.*;
import java.util.List;

public class Spawner{
    private World world;
    private boolean isBirdWorld;
    private Status status;

    public Spawner(){
    }
    public Spawner(World world){
        this.world = world;
    }
    public Spawner(EnergyWorld world){
        this.world = (World)world;
        isBirdWorld = false;
    }
    public Spawner(BirdWorld world){
        this.world = (World)world;
        isBirdWorld = true;
    }

    public void spawnBurung(Status status, boolean isBirdWorld){
        randomSpawn(new Burung(world, status, isBirdWorld));
    }
    
    public void spawnUlar(){
        randomSpawn(new Ular(world));
    }
    
    public void spawnApel(){
        randomSpawn(new Apel(world));
    }

    public void spawnPisang(){
        randomSpawn(new Pisang(world));
    }

    public void spawnPepaya(){
        randomSpawn(new Pepaya(world));
    }


    public void spawnKucing(){
        randomSpawn(new Kucing(world));
    }
    
    public void spawnCacing(){
        randomSpawn(new Cacing(world));
    }
    
    private void randomSpawn(Actor act){
       while(true){
            int xPos = Greenfoot.getRandomNumber(world.getWidth());
            int yPos = Greenfoot.getRandomNumber(world.getHeight());
            if(isValidLocation(xPos, yPos)){
                world.addObject(act, xPos, yPos);
                return;
            }
        }
    }

    private boolean isValidLocation(int x, int y){
        int spawnRange = 30; //Jarak toleransi antar object 
        //Pengecekan object apa saja yang berada pada posisi x dan y
        List<Actor> actors = world.getObjectsAt(x, y, null);
        actors.addAll(world.getObjectsAt(x+spawnRange, y+spawnRange, null));
        actors.addAll(world.getObjectsAt(x-spawnRange, y-spawnRange, null));
        actors.addAll(world.getObjectsAt(x+spawnRange, y-spawnRange, null));
        actors.addAll(world.getObjectsAt(x-spawnRange, y+spawnRange, null));
        actors.addAll(world.getObjectsAt(x+spawnRange, y, null));
        actors.addAll(world.getObjectsAt(x-spawnRange, y, null));
        actors.addAll(world.getObjectsAt(x, y-spawnRange, null));
        actors.addAll(world.getObjectsAt(x, y+spawnRange, null));
        return actors.isEmpty();
    }
}
