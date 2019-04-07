import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pepaya here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pepaya extends Makanan
{
    private static final int RESPAWN_DELAY = 3000; //milisecond
    private static final int ENERGY = 10;
    
    public Pepaya(){
    }
    public Pepaya(World world){
        super(world, ENERGY, RESPAWN_DELAY);
    }
    public void act(){
        behavior();
    }  
}
