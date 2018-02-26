import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
	
	public static void main(String args[])
	{
		
		String function = args[0];

		int datum;
		switch(function) {
		case "subsets":
			datum = Integer.parseInt(args[1]);
			Subsets s = new Subsets(datum);
			s.startBacktrack();
			s.printSolution();
			break;
		case "permutations":
			datum = Integer.parseInt(args[1]);
			Permutations p = new Permutations(datum);
			p.startBacktrack();
			p.printSolution();
			p.nrOfSolutions();
			break;
		case "paths":
			
			HashMap<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
			
			//Graph definition, hardcoded... *embarrassed*
			graph.put(1, new ArrayList<Integer>());
			graph.get(1).add(6); graph.get(1).add(3); graph.get(1).add(2); 
			graph.put(2, new ArrayList<Integer>());
			graph.get(2).add(1); graph.get(2).add(4); 		
			graph.put(3, new ArrayList<Integer>());
			graph.get(3).add(1); graph.get(3).add(4); graph.get(3).add(5);		
			graph.put(4, new ArrayList<Integer>());
			graph.get(4).add(2); graph.get(4).add(5); graph.get(4).add(3); 
			graph.put(5, new ArrayList<Integer>());
			graph.get(5).add(6); graph.get(5).add(3); graph.get(5).add(4);
			graph.put(6, new ArrayList<Integer>());
			graph.get(6).add(1); graph.get(6).add(5); 
			
			int target = Integer.parseInt(args[1]);
			
			Paths pt = new Paths(6,target,graph);
			pt.startBacktrack();
			pt.printSolution();
			pt.nrOfSolutions();
			break;
			
		default:
			break;
			
		}
		
	}
	
	


}
