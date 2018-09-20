/**
 * This class actually runs the program and allow us to compare the performance
 * of Insertion Sort vs Merge Sort
 * @author Jason
 *
 */
public class InsertionVersusMerge
{
	/**
	 * this values are declared constants for easy manipulation
	 * can be changed to console input later on
	 */
	private static final int MIN_ARRAY_SIZE = 1000;
	private static final int MAX_ARRAY_SIZE = 1000_000;
	private static final int STEP_SIZE = 1000;
	
	/**
	 * We need to compare between insertion sort and merge sort for arrays of different types and sizes.
	 * So we generate 
	 * @param args
	 */
	public static void main(String[] args)
	{
		
		// outer loop varies array sizes
		for(int arraySize = MIN_ARRAY_SIZE; arraySize <= MAX_ARRAY_SIZE; arraySize += STEP_SIZE)
		{
			StatisticalResults randomResults =   new StatisticalResults(arraySize);
			StatisticalResults ascendResults =   new StatisticalResults(arraySize);
			StatisticalResults descendResults =   new StatisticalResults(arraySize);
			
			// to-do: place an inner loop here to get multiple instances of results to get the average
			int[] randomArray = InputGenerator.generateRandomArray(arraySize);
			int[] ascendArray = InputGenerator.generateAscendArray(arraySize);
			int[] descendArray = InputGenerator.generateDescendArray(arraySize);
			
			
		}
	}

}
