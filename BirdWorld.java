import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BirdWorld here.
 * 
 * @author (Deden muhamad Furqon) 
 * @version (a version number or a date)
 */
public class BirdWorld extends World
{

    private Timer timer = new Timer();
    private int MAX_FOOD = 2;
    private int MAX_ENEMY = 3;
    public Status status;
    private Spawner spawner;
    private GreenfootSound musik;
    private boolean hasStatusDisplayed = false;

    int[][] layout = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 1, 1, 1, 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, 
            {0, 0, 1, 1, 0, 0, 0, 0, 0, 2, 0, 0, 1, 1, 1, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0}};

    public BirdWorld(){
        super(800, 600, 1);
        createWallLayout();
        if(status == null) status = new Status();
        prepare();
    }

    public BirdWorld(Status status){
        this();
        this.status = status;

        musik = new GreenfootSound("starmenu_musik.wav");

        spawner = new Spawner((World)this);
        status.getImage().scale(800, 40);
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
        final int WALL_SIZE = 45;
        int xPos;
        int yPos = WALL_SIZE/2;
        for(int i = 0; i < layout.length ; i++){
            xPos = WALL_SIZE/2;
            for(int j = 0; j < layout[0].length ; j++){
                if(layout[i][j] == 1){
                    addObject(new BirdWall(), xPos, yPos);
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
        //Spawning enemy
        for(int i = 0 ; i < MAX_ENEMY ; i++){
            spawner.spawnKucing();
        }
        //Spawning food
        for(int i = 0 ; i < MAX_FOOD ; i++){
            spawner.spawnCacing();
        }
        spawner.spawnBurung(status, false);
    }

    public void stopMusic(){
        musik.stop();
    }

    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    }
}
