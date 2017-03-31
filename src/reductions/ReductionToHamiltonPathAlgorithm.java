package reductions;
import algorithms.HamiltonianPath;
import hidato.Cell;
import hidato.Hidato;

public class ReductionToHamiltonPathAlgorithm {
	public void setAndRunReductionToHamiltonPathAlgorithm(Hidato hidato) {  
			int [][] GridGraph = convertHidatoToGridGraph(hidato);
		    HamiltonianPath hamiltonianPathAlgorithm = new HamiltonianPath();
		    
		    if(hamiltonianPathAlgorithm.isHamiltonianPath(GridGraph)) {
			    	//we have a potential solution to the hidato puzzle! let's try and solve it!
			    	boolean done = false;
			    	while(!done) {
			    		String path = hamiltonianPathAlgorithm.findHamiltonianPath(GridGraph);
			    		String[] vertexes = path.split(";");
			    		
			    		//now we need to find the first vertex without value!
			    		int countAllCellsWithValues = 0;
			    		int vertexIndex = -1;
			    		for(String vertex : vertexes) {
			    			 Cell current = hidato.getCellByVertexValue(Integer.valueOf(vertex)); 
			    			 if(current.getValue() == null) {
			    				 vertexIndex = current.getIndex();
			    				 break;
			    			 } else {
			    				 countAllCellsWithValues++;
			    			 }
			    		}
			    		
			    		if(countAllCellsWithValues != hidato.getEnd()) {
				    		boolean decideOnNewValue = false;
				    		while(!decideOnNewValue) {
						    		//now we need to decide - what value is he going to get?
						    		int valueToTry = hidato.tryToMatchValueToCell(vertexIndex);
						    		GridGraph = null; //build the new grid graph
						    		if(hamiltonianPathAlgorithm.isHamiltonianPath(GridGraph)) {
						    			decideOnNewValue = true; //go find it's path and keep on
						    		}
				    		}
			    		} else {
			    			done = true; //we have values for all cells!
			    		}
			    	}
		    } else {
			    	System.out.println("This Hidato has no solution!");
		    }
	}

	private int[][] convertHidatoToGridGraph(Hidato h) {
		int[][] GridGraph = new int[h.getEnd()][h.getEnd()];
		Cell[][] board = h.getBoard();
		
		for(int i=0; i<GridGraph.length; i++) {
			for(int j=0; j<GridGraph.length; j++) {
				
				 Cell current = board[i][j];
				
				 if(i == j) {
					 GridGraph[i][j] = 1; //every vertex is connected to itself... trivial
				 } else if(current.getValue() == null) { //if the cell has no value - we need to fill it ourself
					 
				 } else {
					 
				 }
			}
		}
		
		return null;
	}

}
