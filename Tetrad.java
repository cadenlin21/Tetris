import java.awt.Color;
import java.util.concurrent.Semaphore ; 
/**
 * The Tetard class makes four-block tetrads for the tetris game. 
 *
 * @author Caden Lin 
 * @version April 19, 2021 
 */
public class Tetrad
{
    private Block[] blocks; 
    private MyBoundedGrid<Block> grid ; 
    private Location[] locs; 
    private Semaphore lock; 
    private int shape ; 
    /**
     * Constructor for objects of class Tetrad
     * @param grid the grid that the tetrad is in 
     */
    public Tetrad(MyBoundedGrid<Block> grid)
    {
        
        this.grid = grid; 
        blocks = new Block[4] ; 
        locs = new Location[4] ; 
        int random = (int) (Math.random() * 7) ; 
        shape = random ; 
        for(int i = 0 ; i < 4 ; i++) 
        {
            blocks[i]=new Block() ; 
            if(random == 0 ) 
            {
                blocks[i].setColor(Color.RED) ; 
                if(i==0)
                    locs[i] = new Location (1, 5) ; 
                if(i==1)
                    locs[i] = new Location(0,5) ; 
                if(i==2)
                    locs[i] = new Location(2,5) ; 
                if(i==3)
                    locs[i] = new Location(3,5) ; 
            }
            if(random == 1 ) 
            {
                blocks[i].setColor(Color.GRAY) ; 
                if(i==0)
                    locs[i] = new Location (0, 4) ; 
                if(i==1)
                    locs[i] = new Location(0,5) ; 
                if(i==2)
                    locs[i] = new Location(0,3) ; 
                if(i==3)
                    locs[i] = new Location(1,4) ; 
            }
            if(random == 2 ) 
            {
                blocks[i].setColor(Color.CYAN) ; 
                if(i==0)
                    locs[i] = new Location (0, 4) ; 
                if(i==1)
                    locs[i] = new Location(0,5) ; 
                if(i==2)
                    locs[i] = new Location(1,5) ; 
                if(i==3)
                    locs[i] = new Location(1,4) ; 
            }
            if(random == 3 ) 
            {
                blocks[i].setColor(Color.YELLOW) ; 
                if(i==0)
                    locs[i] = new Location (1, 4) ; 
                if(i==1)
                    locs[i] = new Location(0,4) ; 
                if(i==2)
                    locs[i] = new Location(2,4) ; 
                if(i==3)
                    locs[i] = new Location(2,5) ; 
            }
            if(random == 4 ) 
            {
                blocks[i].setColor(Color.MAGENTA) ; 
                if(i==0)
                    locs[i] = new Location (1, 4) ; 
                if(i==1)
                    locs[i] = new Location(0,4) ; 
                if(i==2)
                    locs[i] = new Location(2,3) ; 
                if(i==3)
                    locs[i] = new Location(2,4) ; 
            }
            if(random == 5 ) 
            {
                blocks[i].setColor(Color.BLUE) ; 
                if(i==0)
                    locs[i] = new Location (0,4) ; 
                if(i==1)
                    locs[i] = new Location(0,5) ; 
                if(i==2)
                    locs[i] = new Location(1,3) ; 
                if(i==3)
                    locs[i] = new Location(1,4) ; 
            }
            if(random == 6 ) 
            {
                blocks[i].setColor(Color.GREEN) ; 
                if(i==0)
                    locs[i] = new Location (0, 4) ; 
                if(i==1)
                    locs[i] = new Location(0,3) ; 
                if(i==2)
                    locs[i] = new Location(1,5) ; 
                if(i==3)
                    locs[i] = new Location(1,4) ; 
            }
        }
        addToLocations(grid, locs) ; 
        lock = new Semaphore(1,true) ; 
    }
    
    /** Constructor for tetrad objects with a specified shape 
     * @param grid the grid to put the tetrad in 
     * @param shape a number that specifies the shape of the object 
     * 
     */
    public Tetrad(MyBoundedGrid<Block> grid, int shape)
    {
        this.shape = shape ; 
        this.grid = grid ; 
        blocks = new Block[4] ;
        locs = new Location[4] ; 
        for(int i = 0 ; i < 4 ; i++) 
        {
            blocks[i]=new Block() ; 
            if(shape == 0 ) 
            {
                blocks[i].setColor(Color.RED) ; 
                if(i==0)
                    locs[i] = new Location (1, 5) ; 
                if(i==1)
                    locs[i] = new Location(0,5) ; 
                if(i==2)
                    locs[i] = new Location(2,5) ; 
                if(i==3)
                    locs[i] = new Location(3,5) ; 
            }
            if(shape == 1 ) 
            {
                blocks[i].setColor(Color.GRAY) ; 
                if(i==0)
                    locs[i] = new Location (0, 4) ; 
                if(i==1)
                    locs[i] = new Location(0,5) ; 
                if(i==2)
                    locs[i] = new Location(0,3) ; 
                if(i==3)
                    locs[i] = new Location(1,4) ; 
            }
            if(shape == 2 ) 
            {
                blocks[i].setColor(Color.CYAN) ; 
                if(i==0)
                    locs[i] = new Location (0, 4) ; 
                if(i==1)
                    locs[i] = new Location(0,5) ; 
                if(i==2)
                    locs[i] = new Location(1,5) ; 
                if(i==3)
                    locs[i] = new Location(1,4) ; 
            }
            if(shape == 3 ) 
            {
                blocks[i].setColor(Color.YELLOW) ; 
                if(i==0)
                    locs[i] = new Location (1, 4) ; 
                if(i==1)
                    locs[i] = new Location(0,4) ; 
                if(i==2)
                    locs[i] = new Location(2,4) ; 
                if(i==3)
                    locs[i] = new Location(2,5) ; 
            }
            if(shape == 4 ) 
            {
                blocks[i].setColor(Color.MAGENTA) ; 
                if(i==0)
                    locs[i] = new Location (1, 4) ; 
                if(i==1)
                    locs[i] = new Location(0,4) ; 
                if(i==2)
                    locs[i] = new Location(2,3) ; 
                if(i==3)
                    locs[i] = new Location(2,4) ; 
            }
            if(shape == 5 ) 
            {
                blocks[i].setColor(Color.BLUE) ; 
                if(i==0)
                    locs[i] = new Location (0,4) ; 
                if(i==1)
                    locs[i] = new Location(0,5) ; 
                if(i==2)
                    locs[i] = new Location(1,3) ; 
                if(i==3)
                    locs[i] = new Location(1,4) ; 
            }
            if(shape == 6 ) 
            {
                blocks[i].setColor(Color.GREEN) ; 
                if(i==0)
                    locs[i] = new Location (0, 4) ; 
                if(i==1)
                    locs[i] = new Location(0,3) ; 
                if(i==2)
                    locs[i] = new Location(1,5) ; 
                if(i==3)
                    locs[i] = new Location(1,4) ; 
            }
        }
        addToLocations(grid, locs) ; 
        lock = new Semaphore(1,true) ; 
    }
    
