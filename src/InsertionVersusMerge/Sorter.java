package InsertionVersusMerge;

//import java.math.BigInteger;
//import java.util.ArrayList;

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
	public static void insertionSort(long[] inputArray, StatisticalResults outputResult)
	{
		long temp; // holds value temporarily during a swap
		
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
	public static void mergeSort(long[] inputArray, int startIndex, int endIndex, StatisticalResults outputResult)
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
	
	private static void merge(long[] inputArray, int startIndex, int endIndex, StatisticalResults outputResult)
	{
		
		if(startIndex >= endIndex)
		{
			return;
		}
		else
		{
			long[] auxiliaryArray = new long[endIndex - startIndex + 1];

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
	public static void quickAndInsertionSort(StatisticalResults[] randomResultsArray, int startIndex, int endIndex)
	{	
		if((endIndex - startIndex) <= 9) // array is small, do insertion sort
		{
			long[] tempArray = new long[endIndex - startIndex + 1];
			StatisticalResults dummyResult = new StatisticalResults(endIndex - startIndex);
			
			for(int index = startIndex; index <= endIndex; ++index)
			{
				tempArray[index - startIndex] = randomResultsArray[index].getCPUTime();
			}
			
			insertionSort(tempArray,dummyResult);
			
			for(int index = startIndex; index <= endIndex; ++index)
			{
				randomResultsArray[index].setCPUTime(tempArray[index - startIndex]);
			}
		}
		else
		{
			int pivotPosition = partition(randomResultsArray,startIndex,endIndex);
			
			quickAndInsertionSort(randomResultsArray,startIndex,pivotPosition-1);
			quickAndInsertionSort(randomResultsArray,pivotPosition+1,endIndex);
		}
	}
	
	private static int partition(StatisticalResults[] randomResultsArray, int startIndex, int endIndex)
	{
		int pivotIndex = findMedianIndexAmong3Values(randomResultsArray, startIndex, endIndex);
		
		// place pivot value at the start of the array to avoid said pivot when iterating through array
		long temp = randomResultsArray[startIndex].getCPUTime();
		
		randomResultsArray[startIndex].setCPUTime(
				randomResultsArray[pivotIndex].getCPUTime());
		
		randomResultsArray[pivotIndex].setCPUTime(temp);
		
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
		
		// place pivot in the right position
		// visually: [keys smaller than pivot][pivot][keys larger than pivot]
		temp = randomResultsArray[startIndex].getCPUTime();
		
		randomResultsArray[startIndex].setCPUTime(
				randomResultsArray[lastPositionSmallerThanPivot].getCPUTime());
		
		randomResultsArray[lastPositionSmallerThanPivot].setCPUTime(temp);
		
		return lastPositionSmallerThanPivot;
	}
	
	/**
	 * This method selects the the median of values of the start, middle and end index of an array as it pivot.
	 * It is used for selecting the pivot to avoid worst case [O(n^2)]
	 * 
	 * @param valueA
	 * @param valueB
	 * @param valueC
	 * @return
	 */
	private static int findMedianIndexAmong3Values(StatisticalResults[] randomResultsArray, int startIndex, int endIndex)
	{
		int middleIndex = (startIndex + endIndex)/2;
		
		long startValue = randomResultsArray[startIndex].getCPUTime();
		long middleValue = randomResultsArray[middleIndex].getCPUTime();
		long endValue = randomResultsArray[endIndex].getCPUTime();
		
		if(startValue <= middleValue)
		{
			if(middleValue <= endValue)
			{
				return middleIndex;
			}
			else
			{
				if(startValue <= endValue)
				{
					return endIndex;
				}
				else
				{
					return startIndex;
				}
			}
		}
		else
		{
			if(endValue <= middleValue)
			{
				return middleIndex;
			}
			else
			{
				if(endValue <= startValue)
				{
					return endIndex;
				}
				else
				{
					return startIndex;
				}
			}
		}
	}
	
	// i should write a swap() method
}


