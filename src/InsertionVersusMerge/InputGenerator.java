package InsertionVersusMerge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This class generates the input arrays to be sorted through.
 * last-updated: 2018-10-18
 * 
 * @author Lewis
 *
 */
public class InputGenerator
{
	/**
	 * These 2 methods each generate an array with elements in random positions.
	 * 
	 * @param arraySize
	 * @return array of random order
	 */
	// Option 1: Using ArrayList object to store integer elements
	public static ArrayList<Integer> generateRandomArray1(int arraySize)
	{
		ArrayList<Integer> inputArrayList = new ArrayList<Integer>(arraySize);
		
		for (int index = 0; index < arraySize; index++)
		{
			inputArrayList.add(index+1);
		}
		
		Collections.shuffle(inputArrayList);
		
		return inputArrayList;
	}
	
	// Option 2: Using normal Array object to store integer elements
	public static long[] generateRandomArray2(int arraySize)
	{
        //    Create an Array object to store the integer elements
		long[] inputArray = generateAscendArray(arraySize);

        //    Generate randomly ordered array by implementing Fisher-Yates shuffle algorithm
        int index;
        Random randomNumGenerator = new Random();

        for(int i = inputArray.length - 1; i > 0; --i)
        {
            index = randomNumGenerator.nextInt(i + 1);
            
            if(index != i)
            {
                inputArray[index] ^= inputArray[i];
                inputArray[i] ^= inputArray[index];
                inputArray[index] ^= inputArray[i];
            }

        }
        
        return inputArray;
    }
	
	
	/**
	 * This method generates an array sorted in ascending order.
	 * 
	 * @param arraySize
	 * @return array of ascending order
	 */
	public static long[] generateAscendArray(int arraySize)
	{
		long[] inputArray = new long[arraySize];
		
		for(int index = 0; index < arraySize; ++index)
		{
			inputArray[index] = index+1;
		}
		
		return inputArray;
	}
	
	/**
	 * This method generates an array sorted in descending order.
	 * 
	 * @param arraySize
	 * @return array of descending order
	 */
	public static long[] generateDescendArray(int arraySize)
	{
		long[] inputArray = new long[arraySize];
		
		for(int index = 0; index < arraySize; ++index)
		{
			inputArray[index] = arraySize - index;
		}
		
		return inputArray;
	}
}
