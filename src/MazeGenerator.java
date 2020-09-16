/**
 * Name: Viraj Goyal
 * Mrs. Kankelborg
 * Period: 1
 * Project 3 Maze Solver Part 1: Maze Generator
 * Revision History:
 * 12/23/2019 - Initialized a stack starting at (0,0) and a maze
 * 12/24/2019 - Created main while loop that removed walls of the maze such that
 * there existed a path between any two cells through a sequence of reachable cells
 * 12/25/2019 - Created randomNeighbor method to choose a random 
 * unvisited neighboring cell
 * 12/26/2019 - Randomly chose start and end cells and returned the maze
 * 12/27/2019 - Fixed a bug that didn't choose a random unvisited neighboring cell
 * correctly
 * 12/28/2019 - Commented all code
 * Class Description: Creates new mazes. 
 * Please refer to the spec for instructions on how to generate mazes.
 */
import java.util.*;
import java.util.Random;
public class MazeGenerator
{
    /**
     * Randomly generates a perfect maze of {@param size}.
     *
     * @param size the size of the maze to generate
     * @return the generated maze
     */
    public Maze generate(int size)
    {
    	//Stack initialized with a cell at (0,0)
    	Maze firstMaze = new Maze(size);
    	Cell currentCell = new Cell(0,0);
    	Stack<Cell> cellStack = new Stack<Cell>();
    	cellStack.push(currentCell);
    	//Loop runs until stack is empty
    	while(!cellStack.isEmpty())
    	{
    		int currentX = currentCell.getX();
    		int currentY = currentCell.getY();
    		//Marking the current cell in the stack as visited
    		firstMaze.visit(currentX, currentY);
    		//Randomly picking an unvisited neighbor cell
    		Cell unvisitedNeighbor = randomNeighbor(firstMaze, currentX, currentY);
    		if(unvisitedNeighbor != null)
    		{
    			/*Removing the wall between the current cell and the random 
    			 *unvisited neighbor cell based on where the neighbor cell
    			 *is in relation to the current cell.
    			 */
	    		if((currentX - 1) == unvisitedNeighbor.getX())
	        	{
	        		firstMaze.removeWall(currentX, currentY, Direction.LEFT);
	        	}
	    		else if((currentY - 1) == unvisitedNeighbor.getY())
	    		{
	    			firstMaze.removeWall(currentX, currentY, Direction.DOWN);
	    		}
	    		else if((currentX + 1) == unvisitedNeighbor.getX())
	    		{
	    			firstMaze.removeWall(currentX, currentY, Direction.RIGHT);
	    		}
	    		/*In this case, the unvisited neighbor cell has to be one space 
	    		 *above the current cell, so no else-if statement is needed.
	    		 */
	    		else
	    		{
	    			firstMaze.removeWall(currentX, currentY, Direction.UP);
	    		}
	    		//Pushing the current and the random neighbor cell onto the stack
	    		cellStack.push(currentCell);
        		cellStack.push(unvisitedNeighbor);
    		}
    		//Popping the current cell off the stack
    		currentCell = cellStack.pop();
    	}
    	//Randomly choosing start and end cells
    	firstMaze.setStart((int)(Math.random() * size), (int)(Math.random() * size));
        firstMaze.setEnd((int)(Math.random() * size), (int)(Math.random() * size));
        /*If the start and end cell are the same, the end cell is regenerated 
         *until it is different from the start cell.
         */
        while(firstMaze.getStart().equals(firstMaze.getEnd()))
        {
        	firstMaze.setEnd((int)(Math.random() * size), (int)(Math.random() * size));
        }
        return firstMaze;
    }
    /*Returns a random unvisited neighboring cell if the current cell has any
     *unvisited neighbors.
     */
    public Cell randomNeighbor(Maze firstMaze, int currentX, int currentY)
    {
    	ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();
    	if(currentX - 1 >= 0 && !firstMaze.isVisited(currentX - 1, currentY))
    	{
    		unvisitedNeighbors.add(new Cell(currentX - 1, currentY));
    	}
    	if(currentX + 1 < firstMaze.size() && !firstMaze.isVisited(currentX + 1, currentY))
    	{
    		unvisitedNeighbors.add(new Cell(currentX + 1, currentY));
    	}
    	if(currentY - 1 >= 0 && !firstMaze.isVisited(currentX, currentY - 1))
    	{
    		unvisitedNeighbors.add(new Cell(currentX, currentY - 1));
    	}
    	if(currentY + 1 < firstMaze.size() && !firstMaze.isVisited(currentX, currentY + 1))
    	{
    		unvisitedNeighbors.add(new Cell(currentX, currentY + 1));
    	}
    	if(unvisitedNeighbors.size() > 0)
    	{
    		/*StdRandom.uniform(n) returns a random integer uniformly between 
    		 *0 and n (0 inclusive and n exclusive).
    		 */
    		int random = StdRandom.uniform(unvisitedNeighbors.size());
    		return unvisitedNeighbors.get(random);
    	}
    	//If no unvisited neighboring cells are found, return null
    	else
    	{
    		return null;
    	}
    	
    }
    /**
     * Creates and draws a sample maze. Try generating mazes with different sizes!
     *
     * @param args unused
     */
    public static void main(String[] args)
    {
        StdRandom.setSeed(3);
        int size = 25; // Setting above 200 is not recommended!
        MazeGenerator generator = new MazeGenerator();
        Maze maze = generator.generate(size);
        maze.draw();
    }
}
