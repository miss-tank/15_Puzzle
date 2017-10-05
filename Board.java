/* Ankita Tank

 * CS342 - Project 2 15 Puzzle
 * This Class Implements the 4x4 grid as a 2d grid
 * it implements the functionality of the undo, reset, and call back to stack
 */


package aa;
import java.util.*;

public class Board
{
    private int[][] grid;
    private int hVal;
    private int a;
    private int b;
    private int vv;
    private int[] movable; 
    private final int SIZE = 4; 
    private int[] arrayinitial=new int[16];
    
    //Stacks to keep a track of x,y,val of the tile
    MyStack xxstack = new MyStack(a);
    MyStack yystack = new MyStack(a);
    MyStack vallstack = new MyStack(a);



    public Board()
    {
        grid = loadBoard(); 
        hVal = inversion(); 
        movable = availableMoves();
        xxstack=getxxStack();
        yystack=getyyStack();
        vallstack=getvallStack();
    }

    //get the grid
    public int[][] getGrid()
    {
        return grid;
    }

    //get the xstack
    public  MyStack getxxStack()
    {
        return xxstack;
    }
    
    //get the ystack
    public  MyStack getyyStack()
    {
        return yystack;
    }

    //get the value stack
    public  MyStack getvallStack()
    {
        return vallstack;
    }

    //set the grid according to the 2d array
    public void setGrid(int[][] grid)
    {
        this.grid = grid;
    }
    
    //get the inversion value
    public int gethVal()
    {
        return hVal;
    }
    
    //get the array of the possible moves from the adjacent tiles
    public int[] getMovable()
    {
        return movable;
    }
    
    //get the size of the board
    public int getSIZE()
    {
        return SIZE;
    }

    //from 1d array get the values 
    private void to1darray(int[] array,int val,int index)
    {
        array[index]=val;
    }

    // load the board and return the grid
    private int[][] loadBoard()
    {
        //System.out.println("in load board");
        int[][] retGrid = new int[SIZE][SIZE];
        int[] nums = new int[SIZE*SIZE];
        boolean flag = true;
        int index;
        int count=0;

        Random generator = new Random(System.currentTimeMillis());

        //Create number set that we'll be randomly loading into grid
        for(int i=0;i<(SIZE*SIZE);i++)
        {
            nums[i] = i;

        }

        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                while(flag)
                {
                    index = (generator.nextInt(16)); 
                    if(nums[index] !=-1) //unused number
                    {
                        retGrid[i][j] =  nums[index];

                        to1darray(arrayinitial,retGrid[i][j],count);
                        count++;
                        nums[index] =-1; //Number is now used
                        flag = false;
                    }
                }
                flag = true;
            }
        }

        return retGrid;
    }



    public int inversion()
    {
    	//calculate the inversion value of the board

        int inversioncount=0;
        int reset =0;
        int len = arrayinitial.length;
        for(int i=0;i<len;i++)
        {
            for(int j=i+1;j<len;j++)
            {
                if(arrayinitial[i]>arrayinitial[j])
                {
                    if(arrayinitial[j]!=0)
                    {
                        inversioncount++;
                        reset++;
                    }
                }

            }

        }
        return inversioncount;
    }



    public int[] availableMoves()
    {
    	
    	//check the adjacent values around the blank tile
        int up =- 1; int down = -1;
        int left = -1; int right= -1;

        int[] openSpace = findSpace();
        int x = openSpace[0];
        int y = openSpace[1];

        //System.out.println("in avalable moves");

        System.out.println("x is " + x);
        System.out.println("y is " + y);
        xxstack.pushx(x);
        yystack.pushy(y);



        // check the values and store in the temporary variable
        if(x>0)
        {
            up = grid[x-1][y];
        }
        if(x<(SIZE-1))
        {
            down = grid[x+1][y];
        }
        if(y<(SIZE-1))
        {
            left = grid[x][y+1];
        }
        if(y>0)
        {
            right = grid[x][y-1];
        }

        int[] moves = {up,down,left,right};

        return moves;
    }


    private int[] findSpace()
    {
      //  System.out.println("in finddd space");

        int[] coordinate = new int[4];

        for(int i=0;i<SIZE;i++)
        {
            for(int j=0;j<SIZE;j++)
            {
                if(grid[i][j]==0)
                {
                    coordinate[0]=i;
                    coordinate[1]=j;
                    return coordinate;
                }
            }
        }



        return coordinate;
    }

    public boolean checkMove(int input)
    {
        //System.out.println("in check moves");
       // System.out.println("input is " + input);

        for(int i = 0; i< movable.length; i++)
        {
            if(movable[i] == input)
            {
                return true;
            }
        }

        return false;
    }


    public void move(int input)
    {

        System.out.println("input is " + input);
        vallstack.push(input);

        int x0=0;
        int y0=0;
        int xInput=0;
        int yInput=0;

        //Find the coordinates of 0 and provided input
        for(int i=0;i<SIZE;i++)
        {
            for(int j=0;j<SIZE;j++)
            {
                if(grid[i][j]==0)
                {
                    x0=i;
                    y0=j;
                }
                if(grid[i][j]==input)
                {
                    xInput=i;
                    yInput=j;
                }
            }
        }

        //Swap 0 with the provided input
        grid[x0][y0] = grid[xInput][yInput];
        grid[xInput][yInput]=0;

        movable =availableMoves();
        hVal = inversion();
    }


    //Go back to the last move 
    public void undomove(int input, int x, int y)
    {

        System.out.println("trying to undo move" );

        int x0=x;
        int y0=y;
        int xInput=0;
        int yInput=0;

        // Find the empty space and replace it with x,y, coordinate
        for(int i=0;i<SIZE;i++)
        {
            for(int j=0;j<SIZE;j++)
            {
                if(grid[i][j]==0)
                {
                    x0=i;
                    y0=j;
                }
                if(grid[i][j]==input)
                {
                    xInput=i;
                    yInput=j;
                }
            }
        }

        //Swap 0 with the provided input
        grid[x0][y0] = grid[xInput][yInput];
        grid[xInput][yInput]=0;

        printBoard();

    }

    //Print the Grid
    public void printBoard()
    {
       // System.out.println("in print board");
        int count =0;
        int x0=-1;
        int y0=-1;

        for(int i=0;i<SIZE;i++)
        {
            for(int j=0;j<SIZE;j++)
            {
                if(grid[i][j]==0)
                {
                    x0=i;
                    y0=j;
                    System.out.print(" " + " ");
                }
                else
                {
                    System.out.print(grid[i][j]+ " ");
                }
            }
            System.out.println("");
        }

    }

    public void printInversion()
    {
        System.out.println("Inversion Value: " + hVal);
    }


} //End class
