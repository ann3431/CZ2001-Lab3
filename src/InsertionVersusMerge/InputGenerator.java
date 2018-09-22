package InsertionVersusMerge;
/**
 * This class generates the arrays to be sorted through.
 * 
 * @author Lewis
 *
 */
public class InputGenerator
{
	/**
	 * This method generates an array with elements in random positions.
	 * Sort through a number of times for the average case.
	 * 
	 * @param arraySize
	 * @return
	 */
	public static int[] generateRandomArray(int arraySize)
	{
		int[] inputArray = new int[arraySize];
		// Lewis, add your code for random array here
		
		return inputArray;
	}
	
	/**
	 * This method generates an array sorted in ascending order.
	 * Also the best case.
	 * 
	 * @param arraySize
	 * @return 
	 */
	public static int[] generateAscendArray(int arraySize)
	{
		int[] inputArray = new int[arraySize];
		
		for(int index = 0; index < arraySize; ++index)
		{
			inputArray[index] = index+1;
		}
		
		return inputArray;
	}
	
	/**
	 * This method generates an array sorted in descending order.
	 * Also the worst case.
	 * 
	 * @param arraySize
	 * @return 
	 */
	public static int[] generateDescendArray(int arraySize)
	{
		int[] inputArray = new int[arraySize];
		
		for(int index = 0; index < arraySize; ++index)
		{
			inputArray[index] = arraySize - index;
		}
		
		return inputArray;
	}
}