     /**
     * returns the blocks in the tetrad 
     *
     * @return the  array that contains the blocks
     * 
     */
    public Block[] getBlocks() 
    {
        return blocks;
    }

    /** Determines the shape of the object 
     * @return an integer that indicates the shape of the object 
     * 
     */
    public int getShape()
    {
        return shape; 
    }

    /**
     * Adds a block in the given locations 
     * @precondition: blocks are not in any grid;
     * @precondition: locs.length = 4.
     * @postcondition: The locations of blocks match locs,
     *                  and blocks have been put in the grid.
     *  @param g the grid to add blocks to 
     *  @param l the array of locations 
     */
    private void addToLocations(MyBoundedGrid<Block> g,Location[] l) 
    {
        for(int i = 0 ; i < 4 ; i++) 
        {
            blocks[i].putSelfInGrid(g, l[i]) ; 
        }
        grid = g ;
        locs = l; 
    }

    /** Removes the tetrad from the grid
     * @precondition: Blocks are in the grid.
     * @postcondition: Returns old locations of blocks;
     *              blocks have been removed from grid.
     *  @return an array of locations of the tetrad              
     */
    private Location[] removeBlocks()
    {
        Location[] old = new Location[4];
        for(int i = 0 ; i < 4 ; i++)
        {
            old[i] = locs[i] ; 
            blocks[i].removeSelfFromGrid() ; 
        }
        locs = null; 
        return old; 
    }

    /** Determines if the locations in the grid are empty  
     * @param g the grid to check 
     * @param l the locations to check in the grid 
     * @return  true if each of locs is valid and empty in grid;
     *              false otherwise.
     */ 
    private boolean areEmpty(MyBoundedGrid<Block> g, Location[] l) 
    {
        for(int i = 0 ; i < 4 ; i++)
            if(!g.isValid(l[i]) )
                return false; 
        int num = 0 ; 
        for(int j = 0 ; j < 4 ; j++) 
            if(g.get(l[j]) == null)
                num++ ; 
            else
            {
                for(int k = 0 ; k < 4 ; k++)
                    if(g.get(l[j]) == blocks[k])
                        num++ ; 
            }
        if(num==4)
            return true;
        return false;
    }

    /** Shifts a tetrad by a given amount 
     * @postcondition: Attempts to move this tetrad deltaRow rows down 
     *              and deltaCol columns to the right, if those positions are valid and empty
     * @param deltaRow the number of rows to move the tetrad down by 
     * @param deltaCol the number of columns to move the tetrad right by 
     * @return true if successful, false otherwise.
     * 
     */ 
    public boolean translate(int deltaRow, int deltaCol)
    {
        try
        {
            lock.acquire();
            Location[] temp = new Location[4] ; 
            for(int i = 0 ; i < 4 ; i++)
                temp[i] = new Location(locs[i].getRow() + deltaRow, locs[i].getCol() + deltaCol) ; 
            if(areEmpty(grid,temp))
            {
                removeBlocks() ; 
                addToLocations(grid, temp) ; 
                return true; 
            }
            return false; 
        }
        catch (InterruptedException e)
        {
            // did not modify the tetrad
            return false;
        }
        finally
        {
            lock.release();
        } 

    }

    /** Rotates the tetrad 90 degrees around the center block 
     * @postcondition the tetrad is rotated 90 degrees around its center if it can be  
     * @return true if rotated, false otherwise 
     * 
     */
    public boolean rotate()
    {
        try
        {
            lock.acquire();
            Location[] temp = new Location[4] ; 
            for(int i = 0 ; i < 4 ; i++)
                temp[i] = new Location(locs[0].getRow() - locs[0].getCol() + locs[i].getCol(), 
                    (locs[0].getRow()+ locs[0].getCol() - locs[i].getRow())); 
            if(areEmpty(grid,temp))
            {
                removeBlocks() ; 
                addToLocations(grid, temp) ; 
                return true; 
            }
            return false; 
        }
        catch (InterruptedException e)
        {
            // did not modify the tetrad
            return false;
        }
        finally
        {
            lock.release();
        } 

    }
}
