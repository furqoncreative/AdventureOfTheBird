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
    private Status status;
    private World world;
    private boolean isEnergyWorld;
    private GreenfootImage lose;
    private GreenfootImage win;
    private boolean hasEnd = false;
    private GreenfootSound musik;
    private boolean isMusicPlaying = false;

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
    
    public Burung(World world, Status status, boolean isEnergyWorld){
        this.world = world;
        this.status = status;
        this.isEnergyWorld = isEnergyWorld;
        prepareImage();
    }
    public void act() 
    {
        if(!hasSpawnPosition) setSpawnPosition();
        if(hasEnd){
            if(status.isWin()){
                win();
            }
            else{
                lose();
        }
        }      
        movement();
        animate();
        checkCollision();
        if(status.isWin() || status.isLose()) hasEnd = true;
    }  
    
    private void setSpawnPosition(){
        xSpawnPosition = getX();
        ySpawnPosition = getY();
        hasSpawnPosition = true;
    }
    
    private void lose(){
        setImage(lose);
        setLocation(world.getWidth()/2, world.getHeight()/2);
       
    }

    private void win(){
       setImage(win);
        setLocation(world.getWidth()/2, world.getHeight()/2);
  
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
        
        if(flyCounter >= flyThreshold){
            status.decreaseEnergy();
            flyCounter = 0;
        }
        
       
    
    }
    
    private void checkCollision(){
        Makanan makanan = (Makanan)getOneIntersectingObject(Makanan.class);
        Predator predator = (Predator)getOneIntersectingObject(Predator.class);
        if(makanan != null){
            status.increaseEnergy(makanan.energyValue);
            status.increaseScore(makanan.energyValue);
            if(makanan.isCacing()) status.increaseWormEaten();
        }
        if(predator != null){
            predator.respawn();   respawn();
            status.decreaseLives(2);
        }
        if(isTouching(BirdWall.class)){
            Greenfoot.playSound("kena.mp3");
            respawn();
            status.decreaseLives(1);
        }
        if(isTouching(EnergyWall.class)){
            Greenfoot.playSound("kena.mp3");
            respawn();
       
        }
        if(isTouching(Pohon.class)){
            if(Greenfoot.isKeyDown("Enter")){
                if(isEnergyWorld){
            ((EnergyWorld)world).showText("Press Enter" , 250, 250);
        }else{
            ((BirdWorld)world).showText("Press Enter" , 250, 250);
        } 
               
            changeWorld();
        }
            
        }
    }
    
    private void changeWorld(){
        if(isEnergyWorld){
            ((EnergyWorld)world).stopMusic();
            Greenfoot.setWorld(new BirdWorld(status));
            return;
        }else{
            ((BirdWorld)world).stopMusic();
            Greenfoot.setWorld(new EnergyWorld(status));
            return;
        }
    }
    
    private void respawn(){
        setLocation(xSpawnPosition, ySpawnPosition);
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
             lose = new GreenfootImage("YouLose.png");
             win = new GreenfootImage("YouWin.png");
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
