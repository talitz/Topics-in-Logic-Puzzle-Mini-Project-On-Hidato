package reductions;
import java.util.Arrays;

import algorithms.HamiltonianPath;
import hidato.Cell;
import hidato.Hidato;
import hidato.HidatoGenerator;

public class ReductionToHamiltonianPathAlgorithm {
	
	private int[][] GridGraph;
	private Hidato hidato;
	
	public ReductionToHamiltonianPathAlgorithm(Hidato hidato) {
		this.GridGraph = new int[hidato.getEnd()][hidato.getEnd()];
		this.hidato = hidato;

	}
	
	public void setAndRunReductionToHamiltonPathAlgorithm() {  
		
		    System.out.println("Just got a hidato puzzle, convert it into a grid graph!");
			
		    System.out.println(hidato);
		    
		    convertHidatoToGridGraph(true);
			
			displayGridGraph(GridGraph);
		    
			HamiltonianPath hamiltonianPathAlgorithm = new HamiltonianPath();
		    
		    String path = hamiltonianPathAlgorithm.isHamiltonianPath(GridGraph);
		    
		    boolean done = false;
		    
		    if(path != null) {
			    	//we have a potential solution to the hidato puzzle! let's try and solve it!
			    	System.out.println("found hamiltonian path: " + path);
			    	done = false;
			    	
			    	while(!done) {
			    		String[] vertexes = path.split(";");
			    		
			    		//now we need to find the first vertex without value!
			    		int countAllCellsWithValues = 0;
			    		int vertexIndex = -1;
			    	
			    		for(String vertex : vertexes) {
				    			 Cell current = hidato.getCellByVertexIndex(Integer.valueOf(vertex)); 
				    			 if(current.getValue() == null) {
				    				 System.out.println("the first vertex with no value is with index" +current.getIndex());
				    				 vertexIndex = current.getIndex();
				    				 break;
				    			 } else {
				    				 countAllCellsWithValues++;
				    			 }
			    		}
			    		
			    		if(countAllCellsWithValues < hidato.getEnd()) {
			    				boolean decideOnNewValue = false;
			    				hidato.setRunner();
					    		while(!decideOnNewValue) {
							    		//now we need to decide - what value is he going to get?
					    				System.out.println("now we need to decide - what value is he going to get?");
					    				
							    		int valueToTry = hidato.tryToMatchValueToCell(vertexIndex);
							    		if(valueToTry == 2) 
							    			System.out.println("");
					    				System.out.println("let's try: " + valueToTry);
					    
					    				hidato.setCellWithNewValue(vertexIndex, valueToTry); //build the new grid graph
					    				convertHidatoToGridGraph(false);
					    				System.out.println("The new Hidato is now:");
					    				System.out.println(hidato);
					    				System.out.println("With the grid");
					    				displayGridGraph(GridGraph);
					    				
							    		path = hamiltonianPathAlgorithm.isHamiltonianPath(GridGraph);
							    		if(path != null) {
						    				System.out.println("we found an hamiltonian path with the value = " + valueToTry + ", path = "+path);
							    			hidato.addToExistingValue(valueToTry);
							    			decideOnNewValue = true; //go find it's path and keep on
							    			System.out.println("continue with the algorithm...");
							    		} else {
							    			System.out.println("could not find a path for this one");
							    		}
					    		}
			    		} else {
				    			done = true; //we have values for all cells!
			    		}
			    	}
			    	System.out.println();
			    	System.out.println("final path solution is " + path);
			    	System.out.println(hidato);
		    } else {
			    	System.out.println("This Hidato has no solution!");
		    }
	}

