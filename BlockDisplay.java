import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * Used to display the contents of a game board
 */
public class BlockDisplay extends JComponent implements KeyListener
{
    private static final Color BACKGROUND = Color.BLACK;
    private static final Color BORDER = Color.BLACK;

    private static final int OUTLINE = 2;
    private static final int BLOCKSIZE = 20;

    private MyBoundedGrid<Block> board;
    private JFrame frame;
    private ArrowListener listener;
    private int x ; 
    private int y;

    // Constructs a new display for displaying the given board
    /** constructor for block display class 
     * @param board the grid that will be represented 
     * 
     */
    public BlockDisplay(MyBoundedGrid<Block> board)
    {
        this.board = board;

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    createAndShowGUI();
                }
            });

        //Wait until display has been drawn
        try
        {
            while (frame == null || !frame.isVisible())
                Thread.sleep(1);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /** Constructor for block display class with a starting position for the window 
     * @param grid the grid that will be displayed 
     * @param xpos the x position of the window 
     * @param ypos the y position of the window 
     * 
     */
    public BlockDisplay(MyBoundedGrid<Block> grid, int xpos, int ypos)
    {
        board = grid ; 
        x = xpos; 
        y = ypos; 
        SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    createAndShowGUI();
                }
            });

        //Wait until display has been drawn
        try
        {
            while (frame == null || !frame.isVisible())
                Thread.sleep(1);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private void createAndShowGUI()
    {
        //Create and set up the window.
        frame = new JFrame();
        frame.setLocation(x,y) ;  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.addKeyListener(this);

        //Display the window.
        this.setPreferredSize(new Dimension(
                BLOCKSIZE * board.getNumCols(),
                BLOCKSIZE * board.getNumRows()
            ));

        frame.pack();
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g)
    {
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(BORDER);
        g.fillRect(0, 0, BLOCKSIZE * board.getNumCols() + OUTLINE, BLOCKSIZE * board.getNumRows());
        for (int row = 0; row < board.getNumRows(); row++)
            for (int col = 0; col < board.getNumCols(); col++)
            {
                Location loc = new Location(row, col);

                Block square = board.get(loc);

                if (square == null)
                    g.setColor(BACKGROUND);
                else
                    g.setColor(square.getColor());

                g.fillRect(col * BLOCKSIZE + OUTLINE/2, row * BLOCKSIZE + OUTLINE/2,
                    BLOCKSIZE - OUTLINE, BLOCKSIZE - OUTLINE);
            }

    }

    //Redraws the board to include the pieces and border colors.
    public void showBlocks()
    {
        repaint();
    }

    // Sets the title of the window.
    public void setTitle(String title)
    {
        frame.setTitle(title);
    }

    public void keyTyped(KeyEvent e)
    {
    }

    public void keyReleased(KeyEvent e)
    {
    }

    public void keyPressed(KeyEvent e)
    {
        if (listener == null)
            return;
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT)
            listener.leftPressed();
        else if (code == KeyEvent.VK_RIGHT)
            listener.rightPressed();
        else if (code == KeyEvent.VK_DOWN)
            listener.downPressed();
        else if (code == KeyEvent.VK_UP)
            listener.upPressed();
        else if (code == KeyEvent.VK_SPACE)
            listener.spacePressed() ; 
    }

    public void setArrowListener(ArrowListener listener)
    {
        this.listener = listener;
    }
}
