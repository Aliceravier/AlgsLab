import java.util.*;

public class Sort {

    private static void bubbleSort(int[] A){}

    private static void radixSort(int[] A){}

    private static void pigeonholeSort(int[] A){}

    public static void main(String[] args){
	String command = args[0];                   // algorithm name
	int n          = Integer.parseInt(args[1]); // number of numbers
	int m          = Integer.parseInt(args[2]); // biggest number in data
	Random gen     = new Random();
	int[] A        = new int[n];
	for (int i=0;i<n;i++) A[i] = gen.nextInt(m);
	if (args.length > 3){
	    for (int x : A) System.out.print(x +" ");
	    System.out.println();
	}
	long startTimeMs = System.currentTimeMillis();
	if (command.equals("arrays")) Arrays.sort(A);
	else if (command.equals("bubble")) bubbleSort(A);
	else if (command.equals("radix")) radixSort(A);
	else if (command.equals("pigeon")) pigeonholeSort(A);
	else if (command.equals("jsort")) Arrays.sort(A);
	else {System.out.println("invalid command"); return;}
	long elapsedTimeMs = System.currentTimeMillis() - startTimeMs;
	System.out.println("time (ms): "+ elapsedTimeMs);
	if (args.length > 3){
	    for (int x : A) System.out.print(x +" ");
	    System.out.println();
	}
    }
}
//
// See readme.txt
//
// to bubble sort 20 numbers, largest is 99
//
// > java Sort bubble 20 100
//
