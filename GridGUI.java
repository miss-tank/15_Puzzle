/* Ankita Tank
 * CS342 - Project 2 15 Puzzle
 * This Class Implements the 4x4 grid 
 * it implements the functionality of the undo, reset, and call back to stack
 * Used Some parts of this GUI from online resources mentioned here
 * https://github.com/kshah21/8-Tiles-Solver
 * https://rosettacode.org/wiki/15_Puzzle_Game#Java
 */


package aa;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import static java.lang.System.exit;

public class GridGUI extends JPanel
{
    
    //Initialize the essential variables for Jframe and JPanel
    private Board gameBoard;
    private JPanel gridPanel; 
    private JPanel optionPanel;
    
    private int[] inputArr;
    private int numInput;
    private int solIterator; 
    private int valstackcapacity=0;
    private int a;
    private int b;
    private int vv;
    int x=0;
    int y=0;
    int input=0;
    int popval=0;

    
    //Create Mystack for each value of the grid -> x-cord, y-cord, moved value;
    public MyStack bstack=new MyStack(a);
    public MyStack x_Stack = new MyStack(a);
    public MyStack y_Stack = new MyStack(a);
    public MyStack val_Stack = new MyStack(vv);

    
    
    //Set the empty Grid
    public GridGUI()
    {
        super();
        this.setLayout(new BorderLayout());        
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(4,4));
        this.add(gridPanel,BorderLayout.CENTER);
        gameBoard = null;       
        initRandBoard();
    }

    //Set getters for the Game and Stack
    public Board getGameBoard() 
    {
        return gameBoard;
    }
    
    public MyStack getyStack()
    {
        return y_Stack;
    }
    
    public MyStack getXStack()
    {
        return x_Stack;
    }

    public MyStack getValStack()
    {
        return val_Stack;
    }
    
    
    //Initialize the board with empty 16 tiles
    private void initRandBoard()
    {
        ImageIcon img1 = new ImageIcon("src/1.png");

        gameBoard = new Board();
        JButton tile;

        int[][] tempGrid = gameBoard.getGrid();
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                int tileNum = tempGrid[i][j];
                if(tileNum!=0)
                {
                 tile = new JButton(Integer.toString(tileNum));
             }
             else
             {
                tile = new JButton(" ");
            }

            tile.addActionListener(new MoveHandler());
            gridPanel.add(tile);
        }
    }
    addGameOptions();
}


    //UpdatePanel : 4x4 grid with each move provided by the user
