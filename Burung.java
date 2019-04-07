import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Burung here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Burung extends Actor
{
    //General Attribute
    private int speed = 3;
    private World world;
    private boolean isEnergyWorld;
    private GreenfootImage lose;
    private GreenfootImage win;
    private boolean hasEnd = false;

    //Spawn Related
    private int xSpawnPosition;
    private int ySpawnPosition;
    private boolean hasSpawnPosition = false;

    //Fly related
    private int flyCounter = 0;
    private int flyThreshold = 8;

    //Animation related
    private GreenfootImage[][] sprites;
    private Timer animation = new Timer();
    private int animationDelay = 100;
    private int spriteCounter;
    private int facing = 0; //0 = kanan, 1 = kiri
    
    public Burung(){
        prepareImage();
    }
    public void act() 
    {
        movement();
        animate();
        
    }    
    
    private void movement(){
        if(Greenfoot.isKeyDown("A")){
            this.setLocation(getX()-speed, getY());
            facing = 1;
            flyCounter++;
        }
        if(Greenfoot.isKeyDown("W")){
            this.setLocation(getX(), getY()-speed);
            flyCounter++;
        }
        if(Greenfoot.isKeyDown("D")){
            this.setLocation(getX()+speed, getY());
            facing = 0;
            flyCounter++;
        }
        if(Greenfoot.isKeyDown("S")){
            this.setLocation(getX(), getY()+speed);
            flyCounter++;
        }

    
    }

    private void prepareImage(){
        String facing;
        sprites = new GreenfootImage[2][8];
        for(int i = 0; i < 2 ; i++){
            switch(i){
                case 0:
                facing = "right";
                break;
                default:
                facing = "left";
                break;
            }
            for(int j = 0 ; j < 5 ; j++){
                sprites[i][j] = new GreenfootImage("/Burung/bird" + facing + (j+1) + ".png");
            }
            for(int j = 5 ; j < 8 ; j++){
                sprites[i][j] = new GreenfootImage("/Burung/bird" + facing + (9-j) + ".png");
            }
        }
  
    }

    private void animate(){
        if(animation.getTimer() >= animationDelay){
            spriteCounter++;
            if(spriteCounter >= sprites[0].length){
                spriteCounter = 0;
            }
            this.setImage(sprites[facing][spriteCounter]);
            animation.markTimer();
        }
    }
}
