package reductions;
import algorithms.HamiltonianPath;
import hidato.Cell;
import hidato.Hidato;

public class ReductionToHamiltonianPathAlgorithm {

	private int[][] GridGraph;
	private Hidato hidato;
	private Cell[][] board = null;
	int [] vertexValues = null;
	private Cell one;
	
	public ReductionToHamiltonianPathAlgorithm(Hidato hidato) {
		this.GridGraph = new int[hidato.getEnd()][hidato.getEnd()];
		this.hidato = hidato;
		this.board = hidato.getBoard();
		vertexValues = new int[this.GridGraph.length];
		one = hidato.getCellByVertexValueIfExistsOrNull(hidato.getStart());
	}

	public String setAndRunReductionToHamiltonPathAlgorithm() {  
		long startTime = System.currentTimeMillis();
		System.out.println("Just got a hidato puzzle, convert it into a grid graph!");
		System.out.println(hidato);
		
		convertHidatoToFullGridGraph();
	
		removeEdgesToVertexWithValueOne();

		removeEdgesAmongNeighborsWithSuccessorValues();
		//displayGridGraph(GridGraph);
		HamiltonianPath hamiltonianPathAlgorithm = new HamiltonianPath();

		constructVertexValues();

		String path = null;

		path = hamiltonianPathAlgorithm.isHamiltonianPath(GridGraph,vertexValues,one.getIndex());
		
		if(path != null) {
			String[] parts = path.split(";");
						
			for(int i=0; i<parts.length; i++) {
					int cellIndex = Integer.parseInt(parts[i]);
					if(!hidato.isCellWithExistingValue(cellIndex)) {
						hidato.setCellWithNewValue(cellIndex, Integer.valueOf(i)+1);
					}
			}
			System.out.println("The solution for this Hidato riddle:");
			System.out.println(hidato);
		} else {
			System.out.println("There is no solution for this Hidato riddle!");
		}
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("It took "+totalTime/1000 + " seconds for this algorithm to end!");
		return "Hidato's path: " + path;    

	}

	private void constructVertexValues() {
		for(int i=0; i<vertexValues.length; i++) {
			Cell curr = hidato.getCellByVertexIndex(i);
			if(curr.getValue() == null) vertexValues[i] = -1;
			else vertexValues[i] = curr.getValue();	    
		}
	}

	private void removeEdgesAmongNeighborsWithSuccessorValues() {
		Cell current = null,right = null,left = null,bottom = null,up = null,downRightDiagonal = null,downLeftDiagonal = null,upRightDiagonal = null,upLeftDiagonal = null;

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
		//remove all edges (X, successor) such that x != current
		//remove all (current, X) such that X != successor 
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				vertex = board[i][j];

				if(vertex.getIndex() != current.getIndex()) {
				
					if(GridGraph[vertex.getIndex()][successor.getIndex()] == 1) {
						GridGraph[vertex.getIndex()][successor.getIndex()] = 0;
					}
				}

				if(vertex.getIndex() != successor.getIndex()) {
					if(GridGraph[current.getIndex()][vertex.getIndex()] == 1) {
						GridGraph[current.getIndex()][vertex.getIndex()] = 0;
					}
				}
			}
		}	
		//remove all edges (successor, X) 
		GridGraph[successor.getIndex()][current.getIndex()] = 0;	
	}

	private void removeEdgesToVertexWithValueOne() {
		Cell[][] board = hidato.getBoard();
		Cell vertex = null;	
		
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

				if(boundsCheck(i,j+1,board)) right = board[i][j+1];
				if(boundsCheck(i+1,j,board)) bottom = board[i+1][j];
				if(boundsCheck(i+1,j+1,board)) downRightDiagonal = board[i+1][j+1];
				if(boundsCheck(i+1,j-1,board)) downLeftDiagonal = board[i+1][j-1];
					 
				connectNeighborsIfExists(current,right,bottom,downRightDiagonal,downLeftDiagonal);
			}
		}
	}

	private void connectNeighborsIfExists(Cell current, Cell right, Cell bottom, Cell downRightDiagonal, Cell downLeftDiagonal) {
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
		return i >=0 && j >= 0 && i < arr.length && j < arr[0].length;
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
