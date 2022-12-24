/**
 * Marbles.java
 * This program creates a game called marbles where the user selects a ball and can
 * hop over one other ball to remove the ball from the board. The board is either
 * 7x7 or 9x9. If there is only one ball remaining at the end the user wins.
 * Else, they lose.
 * @version 1.0
 * @since 1/10/2020
 */
import java.awt.Color;
import java.awt.Font;

public class Marbles
{
    /**    The board object.  1 represents a marble on the board, 0 is an empty space,
     *     and -1 would indicate that this cell is not part of the board.                  */
    private int [][] board;
    
    /**    How long the GUI should pause, before expecting new input.                      */
    private int pauseTime;
    
    /**    Current x and y values of the user's choice.  The x values count the cells
     *     from the lower left to the right, while the y values count the cells from
     *     the bottom left up.                                                             */
    private int xposition, yposition;
    
    /**
     *  Creates a Marbles object, with the font to be used, current position initially
     *  pause off the board, pause at 50 milliseconds, and the board values initialized
     *  in a 9 x 9 grid.
     */
    public Marbles ( )
    {
        Font font = new Font("Arial", Font.BOLD, 18);
        StdDraw.setFont(font);
        xposition = yposition = -5;
        pauseTime = 50;
        board = new int[][]{{-1,-1,-1,1,1,1,-1,-1,-1},{-1,-1,-1,1,1,1,-1,-1,-1},{-1,-1,-1,1,1,1,-1,-1,-1},
            {1,1,1,1,1,1,1,1,1},{1,1,1,1,0,1,1,1,1},{1,1,1,1,1,1,1,1,1},
            {-1,-1,-1,1,1,1,-1,-1,-1},{-1,-1,-1,1,1,1,-1,-1,-1},{-1,-1,-1,1,1,1,-1,-1,-1}};
        StdDraw.enableDoubleBuffering();
    }
    
    /**
     *  Sets up and runs the game of Marbles.
     *  @param  args     An array of String arguments (not used here).
     */
    public static void main(String [] args)
    {
        Marbles run = new Marbles();
        run.playGame();
    }
    
    /**
     *  This method plays the game and keeps running until the user has either
     * 	won or lost. This method keeps track of when the mouse is pressed
     * 	and based on that it calls the other methods. If a move is possible,
     * 	and the user selects the move, this method will make the ball "jump"
     */
    public void playGame ( )
    {
        boolean done = false;
        do
        {
            drawBoard();
            if(StdDraw.isMousePressed())
            {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                int checkx = (int)(10 * x - 0.5);
                int checky = (int)(10 * y - 0.5);
                if(reset(x,y))
                {
                    xposition = yposition = -5;
                }
                else if(possibleMoveSpace(xposition,yposition,checkx,checky))
                {
                    if(yposition == checky)
                    {
                        board[(xposition+checkx)/2][checky]=0;
                        board[checkx][checky]=1;
                        board[xposition][yposition]=0;
                    }
                    else if(xposition == checkx)
                    {
                        board[checkx][(yposition+checky)/2]=0;
                        board[checkx][checky]=1;
                        board[xposition][yposition]=0;                    
                    }
                  
                    
                    StdDraw.show();
                    StdDraw.pause(4 * pauseTime);
                }
                else
                {
                    xposition = checkx;
                    yposition = checky;
                    StdDraw.show();
                    StdDraw.pause(pauseTime);
                }
            }
            StdDraw.show();
            StdDraw.pause(pauseTime);
        }
        while(!done);
    }
    
