import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TeamMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TeamMenu extends World
{

    /**
     * Constructor for objects of class TeamMenu.
     * 
     */
    public TeamMenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(400, 405, 1); 
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        HomeButton homeButton = new HomeButton();
        addObject(homeButton,192,345);
    }
}
