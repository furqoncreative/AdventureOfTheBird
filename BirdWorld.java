import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BirdWorld here.
 * 
 * @author (Deden muhamad Furqon) 
 * @version (a version number or a date)
 */
public class BirdWorld extends World
{

    private Spawner spawner;
    private GreenfootSound bgm;
    private Timer timer = new Timer();
    private int MAX_FOOD = 2;
    private int MAX_ENEMY = 3;
    private boolean hasStatusDisplayed = false;

    int[][] layout = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 1, 1, 1, 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, 
            {0, 0, 1, 1, 0, 0, 0, 0, 0, 2, 0, 0, 1, 1, 1, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0}};
    

    public BirdWorld()
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        prepare();
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
                    addObject(new Tree(), xPos, yPos);
                }
                xPos += WALL_SIZE;
            }
            yPos += WALL_SIZE;
        }
    }

   
    private void prepare()
    {
        createWallLayout();
    }
    
    
}