    /**
     *  This method draws the board for the game
     *  This method also calls the methods to reset the buttons and to draw the win or lose message.
     * 	This is to either end or reset the game.
     */
    public void drawBoard ( )
    {
        StdDraw.setPenColor(new Color(0,0,0));
        StdDraw.filledSquare(0.5,0.5,0.5);
        StdDraw.setPenColor(new Color(5,25,160));
        StdDraw.filledSquare(0.5,0.5,0.475);
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[i].length; j++ )
            {
                if(board[i][j] != -1)
                {
                    drawCell(i,j);
                }
            }
        }
        drawResetButtons();
        drawWinOrLoseMessage();
        
    }
    
    /**
     *  This method draws the rectangles for the reset buttons
     */
    public void drawResetButtons ( )
    {
        StdDraw.setPenColor(new Color(0,0,0));
        StdDraw.filledRectangle(0.8, 0.25, 0.125, 0.05);
        StdDraw.setPenColor(new Color(255,255,255));
        StdDraw.text(0.8, 0.25, "RESET 7 x 7");
        
        StdDraw.setPenColor(new Color(0,0,0));
        StdDraw.filledRectangle(0.8, 0.1, 0.125, 0.05);
        StdDraw.setPenColor(new Color(255,255,255));
        StdDraw.text(0.8, 0.1, "RESET 9 x 9");
    }
    
    /**
     *  This method displays the win or lose message
     * 	If there if one marble left, than the user wins
     * 	If the game is complete and the user has no more possible moves,
     * 	than the user loses
     */
    public void drawWinOrLoseMessage ( )
    {
        if(countMarbles()==1)
        {
            StdDraw.setPenColor(new Color(0,0,0));
            StdDraw.filledRectangle(0.2, 0.8, 0.125, 0.05);
            StdDraw.setPenColor(new Color(255,255,255));
            StdDraw.text(0.2, 0.8, "YOU WIN");
        }
        else if(gameIsFinished())
        {
            StdDraw.setPenColor(new Color(0,0,0));
            StdDraw.filledRectangle(0.2, 0.8, 0.125, 0.05);
            StdDraw.setPenColor(new Color(255,255,255));
            StdDraw.text(0.2, 0.8, "YOU LOSE");
        }
    }
    
    /**
     *  Resets the board depending on which square the user clicks
     * 	Depending on which square the user clicks a different sized board appears
     * 	Either a 9x9 or 7x7 board
     *  @param: double x: x-coordinate for where the user clicked
     *  double y: y-coordinate for where the user clicked
     *  @return: boolean for if they clicked in that area and if the board resets
     */
    public boolean reset(double x, double y)
    {

        if (x>=0.675 && x<=0.9125 && y<=0.15 && y>=0.05)
        {
            board = new int[][]{{-1,-1,-1,1,1,1,-1,-1,-1},{-1,-1,-1,1,1,1,-1,-1,-1},{-1,-1,-1,1,1,1,-1,-1,-1},
                {1,1,1,1,1,1,1,1,1},{1,1,1,1,0,1,1,1,1},{1,1,1,1,1,1,1,1,1},
                {-1,-1,-1,1,1,1,-1,-1,-1},{-1,-1,-1,1,1,1,-1,-1,-1},{-1,-1,-1,1,1,1,-1,-1,-1}};
            return true;
        }

        if(x>=0.675 && y<=0.30 && x<=0.9125 && y>=0.20)
        {
            board = new int[][]{{-1,-1,-1,-1,-1,-1,-1,-1,-1},{-1,-1,-1,1,1,1,-1,-1,-1},{-1,-1,-1,1,1,1,-1,-1,-1},
                {-1,1,1,1,1,1,1,1,-1},{-1,1,1,1,0,1,1,1,-1},{-1,1,1,1,1,1,1,1,-1},
                {-1,-1,-1,1,1,1,-1,-1,-1},{-1,-1,-1,1,1,1,-1,-1,-1},{-1,-1,-1,-1,-1,-1,-1,-1,-1}};
            return true;
        }
        
        return false;
    }
    
    /**
     *  Draws the cells and based on the values in the board array, this method draws
     * 	what needs to be there in the cells.
     * 	This method also calls the possibleMoveSpace method to check for possible moves
     */
     
     
     
    public void drawCell(int x, int y)
    {
        StdDraw.setPenColor(new Color(0,0,0));
        StdDraw.filledSquare(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.055);
        StdDraw.setPenColor(new Color(255,255,255));
        StdDraw.filledSquare(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.0425);
        StdDraw.setPenColor(new Color(200,200,200));
        StdDraw.filledCircle(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.02);
        if(x == xposition && y == yposition && board[x][y] == 1)
        {
            StdDraw.setPenColor(new Color(0,0,0));
            StdDraw.filledSquare(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.05);
            StdDraw.setPenColor(new Color(230,30,30));
            StdDraw.filledCircle(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.04);
        }
        if(possibleMoveSpace(xposition,yposition,x,y))
        {
            StdDraw.setPenColor(new Color(0,0,0));
            StdDraw.filledSquare(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.05);
            StdDraw.setPenColor(new Color(230,30,30));
            StdDraw.filledCircle(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.03);
        }
        if(board[x][y] == 1)
        {
            StdDraw.picture(0.1 + 0.1 * x, 0.1 + 0.1 * y,"marble.png");
        }
    }
    
    /**
     *  This method checks if the marble that the user clicks on has a possible space to jump to
     * 	It checks if the selected area has a marble and checks if the intended location is empty
     * 	It also checks if there was a marble in the middle.
     *  @param: int x: x-coordinate for the current location of the marble
     *  int y: y-coordinate for the current location of the marble
     *  int xval: x-coordinate for the location the user wants to place the marble
     *  int yval: y-coordinate for the location the user wants to place the marble
     *  @return: boolean for if the place the user wants the marble to go to is possible
     */
    public boolean possibleMoveSpace(int x, int y, int xval, int yval)
    {
        if( x>=0 && y>=0 && xval>=0 && yval>=0 && x<board.length && y<board.length && xval<board.length && yval<board.length)
        {
            if(board[x][y] == 1 && board[xval][yval] == 0)
            {
                if(Math.abs(xval-x)==2 && yval == y)
                {
                    if(board[(x+xval)/2][y]==1)
                    { 
						return true; 
					}
                    
                }
                else if(Math.abs(yval-y)==2 && xval == x)
                {
                    if(board[x][(y+yval)/2]==1) 
                    {
						return true;
					}
                }
                
            }
        }
        return false;
    }
    
    /**
     *  This methods checks whether the game has ended by checking if there
     * 	are any further possible moves
     */
    public boolean gameIsFinished()
    {
        for(int row =0; row<board.length;row++)
        {
            for(int col =0; col<board.length;col++)
            {
                if (possibleMoveSpace(row,col,row+2,col) || possibleMoveSpace(row,col,row-2,col) || possibleMoveSpace(row,col,row,col+2) || possibleMoveSpace(row,col,row,col-2))
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     *  This method counts the number of marbles on the board.
     */
    public int countMarbles ( )
    {
        int count = 0;
        
        for(int row = 0; row < board.length;row++)
        {
            for(int col =0; col < board[row].length; col++)
            {
                if(board[row][col] == 1) 
                {
					count++;
				}
            }
        }
        return count;
    }
}

