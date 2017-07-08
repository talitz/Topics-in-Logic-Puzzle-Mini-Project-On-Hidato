package CNF;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GNP {
	public int[] pathArray;
    public int[][] graph;
    public int size;
    
    public GNP(int n,double q) {
    	pathArray= new int[n];
    	graph=new int[n][n];
    	size=n;
    	for(int i=0; i<pathArray.length;i++)
    		pathArray[i]=i;
    	shuffleArray(pathArray);
    	
    	for(int i=0; i<graph.length;i++)
    		for(int j=0; j<graph.length;j++)
    		{
    			double a=Math.random();
    			
    			 if ( a < q)
    			 {
    				 graph[i][j]=1;
    			 } 
    		}
    	
    	for(int i=0; i<pathArray.length-1;i++)
    	{
    		graph[pathArray[i]][pathArray[i+1]]=1;
    	}
    	graph[pathArray[size-1]][size-1]=1;
    	
    	for(int i=0; i<pathArray.length;i++)
    	{
    		graph[i][i]=0;
    	}
    	
	}
    
    
    
    static void shuffleArray(int[] ar)
    {
      // If running on Java 6 or older, use `new Random()` on RHS here
      Random rnd = ThreadLocalRandom.current();
      for (int i = ar.length - 1; i > 0; i--)
      {
        int index = rnd.nextInt(i + 1);
        // Simple swap
        int a = ar[index];
        ar[index] = ar[i];
        ar[i] = a;
      }
    }
   
}
