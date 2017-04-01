package algorithms;

import java.util.Arrays;

public class HamiltonianPath

{
    private int V, pathCount;

    private int[] path;     

    private int[][] graph;

    public String isHamiltonianPath(int[][] g)

    {
    	String path = findHamiltonianPath(g); 
    	return path.equals("No solution")? null : path;	
    }
    
    public String findHamiltonianPath(int[][] g) {
        V = g.length;
        path = new int[V];
        Arrays.fill(path, -1);
        
        graph = new int[V][V];
        
        for(int i=0; i<V; i++)
        	for(int j=0; j<V; j++)
        		  graph[i][j] = g[i][j];

        try {            
            path[0] = 0;
            pathCount = 1;            
            solve(0);
            return("No solution");
        } catch (Exception e) {
            return(display());
        }
    }

    public void solve(int vertex) throws Exception {
        /** solution **/
        if (pathCount == V)
            throw new Exception();

        /** all vertices selected but last vertex not linked to 0 **/
        if (pathCount == V)
           return;
        
        for (int v = 0; v < V; v++) {

            /** if connected **/
            if (graph[vertex][v] == 1 ) {

                /** add to path **/            
                path[pathCount++] = v;    

                /** remove connection **/            
                graph[vertex][v] = 0;
                graph[v][vertex] = 0;

                /** if vertex not already selected  solve recursively **/
                if (!isPresent(v))
                    solve(v);

                /** restore connection **/
                graph[vertex][v] = 1;
                graph[v][vertex] = 1;

                /** remove path **/
                path[--pathCount] = -1;                    
            }
        }
    }    

    public boolean isPresent(int v) {
        for (int i = 0; i < pathCount - 1; i++)
            if (path[i] == v)
                return true;
        return false;                
    }

    public String display() {
    	StringBuilder ans = new StringBuilder("");
        for (int i = 0; i < V; i++) {
            ans.append(path[i % V]);
            if(i != V-1) {
            	ans.append(";");
            } 
        }

        return ans.toString();
    }  
    

    public static void main (String[] args)  {
        HamiltonianPath hc = new HamiltonianPath();

        int[][] graph =         {   {1, 1 ,0 ,0 ,0 ,0 ,0 ,0 ,0 },
        							{ 1, 1 ,1 ,1 ,1 ,1 ,0 ,0 ,0 },
        							{ 0 ,1, 1, 0, 1, 1, 0, 0, 0 },
        							{0, 1 ,0 ,1, 1 ,0 ,1 ,1 ,0 },
        							{0 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 },
        							{ 0 ,1 ,1 ,0 ,1 ,1 ,0 ,1 ,1 },
        							{ 0 ,0, 0, 1, 1, 0, 1, 1, 0 },
        							{ 0 ,0 ,0 ,1 ,1 ,1 ,1 ,1 ,1 },
        							{ 0 ,0 ,0 ,0 ,1 ,1 ,0 ,1 ,1 }};


        
        
        System.out.println(hc.isHamiltonianPath(graph));
        System.out.println(hc.findHamiltonianPath(graph));        

    }    

}