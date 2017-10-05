/* Ankita Tank

 * CS342 - Project 2 15 Puzzle
 * This Class Implements the 4x4 grid GUI
 * it implements the functionality of the setting up the buttons, Menu and add Action and Menu Listeners for the GUI
 */


package aa;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.*;
import java.awt.event.*;
import static java.lang.System.exit;

public class BoardGUI
{
	//initial Board 
    private JPanel gamePanel; 
    private GridGUI boardPanel;
    JMenu About;
    JMenu Help;
    JMenu Exit;
    JMenu Mix;


    public BoardGUI()
    {

    		//Start Screen when the app is set to run
    		gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.setSize(400,400);
        boardPanel = null;


        displayStartScreen();

        JFrame gameFrame = new JFrame();
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameFrame.setLayout(new BorderLayout());
        gameFrame.setSize(400,400);
        gameFrame.add(gamePanel);
        
        //Add a menu bar
        JMenuBar mb=new JMenuBar();  
        
        //Menu Items
        About=new JMenu("About");  
        Help= new JMenu("Help");
        Exit = new JMenu("Quit");
        Mix = new JMenu("Mix");
        
        
        mb.add(About);  
        mb.add(Help);  
        mb.add(Exit);
        mb.add(Mix);
        
        gameFrame.setJMenuBar(mb);  
        gameFrame.setVisible(true);   
        
        //MenuListeners for the menu items
        About.addMenuListener(new aboutDialogueBox());
        Help.addMenuListener(new HelpDialogueBox());
        Exit.addMenuListener(new Exit());
        Mix.addMenuListener(new MixGrid());
    }
    //Quit in the Menu BAr Listener
    private class Exit implements MenuListener 
    {
        public void menuSelected(MenuEvent e) 
        {
        		JOptionPane.showMessageDialog(gamePanel,"Thanks for playing!!");
            exit(0);
        }
        
        public void menuDeselected(MenuEvent e) 
        {
            System.out.println("menuDeselected");
        }

        public void menuCanceled(MenuEvent e) 
        {
            System.out.println("menuCanceled");
        }
    } 
    
    
    //Quit in the Menu BAr Listener
    private class MixGrid implements MenuListener 
    {
        public void menuSelected(MenuEvent e) 
        {
        	setUpGame();
        }
        
        public void menuDeselected(MenuEvent e) 
        {
            System.out.println("menuDeselected");
        }

        public void menuCanceled(MenuEvent e) 
        {
            System.out.println("menuCanceled");
        }
    } 
    
    
    //MenuListener for the About Menu item
    private class aboutDialogueBox implements MenuListener 
    {

        public void menuSelected(MenuEvent e) 
        {
        	
        		String s="Author: Ankita Tank \n Date: 10/4/1017 \n CS 342 Programming assignment 2 \n No Extra Credit Attempted."; 
        	
            JOptionPane.showMessageDialog(null, s);
        }
        
        public void menuDeselected(MenuEvent e) 
        {
            System.out.println("menuDeselected");
        }

        public void menuCanceled(MenuEvent e) 
        {
            System.out.println("menuCanceled");

        }

    } 
    
    //MenuListener for the Help Menu item
    private class HelpDialogueBox implements MenuListener 
    {
        public void menuSelected(MenuEvent e) 
        {
            JOptionPane.showMessageDialog(null, "You selectd teh Help  menu");
        }
        
        public void menuDeselected(MenuEvent e) 
        {
            System.out.println("menuDeselected");
        }

        public void menuCanceled(MenuEvent e) 
        {
            System.out.println("menuCanceled");
        }
    } 
   
    //Begin the Screen
    private void displayStartScreen()
    {
        StartScreen begin = new StartScreen();
        gamePanel.add(begin, BorderLayout.CENTER);
    }


    //make a clean board with nothing initialzed : everything to " "
    private void setUpGame()
    {
        gamePanel.removeAll();
        boardPanel = new GridGUI();

        JButton exitGame = new JButton("EXIT");
        exitGame.addActionListener(new ExitHandler());

        gamePanel.add(boardPanel,BorderLayout.CENTER);
        gamePanel.add(exitGame,BorderLayout.SOUTH);

        SwingUtilities.updateComponentTreeUI(gamePanel);
    }


    private class StartScreen extends JPanel
    {
        public StartScreen()
        {

            super();
            this.setLayout(new GridLayout(4,1));

            //Add buttons on the start screen
            JButton startGame = new JButton("START");
            JButton exitGame = new JButton("EXIT");
            
            //ActionListeners for the Buttons
            startGame.addActionListener(new StartHandler());
            exitGame.addActionListener(new ExitHandler());

            //Add the buttons to the panel
            this.add(startGame);
            this.add(exitGame);
            this.setVisible(true);

        }
    }

   //Start button lead to setting up the game
private class StartHandler implements ActionListener
{
    public void actionPerformed (ActionEvent event)
    {
        setUpGame();           
    }
}

//Close the Frame : same as frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

private class ExitHandler implements ActionListener
{
    public void actionPerformed(ActionEvent event)
    {
        JOptionPane.showMessageDialog(gamePanel,"Thank You For Playing :) \n Hope to see you again :) !!");
        exit(0);
    }
}
}