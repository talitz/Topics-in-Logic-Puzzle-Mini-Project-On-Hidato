package reductions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import algorithms.HamiltonianPath;
import hidato.Cell;
import hidato.Hidato;

public class ReductionToHamiltonianPathAlgorithm {
	
	private int[][] GridGraph;
	private Hidato hidato;
	private Cell lastCellBelongToHemiltonianPath = null;
	private Cell[][] board = null;
	int [] vertexValues = null;
	
	public ReductionToHamiltonianPathAlgorithm(Hidato hidato) {
		this.GridGraph = new int[hidato.getEnd()][hidato.getEnd()];
		this.hidato = hidato;
		this.board = hidato.getBoard();
		vertexValues = new int[this.GridGraph.length];
		lastCellBelongToHemiltonianPath = calculateLastCellBelongToHemiltonianPath();
	}
	
	public String setAndRunReductionToHamiltonPathAlgorithm() {  
		
		    System.out.println("Just got a hidato puzzle, convert it into a grid graph!");
		    System.out.println(hidato);
		    ArrayList<Integer> missingValues = hidato.getNonExistingValues();
		    hidato.getCellByVertexIndex(0).setIsPartOfHamiltonianPath(true);
		    
		    convertHidatoToFullGridGraph();
		    removeEdgesToVertexWithValueOne();
			displayGridGraph(GridGraph);

		    removeEdgesAmongNeighborsWithSuccessorValues();
		    displayGridGraph(GridGraph);

			HamiltonianPath hamiltonianPathAlgorithm = new HamiltonianPath();

			constructVertexValues();
			
		    String path = null;
 
		    while(!missingValues.isEmpty()) {
		            System.out.println("--------------------------");
		    		Integer b = missingValues.remove(0);
		    		ArrayList<Integer> neighborsIndexesToPutAt = new ArrayList<Integer>();
		    		Cell current = lastCellBelongToHemiltonianPath;
		    		int i,j;
		    		
		    		for(int k = 0; k < board.length*board.length; k++) {
		    			if(GridGraph[current.getIndex()][k] == 1 && hidato.getCellByVertexIndex(k).getValue() == null) {
		    				neighborsIndexesToPutAt.add(k);
		    			}
		    		}
		    		
		    		int index = !neighborsIndexesToPutAt.isEmpty() ? neighborsIndexesToPutAt.remove(0) : -1;
		    		
		    		if(index != -1) {	
				    	Cell firstToPutAt = hidato.getCellByVertexIndex(index);
				    	hidato.setCellWithNewValue(index, b);
				        System.out.println(hidato);
				    	constructVertexValues();
				    	path = hamiltonianPathAlgorithm.isHamiltonianPath(GridGraph,vertexValues);
				    	System.out.println("--------------------------");
				    	while(path == null) {
				    		hidato.setCellWithNewValue(index, null); //this value didn't work for us...
				    		if(neighborsIndexesToPutAt.isEmpty()) return "There is no solution for this Hidato riddle!";
				    		else {
				    				index = neighborsIndexesToPutAt.remove(0);
				    				firstToPutAt = hidato.getCellByVertexIndex(index);
						    		hidato.setCellWithNewValue(firstToPutAt.getIndex(), b);
						    		   System.out.println(hidato);
						    		constructVertexValues();
						    		path = hamiltonianPathAlgorithm.isHamiltonianPath(GridGraph,vertexValues);
				    		}
				    	}
				    		
				    	removeEdgesAmongNeighborsWithSuccessorValues();
				    	firstToPutAt.setIsPartOfHamiltonianPath(true);
				    	lastCellBelongToHemiltonianPath = calculateLastCellBelongToHemiltonianPath();
		    		} else {
		    			missingValues.add(b);
		    			Collections.sort(missingValues);
		    		}
		    		
		    }
		    System.out.println(hidato);
		    return path;    
	
	}

	private void constructVertexValues() {
		for(int i=0; i<vertexValues.length; i++) {
			   Cell curr = hidato.getCellByVertexIndex(i);
			   if(curr.getValue() == null) vertexValues[i] = -1;
			   else vertexValues[i] = curr.getValue();	    
		}
	}
	
