import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ButtonMulai here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ButtonMulai extends Button
{
    public void act(){
        if(Greenfoot.mouseClicked(this)){
            status = new Status();
            getWorldOfType(StartMenu.class).stopMusic();
            Greenfoot.setWorld(new BirdWorld(status));
           
        }
    }
}
