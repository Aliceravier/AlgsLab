import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * This class enumerates all possible arrangements that can be formed from a given set of consecutive integers 1...cap
 */

public class Permutations extends AbstractBacktrack {
	
	public Permutations(int cap) {
		this.capacity = cap;
		this.data = cap;
		this.partialSolution = new ArrayList<Integer>();
		for (int i=0; i<cap; i++) {
			partialSolution.add(null);
		}
		this.solutions = new ArrayList<>();
	}

	//Hint: Have all slots been assigned an object? Have you reached the edge of the array?
	@Override
	protected boolean isValidSolution(ArrayList<Integer> partialSolution, int cursor, int data) {
		//TODO
		return false;
	}

	@Override
	protected void handleSolution(ArrayList<Integer> partialSolution) {
		//TODO
	}

	//Hint: Which objects have not yet been assigned?
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