	//this function is building a grid graph based on the hidato puzzle.
	//very important - if we have successors i, i+1 there must be one edge between them and only one edge!
	//so the hamiltonian path will go through this edge and we find a solution!
	public void convertHidatoToGridGraph(boolean isFirst) {
		Cell[][] board = hidato.getBoard();
		Cell current = null;
		Cell right = null;
		Cell bottom = null;
		Cell downRightDiagonal = null;
		Cell downLeftDiagonal = null;
		boolean oneRightEdgeOnly = false,oneBottomEdgeOnly = false,oneDownRightDiagonalEdgeOnly = false,oneDownLeftDiagonalEdgeOnly = false;
		
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				 current = board[i][j];
				 right = null;
				 bottom = null;
				 downRightDiagonal = null;
				 downLeftDiagonal = null;
				 oneRightEdgeOnly = false;
				 oneBottomEdgeOnly = false;
				 oneDownRightDiagonalEdgeOnly = false;
				 oneDownLeftDiagonalEdgeOnly = false;
				 
				 int currentIndex = current.getIndex();
				 
				 if(boundsCheck(i,j+1,board)) {
					if(isFirst) {
						right = board[i][j+1];
					} else {
						right = board[i][j+1];
						if(GridGraph[currentIndex][right.getIndex()] == 0 || !current.isPartOfHamiltonianPath())
							right = null;
					}
				 }
				 
				 if(boundsCheck(i+1,j,board)) {
					if(isFirst) {
						bottom = board[i+1][j];
					} else {
						bottom = board[i+1][j];
					    if(GridGraph[currentIndex][bottom.getIndex()] == 0 || !current.isPartOfHamiltonianPath())
					    	bottom = null;
					} 
				 }
				 
				 if(boundsCheck(i+1,j+1,board)) {
					if(isFirst) {
						 downRightDiagonal = board[i + 1][j + 1];
					} else {
						downRightDiagonal = board[i + 1][j + 1];
						if(GridGraph[currentIndex][downRightDiagonal.getIndex()] == 0 || !current.isPartOfHamiltonianPath())
							downRightDiagonal = null;
					}

				 }
				 
				 if(boundsCheck(i+1,j-1,board)) {
					if(isFirst) {
						 downLeftDiagonal = board[i + 1][j - 1];
					} else {
						downLeftDiagonal = board[i + 1][j - 1];
						if(GridGraph[currentIndex][downLeftDiagonal.getIndex()] == 0 || !current.isPartOfHamiltonianPath())
							downLeftDiagonal = null;
					}
					
				 }

				 GridGraph[currentIndex][currentIndex] = 1; //every vertex is connected to itself... trivial 
				 
				 if(current.getValue() != null && !current.isPartOfHamiltonianPath()) {
					 if(right != null && right.getValue() != null && !right.isPartOfHamiltonianPath() && (right.getValue().intValue() + 1 == current.getValue().intValue() || right.getValue().intValue() - 1 == current.getValue().intValue()))
						 oneRightEdgeOnly = true;
					 
					 if(bottom != null && bottom.getValue() != null && !bottom.isPartOfHamiltonianPath() && (bottom.getValue().intValue() + 1 == current.getValue().intValue() || bottom.getValue().intValue() - 1 == current.getValue().intValue()))
						 oneBottomEdgeOnly = true;
					 
					 if(downRightDiagonal != null && downRightDiagonal.getValue() != null && !downRightDiagonal.isPartOfHamiltonianPath() && (downRightDiagonal.getValue().intValue() + 1 == current.getValue().intValue() || downRightDiagonal.getValue().intValue() - 1 == current.getValue().intValue()))
						 oneDownRightDiagonalEdgeOnly = true;
					 
					 if(downLeftDiagonal != null && downLeftDiagonal.getValue() != null && !downLeftDiagonal.isPartOfHamiltonianPath() && (downLeftDiagonal.getValue().intValue() + 1 == current.getValue().intValue() || downLeftDiagonal.getValue().intValue() - 1 == current.getValue().intValue()))
						 oneDownLeftDiagonalEdgeOnly = true;
				 }
				 
					 
				 boolean oneEdgeOnly = oneRightEdgeOnly || oneBottomEdgeOnly || oneDownRightDiagonalEdgeOnly || oneDownLeftDiagonalEdgeOnly;
				 
				 if(oneEdgeOnly) {
					 if(oneRightEdgeOnly && !right.isPartOfHamiltonianPath()) {
						 Arrays.fill(GridGraph[currentIndex], 0);
						 for(int k = 0; k < GridGraph[0].length; k++)
							 GridGraph[k][currentIndex] = 0;
						 
						 GridGraph[currentIndex][currentIndex] = 1;
						 
						 GridGraph[currentIndex][right.getIndex()] = 1;
						 GridGraph[right.getIndex()][currentIndex] = 1;
						 current.setIsPartOfHamiltonianPath(true);
						 continue;
					 }
					 if(oneBottomEdgeOnly && !bottom.isPartOfHamiltonianPath()) {
						 Arrays.fill(GridGraph[currentIndex], 0);
						 for(int k = 0; k < GridGraph[0].length; k++)
							 GridGraph[k][currentIndex] = 0;
						 
						 GridGraph[currentIndex][currentIndex] = 1; 
						 GridGraph[currentIndex][bottom.getIndex()] = 1;
						 GridGraph[bottom.getIndex()][currentIndex] = 1;
						 current.setIsPartOfHamiltonianPath(true);
						 continue;
					 }
					 if(oneDownRightDiagonalEdgeOnly && !downRightDiagonal.isPartOfHamiltonianPath()) {
						 Arrays.fill(GridGraph[currentIndex], 0);
						 for(int k = 0; k < GridGraph[0].length; k++)
							 GridGraph[k][currentIndex] = 0;
						 
						 GridGraph[currentIndex][currentIndex] = 1;
						 GridGraph[currentIndex][downRightDiagonal.getIndex()] = 1;
						 GridGraph[downRightDiagonal.getIndex()][currentIndex] = 1;
						 current.setIsPartOfHamiltonianPath(true);
						 continue;
					 }
					 if(oneDownLeftDiagonalEdgeOnly  && !downLeftDiagonal.isPartOfHamiltonianPath()) {
						 Arrays.fill(GridGraph[currentIndex], 0);
						 for(int k = 0; k < GridGraph[0].length; k++)
							 GridGraph[k][currentIndex] = 0;
						 
						 GridGraph[currentIndex][currentIndex] = 1;
						 GridGraph[currentIndex][downLeftDiagonal.getIndex()] = 1;
						 GridGraph[downLeftDiagonal.getIndex()][currentIndex] = 1;
						 current.setIsPartOfHamiltonianPath(true);
						 continue;
					 }		 
				 } else {
						 //if a righty neighbor exists
						 if(right != null && !oneEdgeOnly && !right.isPartOfHamiltonianPath()) { 
							 //the right neighbor is connected to him
							 GridGraph[currentIndex][right.getIndex()] = 1;
							 GridGraph[right.getIndex()][currentIndex] = 1;
						 }
						 
						 //if a bottom neighbor exists
						 if(bottom != null && !oneEdgeOnly && !bottom.isPartOfHamiltonianPath()) {		 
							 //the right neighbor is connected to him
							 GridGraph[currentIndex][bottom.getIndex()] = 1;
							 GridGraph[bottom.getIndex()][currentIndex] = 1;
						 }
						 
						 //if a down right diagonal neighbor exists
						 if(downRightDiagonal != null && !oneEdgeOnly && !downRightDiagonal.isPartOfHamiltonianPath()) {
							 //the right neighbor is connected to him
							 GridGraph[currentIndex][downRightDiagonal.getIndex()] = 1;
							 GridGraph[downRightDiagonal.getIndex()][currentIndex] = 1;
						 }
						 
						 //if a down left diagonal neighbor exists
						 if(downLeftDiagonal != null && !oneEdgeOnly && !downLeftDiagonal.isPartOfHamiltonianPath()) {
							 //the right neighbor is connected to him
							 GridGraph[currentIndex][downLeftDiagonal.getIndex()] = 1;
							 GridGraph[downLeftDiagonal.getIndex()][currentIndex] = 1;
						 }	
				 }
			}
		}
	}

	public static boolean boundsCheck(int i, int j, Cell[][] arr) {
		return (i >= 0 && j >=0 && i < arr.length && j < arr[0].length);
	}
	
	public static void displayGridGraph(int[][] GridGraph) {
		for(int i=0; i<GridGraph.length; i++) {
			for(int j=0; j<GridGraph[i].length; j++) {
				System.out.print(GridGraph[i][j] + " ");
			}
			System.out.println();
		}
	}
}