private void updatePanel(boolean user)
{
    gridPanel.removeAll();
    this.removeAll();

    int[][] tempGrid = gameBoard.getGrid();
    JButton tile;

    //Update the board according to the new coordinates i,j or x,y
    for(int i=0;i<4;i++)
    {
        for(int j=0;j<4;j++)
        {
            int tileNum = tempGrid[i][j];
            if(tileNum!=0)
            {
                    tile = new JButton(Integer.toString(tileNum)); 
                }
                else
                {
                    tile = new JButton(" ");
                }
                tile.addActionListener(new MoveHandler()); 
                gridPanel.add(tile);
            }
        }

        gridPanel.revalidate();
        this.add(gridPanel,BorderLayout.CENTER);
        addGameOptions();
        this.revalidate();
    }

	//Adding buttons for the user to selected from in the grid panel
    private void addGameOptions()
    {
    		//add a menubar for About , help 
        JMenuBar mb=new JMenuBar();   
        optionPanel =new JPanel();
        
        optionPanel.setLayout(new GridLayout(2,2));
 
        //3 buttons for the User for interaction with the grid
        JButton solveGame = new JButton("Auto Solve");
        JButton undoOne = new JButton("Undo Move");
        JButton resetboard = new JButton("Reset Board");
 
        String score = Integer.toString(gameBoard.gethVal());
        JLabel hValDisplay = new JLabel("Inversion Value : " + score);

        
        //Adding handlers for each buttons
        solveGame.addActionListener(new SolutionHandler());
        undoOne.addActionListener(new Undomove());
        resetboard.addActionListener(new ResetHandler());
             
        this.add(solveGame,BorderLayout.NORTH);
        optionPanel.add(undoOne);
        optionPanel.add(resetboard);
        optionPanel.add(hValDisplay);
        this.add(optionPanel,BorderLayout.SOUTH);
        this.revalidate();
    }


    
    private void userInputUpdate(String chosen)
    {
        for(int i=0;i<16;i++)
        {
            if(chosen.contains(Integer.toString(i)))
            {
                inputArr[i] = numInput;
                numInput++;
            }
        }
    }

    //Handler for the reset button
    private class ResetHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {           
        		//Take the values from all the 3 stacks
           x_Stack=gameBoard.getxxStack();
           y_Stack=gameBoard.getyyStack();
           val_Stack=gameBoard.getvallStack();
           int count=0;
          
           //get the length of the value stack to use that as a counter for the while loop
           valstackcapacity= val_Stack.valStacklength();
              
           //run a while loop until the stack is empty i.e there are no more EX-moves left
            Timer timer  = new Timer(500,null);

            ActionListener listener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(valstackcapacity >0){
		                x= x_Stack.xpop();
		                y= y_Stack.ypop();
		                input = val_Stack.valpop();
		                System.out.println("this is x -> "+ x);
		                System.out.println("this is y -> "+ y);
		                System.out.println("this is input -> "+ input);
		                gameBoard.undomove(input, x, y);
		                updatePanel(true);
		                valstackcapacity--;
		            }
					else {
						timer.stop();
					}		
				}
            };
            timer.addActionListener(listener);
            timer.start();
        } 
    }       

    
    
//TO-DO
   // This uses BFS to auto solve the puzzle
private class SolutionHandler implements ActionListener
{
    public void actionPerformed(ActionEvent event)
    {
       System.out.println(" I Havent Implemented this ATALL  ");
   }       
}


//Undo one more at a time
private class Undomove implements ActionListener
{
    public void actionPerformed(ActionEvent event)
    {       
    		//take all the values from the stack
       x_Stack=gameBoard.getxxStack();
       y_Stack=gameBoard.getyyStack();
       val_Stack=gameBoard.getvallStack();
       
       //Pop the top value form each stack to return to the EX-coordinate
       x= x_Stack.xpop();
       y= y_Stack.ypop();
    
       System.out.println("this is input -> "+ input);
       System.out.println("this is x -> "+ x);
       System.out.println("this is y -> "+ y);
       System.out.println("this is input -> "+ x_Stack.xStacklength() );
       
       if(x_Stack.xStacklength()==0)
       {
    	   		JOptionPane.showMessageDialog(GridGUI.this, event.getActionCommand() + " No more moves to undo");
       }
       else 
       {
    	   input = val_Stack.valpop();
    	   
       //updtate the game with the new coordinate
       gameBoard.undomove(input, x, y);
       updatePanel(true);  
       }
   }       
}

//handles the moves of the grid when a valid tile is clicked
private class MoveHandler implements ActionListener
{
    public void actionPerformed(ActionEvent event)
    {
    		// if a blank tile is clicked -> it is not a valid move
        if(event.getActionCommand().equals(" "))
        {
            JOptionPane.showMessageDialog(GridGUI.this, event.getActionCommand() + " Is Not A Valid Move");
        }
        else //selecting a numbered tile is a valid move
        {
            int toMove = Integer.parseInt(event.getActionCommand());
            
            if(gameBoard.checkMove(toMove))
            {
                gameBoard.move(toMove);
                System.out.println();
                gameBoard.printBoard();
                gameBoard.printInversion();

                GridGUI.this.updatePanel(false);

                if(gameBoard.gethVal()==0)
                {
                    JOptionPane.showMessageDialog(GridGUI.this, "Congratulations!! You Have Sucessfully Solved The Puzzle");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(GridGUI.this, event.getActionCommand() + " Is an Invalid Move");
            }
        }
    }
}

} 