	private Cell calculateLastCellBelongToHemiltonianPath() {
	    int max = board.length*board.length;
	    Cell maxCell = null;
	    ArrayList<Cell> allCellsWithValues = new ArrayList<Cell>();
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				 if(board[i][j].getValue() != null && board[i][j].getValue() < max) {
					 allCellsWithValues.add(board[i][j]);
				 }
			}
		}	
		Collections.sort(allCellsWithValues, new CellComparator());
		return allCellsWithValues.remove(allCellsWithValues.size()-1);
	}
	class CellComparator implements Comparator<Cell> {
	    @Override
	    public int compare(Cell a, Cell b) {
	        return a.getValue().compareTo(b.getValue());
	    }
	}
	private void removeEdgesAmongNeighborsWithSuccessorValues() {
		Cell current = null;
		Cell right = null;
		Cell left = null;
		Cell bottom = null;
		Cell up = null;
		Cell downRightDiagonal = null;
		Cell downLeftDiagonal = null;
		Cell upRightDiagonal = null;
		Cell upLeftDiagonal = null;
		
		for(int i=0; i<board.length; i++) {
				for(int j=0; j<board[i].length; j++) {
					 current = board[i][j];
					 if(current.getValue() == null) continue;
					 right = null;
					 left = null;
					 bottom = null;
					 up = null;
					 downLeftDiagonal = null;
					 downRightDiagonal = null;
					 upLeftDiagonal = null;
					 upRightDiagonal = null;
					 
					 if(boundsCheck(i,j+1,board)) right = board[i][j+1];
					 if(boundsCheck(i,j-1,board)) left = board[i][j-1]; 
					 if(boundsCheck(i+1,j,board))  bottom = board[i+1][j];	 
					 if(boundsCheck(i-1,j,board))  up = board[i-1][j];	 
					 if(boundsCheck(i+1,j+1,board)) downRightDiagonal = board[i+1][j+1];
					 if(boundsCheck(i-1,j+1,board)) upRightDiagonal = board[i-1][j+1];
					 if(boundsCheck(i+1,j-1,board)) downLeftDiagonal = board[i+1][j-1];
					 if(boundsCheck(i-1,j-1,board)) upLeftDiagonal = board[i-1][j-1];
					 
					 //(current,right) is an only edge case
					 if(right != null && right.getValue() != null && right.getValue() - 1 == current.getValue()) {
						 removeEdges(current,right);
					 }
					 
					 //(current,left) is an only edge case
					 if(left != null && left.getValue() != null &&left.getValue() - 1 == current.getValue()) {
						 removeEdges(current,left);
					 }
					 
					 //(current,bottom) is an only edge case
					 if(bottom != null && bottom.getValue() != null && bottom.getValue() - 1 == current.getValue()) {
						 removeEdges(current,bottom);
					 }
					 
					 //(current,up) is an only edge case
					 if(up != null && up.getValue() != null && up.getValue() - 1 == current.getValue()) {
						 removeEdges(current,up);
					 }
					 
					 //(current,downRightDiagonal) is an only edge case
					 if(downRightDiagonal != null && downRightDiagonal.getValue() != null && downRightDiagonal.getValue() - 1 == current.getValue()) {
						 removeEdges(current,downRightDiagonal);
					 }
					 
					 //(current,upRightDiagonal) is an only edge case
					 if(upRightDiagonal != null && upRightDiagonal.getValue() != null && upRightDiagonal.getValue() - 1 == current.getValue()) {
						 removeEdges(current,upRightDiagonal);
					 }
					 
					 //(current,downLeftDiagonal) is an only edge case
					 if(downLeftDiagonal != null && downLeftDiagonal.getValue() != null && downLeftDiagonal.getValue() - 1 == current.getValue()) {
						 removeEdges(current,downLeftDiagonal);
					 }
					 
					 //(current,upLeftDiagonal) is an only edge case
					 if(upLeftDiagonal != null && upLeftDiagonal.getValue() != null && upLeftDiagonal.getValue() - 1 == current.getValue()) {
						 removeEdges(current,upLeftDiagonal);
					 }
				}
		}
	}

	private void removeEdges(Cell current, Cell successor) {
		Cell[][] board = hidato.getBoard();
		Cell vertex = null;
		successor.setIsPartOfHamiltonianPath(true);
		lastCellBelongToHemiltonianPath = successor;
		//remove all edges (X, successor) such that x != current
		//if current is a part of hemiltonian cycle, then remove all (current, X) such that X != successor 
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				 vertex = board[i][j];
	
				 if(vertex.getIndex() != current.getIndex()) {
						 GridGraph[vertex.getIndex()][successor.getIndex()] = 0;
				 }
				 
				 if(current.isPartOfHamiltonianPath() && vertex.getIndex() != successor.getIndex()) {
					 	 GridGraph[current.getIndex()][vertex.getIndex()] = 0;
				 }
			}
		}	
		//remove all edges (successor, X) 
		 GridGraph[successor.getIndex()][current.getIndex()] = 0;	
	}

	private void removeEdgesToVertexWithValueOne() {
		Cell[][] board = hidato.getBoard();
		Cell vertex = null;
		Cell one = null;
		outer:
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				 if(board[i][j].getValue() != null && board[i][j].getValue() == 1) {
					 one = board[i][j];
					 break outer;
				 }
				
			}
		}	
		
		//remove all edges (X, V0) 
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				 vertex = board[i][j];
				 GridGraph[vertex.getIndex()][one.getIndex()] = 0;
			}
		}	
	}
	 
	public void convertHidatoToFullGridGraph() {
		Cell[][] board = hidato.getBoard();
		Cell current = null;
		Cell right = null;
		Cell bottom = null;
		Cell downRightDiagonal = null;
		Cell downLeftDiagonal = null;
		
		for(int i=0; i<board.length; i++) {
				for(int j=0; j<board[i].length; j++) {
					 current = board[i][j];
					 right = null;
					 bottom = null;
					 downLeftDiagonal = null;
					 downRightDiagonal = null;
					 
					 if(boundsCheck(i,j+1,board)) {
						 right = board[i][j+1];
					 }
					 
					 if(boundsCheck(i+1,j,board)) {
						 bottom = board[i+1][j];
					 }
					 
					 if(boundsCheck(i+1,j+1,board)) {
						 downRightDiagonal = board[i+1][j+1];
					 }
					
					 if(boundsCheck(i+1,j-1,board)) {
						 downLeftDiagonal = board[i+1][j-1];
					 }	 
					 connectNeighborsIfExists(current,right,bottom,downRightDiagonal,downLeftDiagonal);
				}
		}
	}

	private void connectNeighborsIfExists(Cell current, Cell right, Cell bottom, Cell downRightDiagonal, Cell downLeftDiagonal) {
		 GridGraph[current.getIndex()][current.getIndex()] = 1;
		 
		 if(right != null) { 
			 GridGraph[current.getIndex()][right.getIndex()] = 1;
			 GridGraph[right.getIndex()][current.getIndex()] = 1;
		 }
		 
		 if(bottom != null) {		 
			 GridGraph[current.getIndex()][bottom.getIndex()] = 1;
			 GridGraph[bottom.getIndex()][current.getIndex()] = 1;
		 }
		 
		 if(downRightDiagonal != null) {
			 GridGraph[current.getIndex()][downRightDiagonal.getIndex()] = 1;
			 GridGraph[downRightDiagonal.getIndex()][current.getIndex()] = 1;
		 }
		 
		 if(downLeftDiagonal != null) {
			 GridGraph[current.getIndex()][downLeftDiagonal.getIndex()] = 1;
			 GridGraph[downLeftDiagonal.getIndex()][current.getIndex()] = 1;
		 }
	}

	public static boolean boundsCheck(int i, int j, Cell[][] arr) {
		boolean flag = (i>=0);
		//System.out.println("flag1 = " + flag);
		flag &= (j >= 0);
		//System.out.println("flag2 = " + flag);
		flag &= (i < arr.length);
		//System.out.println("flag3 = " + flag);
		flag &= (j < arr[0].length);
		//System.out.println("flag4 = " + flag);
		return flag;
	}
	
	public static void displayGridGraph(int[][] GridGraph) {
		System.out.println();
		for(int i=0; i<GridGraph.length; i++) {
			for(int j=0; j<GridGraph[i].length; j++) {
				System.out.print(GridGraph[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
