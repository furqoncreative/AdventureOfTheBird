import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cacing here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cacing extends Makanan
{
    private static final int RESPAWN_DELAY = 5000; //milisecond
    private static final int ENERGY = 0;
    
    public Cacing(){
    }
    public Cacing(World world){
        super(world, ENERGY, RESPAWN_DELAY);
    }
    public void act(){
        behavior();   
    } 
}
