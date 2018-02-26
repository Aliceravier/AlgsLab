import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractBacktrack {
	
	ArrayList<Integer> partialSolution;
	int capacity;
	int data; //variable holding data useful for passing constraint into candidate function, e.g. target node in paths 
	ArrayList<ArrayList<Integer>> solutions; 
	
	//Kick off the backtracking on the empty partialSolution array
	public void startBacktrack() {
		backtrack(this.partialSolution, -1, this.data);

	}

	private void backtrack(ArrayList<Integer> partialSolution, int cursor, int data) {
		
		//If the partial solution is valid, store in solutions
		if (isValidSolution( partialSolution, cursor, data)) {
			handleSolution(partialSolution);
			
		//Else... 
		}else {
			
			//advance cursor
			cursor++;

			//generate candidates for the new slot
			ArrayList<Integer> candidates = generateCandidates( partialSolution, cursor, data);

			//iterate through new partial solutions, invoking backtrack recursively on each
			for (int i=0; i<candidates.size(); i++){
				partialSolution.set(cursor, candidates.get(i));
				backtrack(partialSolution, cursor, data);
				
			}
		}
	}
	
	
	//Tests if the content of the partial solution array until the cursor point forms a complete solution
	protected abstract boolean isValidSolution( ArrayList<Integer> partialSolution, int cursor, int data); 
	
	//Stores partialSolution array in solutions
	protected abstract void handleSolution(ArrayList<Integer> partialSolution);
	
	//Returns Integer array of promising candidates for the current slot (note: data-argument not necessarily useful in this example)
	protected abstract ArrayList<Integer> generateCandidates(ArrayList<Integer> partialSolution, int cursor, int data);
	
	//Print out the contents of solutions using your format of choice
	public abstract void printSolution();

	
	public void nrOfSolutions() {
		System.out.println( solutions.size() );
	}
}
