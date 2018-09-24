package InsertionVersusMerge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This class generates the arrays to be sorted through.
 * 
 * @author Lewis
 *
 */
public class InputGenerator
{
	/**
	 * These 2 methods generates an array with elements in random positions.
	 * Sort through a number of times for the average case.
	 * 
	 * @param arraySize
	 * @return
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
	public static int[] generateRandomArray2(int arraySize)
	{
        //    Create an Array object to store the integer elements
		int[] inputArray = generateAscendArray(arraySize);
		
//        int[] inputArray = new int[arraySize];
//
//        for(int index = 0; index < arraySize; index++){
//            inputArray[index] = index + 1;
//        }

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
