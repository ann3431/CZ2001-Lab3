
public class InputGenerator
{
	public static int[] generateRandomArray(int arraySize)
	{
		int[] inputArray = new int[arraySize];
		
		
		return inputArray;
	}
	
	public static int[] generateAscendArray(int arraySize)
	{
		int[] inputArray = new int[arraySize];
		
		for(int index = 0; index < arraySize; ++index)
		{
			inputArray[index] = index+1;
		}
		
		return inputArray;
	}
	
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
