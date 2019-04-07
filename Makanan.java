import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Makanan here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Makanan extends Actor
{
    private Timer timer = new Timer();
    public World world;
    public int energyValue;
    public int respawnDelay;
    private boolean isCacing = false;
    
    public Makanan(){
    }
    
    public Makanan(World world, int energyValue, int respawnDelay){
        this.world = world;
        this.energyValue = energyValue;
        if(energyValue == 0) isCacing = true;
        this.respawnDelay = respawnDelay;
        timer.markTimer();
    }
    
    public void behavior(){
        if(world == null) return;
        if(timer.getTimer() > respawnDelay){
            randomLocation();
            timer.markTimer();
        }
        checkCollision();
    }
    
    private void randomLocation(){
       while(true){
            int xPos = Greenfoot.getRandomNumber(world.getWidth());
            int yPos = Greenfoot.getRandomNumber(world.getHeight());
            if(isValidLocation(xPos, yPos)){
                setLocation(xPos, yPos);
                return;
            }
       }
    }
    
    private void checkCollision(){
        if(isTouching(Burung.class)){
            randomLocation();
            timer.markTimer();
        }
     
    }
    
    public boolean isCacing(){
        return isCacing;
    }
    
    private boolean isValidLocation(int x, int y){
        int spawnRange = 15; //Jarak toleransi antar object 
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
