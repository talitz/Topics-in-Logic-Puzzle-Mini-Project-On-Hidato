package com.trevorstevens.javasat;

import java.util.NoSuchElementException;

import javax.print.DocFlavor.STRING;

import CNF.CNF;

public final class Solver {

    public static int[] solve(CNF cnf) {
       int n=0;
        final Formula formula = new Formula(cnf);
        try {
            while (!formula.validSolution()) {
                if (formula.getCachedClauseSizeZeroResult()) {
                    formula.backTrack();
                } else {
                    formula.forwardTrack();
                }
            }
        } catch (NoSuchElementException e) {
            // Empty Stack print No Solution & Exit
        	n++;
        	if(n>50)
            System.out.println("Unsolvable Solution");
           // System.exit(0);
        	return null;
        }

       // System.out.println("Solvable Solution");
       // formula.printSolution();
      return  formula.printSolution((int)Math.sqrt(cnf.getMyLiterals().size()/2));
       // System.exit(0);
    }
    
    
   
 }

