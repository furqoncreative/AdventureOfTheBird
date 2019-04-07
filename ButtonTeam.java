import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ButtonTeam here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ButtonTeam extends Button
{
    public void act(){
        if(Greenfoot.mouseClicked(this)){
            getWorldOfType(StartMenu.class).stopMusic();
            Greenfoot.setWorld(new TeamMenu());
           
        }
    }   
}
