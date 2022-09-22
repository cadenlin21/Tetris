
/**
 * The Tetris class plays the classic game Tetris. 
 * Includes increasing speed, levels, points, and displaying the next tetrad 
 * @author Caden Lin 
 * @version April 19, 2021 
 */
public class Tetris implements ArrowListener 
{
    private MyBoundedGrid<Block> grid, grid2 ; 
    private BlockDisplay display, display2; 
    private Tetrad current, next; 
    private int clearedRows, score, currentScore, level; 

    /**
     * Constructor for objects of class Tetris
     */
    public Tetris()
    {
        clearedRows = 0 ; 
        grid = new MyBoundedGrid<Block>(20,10) ; 
        grid2 = new MyBoundedGrid<Block>(10,10) ; 

        display = new BlockDisplay(grid) ; 
        display.setArrowListener(this) ; 
        display.setTitle("Tetris| Score: " + score + "\n" + " level: " + level) ; 
        display.showBlocks() ; 

        display2 = new BlockDisplay(grid2, 200, 0) ; 
        display2.setTitle("Next Piece") ; 
        display2.showBlocks() ; 

        current = new Tetrad(grid) ;  
        next = new Tetrad(grid2) ; 

    }

    /** Rotates the current tetrad. 
     * @postcondition the current tetrad is rotated 90 degrees 
     * 
     */
    public void upPressed()
    {
        current.rotate() ; 
        display.showBlocks();
    }

    /** Moves the tetrad down one row 
     * @postcondition the current tetrad is moved down 1 row 
     * 
     */
    public void downPressed()
    {
        current.translate(1,0) ; 
        display.showBlocks();
    }

    /** Moves the tetrad down left column 
     * @postcondition the current tetrad is moved left one column  
     * 
     */
    public void leftPressed()
    {
        current.translate(0,-1) ;
        display.showBlocks();
    }

    /** Moves the tetrad right one column 
     * @postcondition the current tetrad is moved right one column  
     * 
     */
    public void rightPressed()
    {
        current.translate(0,1) ; 
        display.showBlocks();
    }

    /** Moves the tetrad to the bottom of the grid 
     * @postcondition the current tetrad is moved as far down as possible
     * 
     */
    public void spacePressed()
    {
        while (current.translate(1,0)) 
            current.translate(1,0) ; 
        display.showBlocks() ; 
    }

    /** Increases the player's score 
     * @param num the level, used to determine how much to increase the score by 
     * @return the amount that the score should be increased by 
     * 
     */
    public int increaseScore(int num)
    {
        if(num == 0)
            return 0;
        if(num == 1)
            return 40;
        if(num == 2)
            return 100;
        if(num == 3)
            return 300;
        if(num == 4)
            return 1200;
        else
            throw new RuntimeException("wrong input");

    }

    /** Determines if a given row is filled with blocks or not. 
     * @param row the row to check 
     * @precondiotion the row is in the grid 
     * @return true if the row is filled, 
     *          false otherwise 
     * 
     */
    private boolean isCompletedRow(int row)
    {

        for(int i = 0 ; i < grid.getNumCols() ; i++)
        {
            if(grid.get(new Location(row,i))==null)
                return false ;
        }
        return true ; 
    }

    /** Clears a given row of blocks 
     * @param row the row to clear 
     * @precondition the row is full of blocks 
     * @postcondition the row is empty 
     * 
     */
    private void clearRow(int row) 
    {
        for(int i = 0 ; i < grid.getNumCols() ; i++)
        {
            Location temp = new Location(row, i) ; 
            grid.get(temp).removeSelfFromGrid() ; 
        }
        for(int i = row - 1 ; i >= 0 ; i--)
        {
            for(int j = 0 ; j < grid.getNumCols() ; j++)
            {
                Location temp = new Location(i,j) ; 
                if(grid.get(temp)!=null) 
                {
                    grid.get(temp).moveTo(new Location(i+1,j)) ; 
                }
            }
        }
    }

    /** Clears all completed rows in the grid 
     * @postcondition all the completed rows in the grid have been cleared 
     *                  the current score and cleared rows value is updated
     * 
     */
    private void clearCompletedRows()
    {
        for(int i = 0 ; i < grid.getNumRows() ; i++)
        {
            if(isCompletedRow(i))
            {
                clearedRows++ ; 
                currentScore++ ; 
                clearRow(i) ; 
            }
        }
    }

    /** Plays the tetris game: repeatedly pauses and then 
     * translates the tetrad down one row. The pause time 
     * starts at one second and repeatedly gets faster as the score increases, 
     * with a minimum time of 200 milliseconds. 
     * 
     * 
     */
    public void play()
    {
        while(true)
        {
            try
            {
                display.setTitle("Tetris score: " + score + "\n" + " level: " + level);
                currentScore = 0;
                int sleep = 1000 - clearedRows * 40;
                if(sleep < 200)
                {
                    Thread.sleep(200);
                }
                else
                {
                    Thread.sleep(sleep);
                }
                if(current.translate(1,0))
                    display.showBlocks() ; 
                else
                {
                    clearCompletedRows() ; 
                    score+=increaseScore(currentScore) ;
                    current = new Tetrad(grid,next.getShape()) ; 
                    for(Location loc : grid2.getOccupiedLocations())
                        grid2.remove(loc) ; 
                    next = new Tetrad(grid2) ; 

                }
            }
            catch(InterruptedException e)
            {
                //ignore
            } 
            level = clearedRows % 10 ; 
            display.showBlocks() ; 
            display2.showBlocks() ; 
        }
    }

    /** the main method - plays a game of tetris 
     * @param args arguments from the command line  
     * 
     */
    public static void main(String[] args) throws Exception
    {
        Tetris game = new Tetris();
        game.play();
    }
}
