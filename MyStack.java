
/*Ankita Tank
 * CS342 - Project 2 15 Puzzle
 * The MyStack Class keeps a track of the indexes of the empty slots and the Value that is moved 
 * This helps when the User wants to Undo a move or Reset the Puzzle to the original position.
 */

package aa;
public class MyStack 
{
	public int[] stackArray = new int[10000];
	
	/* Creating a Stack_X to keep a track of the x coordinates of the blocks that are moved.
	 * This also has the counter xcount and xtop to check the index and the length of the stack array  */
	public int[] xarray=new int[100000];
	public int xtop=0;
	public int xcount=0;
	
	
	/* Creating a Stack_Y to keep a track of the y coordinates of the blocks that are moved.
	 * This also has the counter ycount and ytop to check the index and the length of the stack array  */
	public int[] yarray=new int[100000];
	public int ytop=0;
	public int ycount=0;

	
	/* Creating a Stack_Val to keep a track of the value of the blocks that is moved.
	 * This also has the counter valcount and valtop to check the index and the length of the stack array  */
	public int[] valarray=new int[100000];
	public int valtop=0;
	public int valcount=0;


	//Temporary variables
	public int top=0;
	public int xcord=0;
	public int ycord=0;
	public int value=0;
	int count=0;
	
	// MyStack that sets teh value of the index that is moved
	public MyStack(int val) 
	{
		value=val;
	}
	
	//push the x-coordinate of the block
	public void pushx(int x) 
	{
		xarray[xtop++]=x;
		xcount++;       
	}
	
	//push the y-coordinate of the block
	public void pushy(int y) 
	{
		yarray[ytop++] = y;
		ycount++;
	}
	
	//push the value of the block which is moved
	public void push(int val)
	{
		System.out.println("***********");
		valarray[valtop++] = val;
		valcount++;
	}

	//pop the top of the stack for the x-coordinate
	public int xpop() 
	{
		System.out.println("tryng to pop top " + xarray[xtop--]);
		xcount--;
		return xarray[xcount];
	}

	//pop the top of the stack for the y-coordinate
	public int ypop() 
	{
		System.out.println("tryng to pop y2 " + yarray[ytop--]);
		ycount--;
		return yarray[ycount];	
	}

	//pop the top of the stack for the value
	public int valpop() 
	{
		System.out.println("tryng to pop  val" + valarray[valtop--]);
		valcount--;
		return valarray[valcount];	
	}

	//returns the length of the value stack : only the utilized counters of the stack
	public int valStacklength()
	{
		printvalstack();
		return valcount;
	}

	//returns the length of the x stack : only the utilized counters of the stack
		public int xStacklength()
		{
			printvalstack();
			return xcount;
		}

	
	
	//Boolean returning if the Stack is empty or not
	public boolean isEmpty() 
	{
		return (top == -1);
	}

	
	//Print the values in the X-coordinate stack
	public void printxstack()
	{
		System.out.println("this is my xstack ");

		for(int i=0;i<xcount;i++)
		{
			System.out.println(xarray[i] + " "); 
		}
		
	}

	
	//Print the values in the Y-coordinate stack
	public void printystack()
	{
		System.out.println("this is my ystack ");

		for(int i=0;i<ycount;i++)
		{
			System.out.println(yarray[i] + " "); 
		}
		
	}

	
	//Print the values in the Value-coordinate stack
	public void printvalstack()
	{
		System.out.println("this is my valstack ");
		for(int i=0;i<valcount;i++)
		{
			System.out.println(valarray[i] + " "); 
		}	
	}
}