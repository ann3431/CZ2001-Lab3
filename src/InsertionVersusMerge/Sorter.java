package InsertionVersusMerge;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * This class does the sorting.
 * 
 * @author Jason
 *
 */
public class Sorter
{
	/**
	 * Insertion sort as shown in lecture.
	 * 
	 * @param inputArray
	 * @param outputResult
	 */
	public static void insertionSort(int[] inputArray, StatisticalResults outputResult)
	{
		int temp; // holds value temporarily during a swap
		
		long startTime = System.nanoTime();
		
		for(int sortedIndex = 1; sortedIndex < inputArray.length; ++sortedIndex)
		{
			for(int unsortedIndex = sortedIndex; unsortedIndex > 0; --unsortedIndex)
			{
				outputResult.incrementNumOfKeyCmp(); // the if statement below is the key comparison
				
				if(inputArray[unsortedIndex-1]>inputArray[unsortedIndex])
				{
					temp = inputArray[unsortedIndex-1];
					inputArray[unsortedIndex-1] = inputArray[unsortedIndex];
					inputArray[unsortedIndex] = temp;
				}
				else
					break;
			}
		}
		
		long endTime = System.nanoTime();
		
		outputResult.setCPUTime(endTime - startTime);
	}
	
	/**
	 * Merge sort, but stores merge result in an auxiliary array instead of doing the merging in the original array.
	 * 
	 * @param inputArray
	 * @param startIndex
	 * @param endIndex
	 * @param outputResult
	 */
	public static void mergeSort(int[] inputArray, int startIndex, int endIndex, StatisticalResults outputResult)
	{
		long startTime = System.nanoTime();
		
		if(startIndex >= endIndex)
		{
			return;
		}
		else
		{
			int midIndex = (startIndex + endIndex)/2;
			
			mergeSort(inputArray,startIndex,midIndex,outputResult);
			mergeSort(inputArray,midIndex+1,endIndex,outputResult);
		}
		
		merge(inputArray,startIndex,endIndex,outputResult);
		
		long endTime = System.nanoTime();
		
		outputResult.setCPUTime(endTime - startTime);
	}
	
	private static void merge(int[] inputArray, int startIndex, int endIndex, StatisticalResults outputResult)
	{
		
		if(startIndex >= endIndex)
		{
			return;
		}
		else
		{
			int[] auxiliaryArray = new int[endIndex - startIndex + 1];

			int auxiliaryIndex = 0;
			
			int firstHead = startIndex;
			int firstTail = (startIndex + endIndex)/2;
			
			int secondHead = firstTail + 1;
			int secondTail = endIndex;

			while(firstHead <= firstTail && secondHead <= secondTail)
			{
				outputResult.incrementNumOfKeyCmp(); // if statement below is the key comparison
				
				if(inputArray[firstHead] < inputArray[secondHead])
				{
					auxiliaryArray[auxiliaryIndex] = inputArray[firstHead];
					++firstHead;
				}
				else if (inputArray[secondHead] < inputArray[firstHead])
				{
					auxiliaryArray[auxiliaryIndex] = inputArray[secondHead];
					++secondHead;
				}
				else
				{
					// both sublists reach the end
					if(firstHead == firstTail && secondHead == secondTail)
					{
						break;
					}
					
					auxiliaryArray[auxiliaryIndex] = inputArray[firstHead];
					++firstHead;

					++auxiliaryIndex;
					
					auxiliaryArray[auxiliaryIndex] = inputArray[secondHead];
					++secondHead;
				}
				
				++auxiliaryIndex;
			}
			
			/**
			 * if there are any remaining elements to be merged, merge them
			 */
			if(firstHead <= firstTail)
			{
				for(int index = firstHead; index <= firstTail; ++index)
				{
					auxiliaryArray[auxiliaryIndex] = inputArray[index];
					++auxiliaryIndex;
				}
			}
			if(secondHead <= secondTail)
			{
				for(int index = secondHead; index <= secondTail; ++index)
				{
					auxiliaryArray[auxiliaryIndex] = inputArray[index];
					++auxiliaryIndex;
				}
			}
			
			// transfer values in auxiliary array (merge result) to the original array
			for(int offset = 0; offset < auxiliaryArray.length; ++offset)
			{
				inputArray[startIndex + offset] = auxiliaryArray[offset];
			}
		}
	}
	
	/**
	 * This method is used for sorting results of sorting through randomly-ordered array.
	 * 
	 * @param randomResultsArray
	 * @param startIndex
	 * @param endIndex
	 */
	public static void quickSort(StatisticalResults[] randomResultsArray, int startIndex, int endIndex)
	{	
		if(startIndex >= endIndex)
			return;
		else
		{
			int pivotPosition = partition(randomResultsArray,startIndex,endIndex);
			
			quickSort(randomResultsArray,startIndex,pivotPosition-1);
			quickSort(randomResultsArray,pivotPosition+1,endIndex);
		}
	}
	
	private static int partition(StatisticalResults[] randomResultsArray, int startIndex, int endIndex)
	{
		int middleIndex = (startIndex + endIndex)/2;
		
		// swap(low,mid) in lecture notes
		long temp = randomResultsArray[startIndex].getCPUTime();
		randomResultsArray[startIndex].setCPUTime(randomResultsArray[middleIndex].getCPUTime());
		randomResultsArray[middleIndex].setCPUTime(temp);
		
		long pivotValue = randomResultsArray[startIndex].getCPUTime();
		int lastPositionSmallerThanPivot = startIndex;
		
		for(int index = startIndex + 1; index <= endIndex; ++index)
		{
			if(randomResultsArray[index].getCPUTime() < pivotValue)
			{
				++lastPositionSmallerThanPivot; // bring the increment out in case of context switch
				
				// swap(last_small,i) in lecture notes (excluding increment of last_small)
				temp = randomResultsArray[lastPositionSmallerThanPivot].getCPUTime();
				
				randomResultsArray[lastPositionSmallerThanPivot].setCPUTime(randomResultsArray[index].getCPUTime());
				
				randomResultsArray[index].setCPUTime(temp);
				
			}
		}
		
		// swap(low,last_small) in lecture notes
		temp = randomResultsArray[startIndex].getCPUTime();
		
		randomResultsArray[startIndex].setCPUTime(randomResultsArray[lastPositionSmallerThanPivot].getCPUTime());
		
		randomResultsArray[lastPositionSmallerThanPivot].setCPUTime(temp);
		
		return lastPositionSmallerThanPivot;
	}
	
	// i should write a swap() method
}
