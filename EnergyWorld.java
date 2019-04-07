import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnergyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnergyWorld extends World
{

    public Status status;
    private Timer timer = new Timer();
    private int MAX_FOOD = 3;
    private int MAX_ENEMY = 3;
    private Spawner spawner;
    private GreenfootSound musik;
    private boolean hasStatusDisplayed = false;
   
    int[][] layout = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, 
        {0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
        {0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
        {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1}, 
        {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 2, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, 
        {0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1}, 
        {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
        {0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1}, 
        {0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1}};
    
    
    public EnergyWorld(){    
        super(600, 600, 1);
        createWallLayout();
        if(status == null) status = new Status();
    }
    
    public EnergyWorld(Status status){
        this();
        this.status = status;
        musik = new GreenfootSound("energyWorld.wav");
        spawner = new Spawner((World)this);
        status.getImage().scale(600, 40);
        addObject(status, getWidth()/2, 20);
        firstSpawn();
        musik.playLoop();
    }

    public void act(){
        if(!hasStatusDisplayed){
            status.setWorld(this);
            status.tampil();
            hasStatusDisplayed = true;
        }
    }
    private void createWallLayout(){
        final int WALL_SIZE = 30;
        int xPos;
        int yPos = WALL_SIZE/2;
        for(int i = 0; i < layout.length ; i++){
            xPos = WALL_SIZE/2;
            for(int j = 0; j < layout[0].length ; j++){
                if(layout[i][j] == 1){
                    addObject(new EnergyWall(), xPos, yPos);
                }
                if(layout[i][j] == 2){
                    addObject(new Pohon(), xPos, yPos);
                }
                xPos += WALL_SIZE;
            }
            yPos += WALL_SIZE;
        }
    }

    private void firstSpawn(){
        //Spawning food
        for(int i = 0 ; i < MAX_FOOD ; i++){
            spawner.spawnApel();
            spawner.spawnPisang();
            spawner.spawnPepaya();
        }
        //Spawning enemy
        for(int i = 0 ; i < MAX_ENEMY ; i++){
          spawner.spawnUlar();
        }
        spawner.spawnBurung(status, true);
    }
    
    public void stopMusic(){
        musik.stop();
    }
}
