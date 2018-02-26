import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * This class enumerates all possible subsets that can be formed from a given set of consecutive integers 1...cap
 */


public class Subsets extends AbstractBacktrack {

	public Subsets(int cap ) {
		this.partialSolution = new ArrayList<Integer>();
		for (int i=0; i<cap; i++) {
			partialSolution.add(null);
		}
		this.capacity = cap;
		this.data = capacity;
		this.solutions = new ArrayList<>();
	}
	
	//Hint: have all items been assigned membership status? Have you reached the edge of the array?
	@Override
	protected boolean isValidSolution(ArrayList<Integer> partialSolution, int cursor, int data) {
		//TODO
		return false;
	}

	//Collect in solutions-array to be printed out later (or print immediately)
	@Override
	protected void handleSolution(ArrayList<Integer> partialSolution) {
		//TODO
	}
	
	//Hint: It's Boolean
	@Override
	protected ArrayList<Integer> generateCandidates(ArrayList<Integer> partialSolution, int cursor, int data) {
		//TODO
		return null;

	}
	
	@Override
	public void printSolution() {
		//TODO
	}





}
