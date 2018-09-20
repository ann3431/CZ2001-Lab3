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
		int numOfKeyComparisons = outputResult.getNumOfKeyComparisons();
		
		long startTime = System.nanoTime(); // start timer
		
		for(int sortedIndex = 1; sortedIndex < inputArray.length; ++sortedIndex)
		{
			for(int unsortedIndex = sortedIndex; unsortedIndex > 0; --unsortedIndex)
			{
				++numOfKeyComparisons; // the if statement below is the key comparison
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
		
		long endTime = System.nanoTime(); // stop timer
		
		outputResult.setNumOfKeyComparisons(numOfKeyComparisons);
		outputResult.setCPUTime(endTime - startTime);
	}
	
	// for tracing
	private static int mergeSortCallCount = 0;
	private static int mergeCallCount = 0;
	/**
	 * Merge sort, but stores merge result in an auxiliary array instead of doing the merging in the original array.
	 * Getting the right output is giving me a headache.
	 * I suspected this has to do with shallow/deep-copying during passing of parameters/assignment of values for arrays.
	 * 
	 * @param inputArray
	 * @param startIndex
	 * @param endIndex
	 * @param outputResult
	 */
	public static void mergeSort(int[] inputArray, int startIndex, int endIndex, StatisticalResults outputResult)
	{
		++mergeSortCallCount;
		System.out.println("MergeSort called " + mergeSortCallCount + " times");
		int numOfKeyComparisons = outputResult.getNumOfKeyComparisons();
		
		long startTime = System.nanoTime(); // start timer
		
		++numOfKeyComparisons;
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
		
		
		IOHandler.printArray(inputArray);
		merge(inputArray,startIndex,endIndex,outputResult);
		
		long endTime = System.nanoTime(); // stop timer
		
		outputResult.setNumOfKeyComparisons(outputResult.getNumOfKeyComparisons() + numOfKeyComparisons);
		outputResult.setCPUTime(endTime - startTime);
	}
	
	private static void merge(int[] inputArray, int startIndex, int endIndex, StatisticalResults outputResult)
	{
		++mergeCallCount;
		IOHandler.printArray(inputArray);
		System.out.println("Merge called " + mergeCallCount + " times");
		
		if(startIndex >= endIndex)
		{
			return;
		}
		else
		{
			//IOHandler.printArray(inputArray);
			int[] auxiliaryArray = new int[endIndex - startIndex + 1];
			System.out.println("Auxiliary array length: " + auxiliaryArray.length);
			int auxiliaryIndex = 0;
			
			int firstHead = startIndex;
			//System.out.println("start index: " + firstHead);
			int secondHead = (startIndex + endIndex)/2;
			//System.out.println("end index: " + endIndex);
			//System.out.println();
			
			//System.out.println(secondHead);
			//System.out.println("Entry 0");
			while((firstHead < (startIndex + endIndex)/2) && secondHead < endIndex)
			{
				System.out.println("First head:" + firstHead + ", first head value: " + inputArray[firstHead]);
				System.out.println("First tail: " + (startIndex + endIndex)/2);
				System.out.println("Second head:" + secondHead + ", second head value: " + inputArray[secondHead]);
				System.out.println("Second tail: " + endIndex);
				System.out.println();
				//System.out.println("Entry 1");
				if(inputArray[firstHead] < inputArray[secondHead])
				{
					
					auxiliaryArray[auxiliaryIndex] = inputArray[firstHead];
					++firstHead;
				}
				else if (inputArray[secondHead] < inputArray[firstHead])
				{
					//System.out.println("Entry 3");
					auxiliaryArray[auxiliaryIndex] = inputArray[secondHead];
					//System.out.println("Input value: " + inputArray[firstHead]);
					//System.out.println("Merged value: " + mergedArray[mergedIndex]);
					++secondHead;
				}
				else
				{
					//System.out.println("Entry 4");
					if((firstHead == ((startIndex + endIndex)/2 - 1)) && secondHead == endIndex)
					{
						//System.out.println("Entry 5");
						break;
					}
						
					//System.out.println("Entry 6");
					auxiliaryArray[auxiliaryIndex] = inputArray[firstHead];
					++firstHead;
					auxiliaryArray[auxiliaryIndex+1] = inputArray[secondHead];
					++secondHead;
				}
				
				++auxiliaryIndex;
				//System.out.println(mergedArray[mergedIndex-1]);
			}
			//IOHandler.printArray(mergedArray);
			
			for(int offset = 0; offset < auxiliaryArray.length; ++offset)
			{
				inputArray[startIndex + offset] = auxiliaryArray[offset];
			}
		}
	}
}
