/**
 * Name: Viraj Goyal
 * Mrs. Kankelborg
 * Period: 1
 * Project 3 Maze Solver Part 2: Maze Solver
 * Revision History:
 * 1/3/2020 - Initialized a queue with the start cell and created 
 * other important variables such as the solution path and the integer array that 
 * tracks how many moves it takes to get to the start cell from another cell
 * 1/4/2020 - Created main while loop that explores paths to get from the start
 * cell to the end cell (enqueues neighbors of the current cell that are reachable
 * and unvisited)
 * 1/5/2020 - Created a matrix to track how many moves the end cell was away from the 
 * start cell, and retraced moves using the matrix to build a path to the start cell
 * 1/6/2020 - Fixed multiple bugs related to the correct path not being drawn
 * correctly(all cells were being drawn using the color blue initially)
 * 1/7/2020 - Commented all code
 * Class Description: Solves mazes. 
 * Please refer to the specification for instructions on how to solve mazes.
 */
public class MazeSolver
{
    /**
     * Provides a solution for a given maze, if possible. A solution is a path from the start cell
     * to the finish cell (inclusive). If there is no solution to the maze then returns the static
     * instance {@link Path#NO_PATH}. If the maze is perfect then there must be only one solution.
     *
     * @param maze the maze to solve
     * @return a solution for the maze or {@link Path#NO_PATH} if there is no solution
     */
    public Path solve(Maze maze)
    {
    	//Queue initialized with the start cell
        Queue<Cell> cellQueue = new Queue<Cell>();
        Cell currentCell = new Cell(maze.getStart().getX(), maze.getStart().getY());
        cellQueue.enqueue(maze.getStart());
        /*Initialized an array of integers to track how many moves it takes to get 
         *from the current cell to the start cell.
         */
        int[][] trackMoves = new int[maze.size()][maze.size()];
        Path solutionPath = new Path();
        /*The number of moves it takes to get from the current cell to the 
         *start cell can be accessed using the current cell's coordinates as indices 
         *in the trackMoves array.
         */
        trackMoves[currentCell.getX()][currentCell.getY()] = 0;
        while(!cellQueue.isEmpty()) 
        {
        	int currentX = currentCell.getX();
    		int currentY = currentCell.getY();
    		maze.visit(currentX, currentY);
    		if(currentCell.equals(maze.getEnd()))
    		{
    			solutionPath.addLast(currentCell);
    			for(int i = 0; i < trackMoves[currentX][currentY]; i++)
    			{
    				int newCurrentX = currentCell.getX();
    				int newCurrentY = currentCell.getY();
    				/*A cell that neighbors the current cell is added to the front of the solution path 
    				 *if it is reachable from the current cell and it has a move value that is one less 
    				 *than the current cell's move value.
    				 *This allows moves to be retraced back to the start cell and a solution path
    				 *to be created.
    				 */
    				if(newCurrentX - 1 >= 0 && maze.isOpen(newCurrentX, newCurrentY, Direction.LEFT) && 
    				   trackMoves[newCurrentX - 1][newCurrentY] == trackMoves[newCurrentX][newCurrentY] - 1)
    				{
    					solutionPath.addFirst(new Cell(newCurrentX - 1, newCurrentY));
    					currentCell = new Cell(newCurrentX - 1, newCurrentY);
    				}
    				else if(newCurrentX + 1 < maze.size() && maze.isOpen(newCurrentX, newCurrentY, Direction.RIGHT) && 
    						trackMoves[newCurrentX + 1][newCurrentY] == trackMoves[newCurrentX][newCurrentY] - 1)
	    	    	{
	    	    		solutionPath.addFirst(new Cell(newCurrentX + 1, newCurrentY));
	    	    		currentCell = new Cell(newCurrentX + 1, newCurrentY);
	    	    	}
    				else if(newCurrentY - 1 >= 0 && maze.isOpen(newCurrentX, newCurrentY, Direction.DOWN) && 
    						trackMoves[newCurrentX][newCurrentY - 1] == trackMoves[newCurrentX][newCurrentY] - 1)
    	    		{
    	    			solutionPath.addFirst(new Cell(newCurrentX, newCurrentY - 1));
    	    			currentCell = new Cell(newCurrentX, newCurrentY - 1);
    	    		}
    				else if(newCurrentY + 1 < maze.size() && maze.isOpen(newCurrentX, newCurrentY, Direction.UP) && 
    	    				trackMoves[newCurrentX][newCurrentY + 1] == trackMoves[newCurrentX][newCurrentY] - 1) 
    				{
    	    			solutionPath.addFirst(new Cell(newCurrentX, newCurrentY + 1));
    	    			currentCell = new Cell(newCurrentX, newCurrentY + 1);
    	    		}
    			}
    			return solutionPath;
    		}
    		/*Enqueuing all cells that neighbor the current cell that are
    		 *reachable and unvisited
    		 */
    		if(maze.isOpen(currentX, currentY, Direction.LEFT) && 
    		   !maze.isVisited(currentX - 1, currentY))
    		{
    			Cell newCell = new Cell(currentX - 1, currentY);
    			cellQueue.enqueue(newCell);
    			trackMoves[currentX - 1][currentY] = trackMoves[currentX][currentY] + 1;
    		}
    		if(maze.isOpen(currentX, currentY, Direction.RIGHT) && 
    	       !maze.isVisited(currentX + 1, currentY))
    	    {
    	    	Cell newCell = new Cell(currentX + 1, currentY);
    	    	cellQueue.enqueue(newCell);
    	    	trackMoves[currentX + 1][currentY] = trackMoves[currentX][currentY] + 1;
    	    }
    		if(maze.isOpen(currentX, currentY, Direction.DOWN) && 
    		   !maze.isVisited(currentX, currentY - 1))
    	    {
    	    	Cell newCell = new Cell(currentX, currentY - 1);
    	    	cellQueue.enqueue(newCell);
    	    	trackMoves[currentX][currentY - 1] = trackMoves[currentX][currentY] + 1;
    	    }
    		if(maze.isOpen(currentX, currentY, Direction.UP) && 
    		   !maze.isVisited(currentX, currentY + 1))
    	    {
    	    	Cell newCell = new Cell(currentX, currentY + 1);
    	    	cellQueue.enqueue(newCell);
    	    	trackMoves[currentX][currentY + 1] = trackMoves[currentX][currentY] + 1;
    	    }
    		//Dequeuing the current cell off the queue
    		currentCell = cellQueue.dequeue();
        }
        //A special constant is returned if no solution to the maze has been found
        return Path.NO_PATH;
    }

    /**
     * Creates, solves, and draws a sample maze. Try solving mazes with different sizes!
     *
     * @param args unused
     */
    public static void main(String[] args)
    {
        // First, generate a new maze.
        int size = 25; // Setting above 200 is not recommended!
        MazeGenerator generator = new MazeGenerator();
        Maze maze = generator.generate(size);
        maze.freeze();

        // Next, solve it!
        MazeSolver solver = new MazeSolver();
        maze.resetVisited();
        Path solutionPath = solver.solve(maze);
        maze.setSolution(solutionPath);
        // This is so we can see which cells were explored and in what order.
        maze.setDrawVisited(true);

        maze.draw();
    }
}
