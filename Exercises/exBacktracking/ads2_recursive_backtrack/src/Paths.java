import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * This class enumerates all possible paths to target node that can made in a graph of nodes labelled 1... cap 
 * where node 1 is source.
 */

public class Paths extends AbstractBacktrack {
	
	HashMap<Integer, ArrayList<Integer>> graph;

	public Paths(int cap, int target, HashMap<Integer, ArrayList<Integer>> graph) {
		this.capacity = cap;
		this.partialSolution = new ArrayList<Integer>();
		for (int i=0; i<cap; i++) {
			partialSolution.add(null);
		}		
		this.data = target;
		this.solutions = new ArrayList<>();
		this.graph = graph;
	}
	
	//Hint: Have you reached the destination?
	@Override
	protected boolean isValidSolution(ArrayList<Integer> partialSolution, int cursor, int data) {
		//TODO
		return false;
	}


	@Override
	protected void handleSolution(ArrayList<Integer> partialSolution) {
		//TODO
	}

	//Hint: Which neighbours have not yet been explored? Perhaps obtain all neighbours from the map and exclude the ones you have visited
	protected ArrayList<Integer> generateCandidates(ArrayList<Integer> partialSolution, int cursor, int data) {
		//TODO
		return null;
	}
	
	@Override
	public void printSolution() {
		//TODO
	}




}
