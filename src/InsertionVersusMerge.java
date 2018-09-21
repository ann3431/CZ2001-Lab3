/**
 * This class actually runs the program and allow us to compare the performance
 * of Insertion Sort vs Merge Sort
 * @author Annie, Jason, Kenrick, Lewis, Stephen
 *
 */
public class InsertionVersusMerge
{
	/**
	 * this values are declared constants for easy manipulation
	 * can be changed to console input later on
	 */
	private static final int MIN_ARRAY_SIZE = 1000;
	private static final int MAX_ARRAY_SIZE = 100_000;
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
//			StatisticalResults randomResults =   new StatisticalResults(arraySize);
//			
//			StatisticalResults insertAscendResults =   new StatisticalResults(arraySize);
			StatisticalResults insertDescendResults =   new StatisticalResults(arraySize);
//			
//			StatisticalResults mergeAscendResults =   new StatisticalResults(arraySize);
//			StatisticalResults mergeDescendResults =   new StatisticalResults(arraySize);
			
			// to-do: place an inner loop here to get multiple instances of results to get the average
			
//			/int[] randomArray = InputGenerator.generateRandomArray(arraySize);
//			
//			/int[] insertAscendArray = InputGenerator.generateAscendArray(arraySize);
			int[] insertDescendArray = InputGenerator.generateDescendArray(arraySize);
//			
//			int[] mergeAscendArray = InputGenerator.generateAscendArray(arraySize);
//			int[] mergeDescendArray = InputGenerator.generateDescendArray(arraySize);
			
			// counterbalancing to remove order effect

			Sorter.insertionSort(insertDescendArray, insertDescendResults);
//			Sorter.insertionSort(insertAscendArray, insertAscendResults);
//				
//			Sorter.mergeSort(mergeDescendArray, 0, arraySize-1, mergeDescendResults);
//			Sorter.mergeSort(mergeAscendArray, 0, arraySize-1, mergeAscendResults);
			
			System.out.println("Insertion sort (descending): " + insertDescendResults);
//			System.out.println("Merge sort (descending): " + mergeDescendResults);
//			System.out.println();
//			System.out.println("Insertion sort (ascending): " + insertAscendResults);
//			System.out.println("Merge sort (ascending): " + mergeAscendResults);
			System.out.println();
		}
	}

}
