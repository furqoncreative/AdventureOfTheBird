import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartMenu extends World
{

    private boolean isMusicPlaying = false;
    private GreenfootSound musik;
    public StartMenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        prepare();
        musik = new GreenfootSound("starmenu_musik.wav");
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        addObject(new ButtonMulai(), getWidth()/2, 400);
        addObject(new ButtonTeam(), getWidth()/2, 500);
        
    }
    
    public void act(){
        if(!isMusicPlaying){
            musik.playLoop();
            isMusicPlaying = true;
        }
    }

    public void stopMusic(){
        musik.stop();
    }
}
