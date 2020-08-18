package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(w, h);

		// 4. select a random cell to start
		
		Cell randCell = maze.cells[randGen.nextInt(maze.cells.length)][randGen.nextInt(maze.cells[0].length)];
		
		// 5. call selectNextPath method with the randomly selected cell

		selectNextPath(randCell);

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited

		currentCell.setBeenVisited(true);

		// B. Get an ArrayList of unvisited neighbors using the current cell and the
		// method below

		ArrayList<Cell> unvisitedNeighbors = getUnvisitedNeighbors(currentCell);
		
		// C. if has unvisited neighbors,

		// C1. select one at random.

		// C2. push it to the stack

		// C3. remove the wall between the two cells

		// C4. make the new cell the current cell and mark it as visited

		// C5. call the selectNextPath method with the current cell

		// D. if all neighbors are visited

		// D1. if the stack is not empty

		// D1a. pop a cell from the stack

		// D1b. make that the current cell

		// D1c. call the selectNextPath method with the current cell

		if (unvisitedNeighbors.size() > 0) {
			Cell randCell = unvisitedNeighbors.get(randGen.nextInt(unvisitedNeighbors.size()));
			uncheckedCells.push(randCell);
			removeWalls(currentCell, randCell);
			currentCell = randCell;
			currentCell.setBeenVisited(true);
			selectNextPath(currentCell);
		}
		else {
			if (uncheckedCells.size() != 0) {
				Cell popped = uncheckedCells.pop();
				currentCell = popped;
				selectNextPath(currentCell);
			}
		}
		
		
	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {

		for (int i = 0; i < maze.cells.length; i++) {
			for (int j = 0; j < maze.cells[i].length; j++) {
				if (maze.cells[i][j].equals(c1)) {
					if (j == maze.cells[i].length - 1) {
						if (maze.cells[i][j - 1].equals(c2)) {

							c1.setWestWall(false);
							c2.setEastWall(false);

						}
//                                                                     ^\_("_")_/^
					} else if (j == 0) {                          //       | |
						                           //                     /   \  
						if (maze.cells[i][j + 1].equals(c2)) {

							c1.setEastWall(false);
							c2.setWestWall(false);
						}
					} else {

						if (maze.cells[i][j + 1].equals(c2)) {
							c1.setEastWall(false);
							c2.setWestWall(false);
						} else if (maze.cells[i][j - 1].equals(c2)) {
							c1.setWestWall(false);
							c2.setEastWall(false);
						}
					}

					if (i == maze.cells.length-1) {
						if (maze.cells[i - 1][j].equals(c2)) {

							c1.setNorthWall(false);
							c2.setSouthWall(false);
						}
					} else if (i == 0) {
						if (maze.cells[i + 1][j].equals(c2)) {
							c1.setSouthWall(false);
							c2.setNorthWall(false);
						}

					} else {

						if (maze.cells[i + 1][j].equals(c2)) {
							c1.setSouthWall(false);
							c2.setNorthWall(false);
						} else if (maze.cells[i - 1][j].equals(c2)) {
							c1.setNorthWall(false);
							c2.setSouthWall(false);
						}
					}
				}

			}

		}
		
	maze.cells[maze.cells.length-1][0].setWestWall(false);
	maze.cells[0][maze.cells.length-1].setEastWall(false);

	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();

		for (int i = 0; i < maze.cells.length; i++) {
			for (int j = 0; j < maze.cells[i].length; j++) {
				if (maze.cells[i][j].equals(c)) {
					
					if (j < maze.cells[i].length - 1) {
						if (!maze.cells[i][j + 1].hasBeenVisited()) {
							unvisitedNeighbors.add(maze.cells[i][j + 1]);

						}
					}
					if (j >  0) {
						if (!maze.cells[i][j - 1].hasBeenVisited()) {

							unvisitedNeighbors.add(maze.cells[i][j - 1]);
						}
					}
					if (i > 0) {
						if (!maze.cells[i - 1][j].hasBeenVisited()) {

							unvisitedNeighbors.add(maze.cells[i - 1][j]);
						}
					}
					if (i < maze.cells.length-1) {
						if (!maze.cells[i + 1][j].hasBeenVisited()) {

							unvisitedNeighbors.add(maze.cells[i + 1][j]);
						}
					}
				}
			}
		}
		

		return unvisitedNeighbors;
	}
}
