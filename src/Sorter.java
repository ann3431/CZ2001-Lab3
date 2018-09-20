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
	
	/**
	 * Merge sort, but with auxiliary array instead of doing the merging in the original array.
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
		
		merge(inputArray,startIndex,endIndex,outputResult);
		
		long endTime = System.nanoTime(); // stop timer
		
		outputResult.setNumOfKeyComparisons(outputResult.getNumOfKeyComparisons() + numOfKeyComparisons);
		outputResult.setCPUTime(endTime - startTime);
	}
	
	private static void merge(int[] inputArray, int startIndex, int endIndex, StatisticalResults outputResult)
	{
		//IOHandler.printArray(inputArray);
		
		if(startIndex >= endIndex)
		{
			return;
		}
		else
		{
			//IOHandler.printArray(inputArray);
			int[] mergedArray = new int[endIndex - startIndex + 1];
			int mergedIndex = 0;
			
			int firstHead = startIndex;
			//System.out.println("start index: " + firstHead);
			int secondHead = (startIndex + endIndex)/2;
			//System.out.println("end index: " + endIndex);
			//System.out.println();
			
			//System.out.println(secondHead);
			System.out.println("Entry 0");
			while((firstHead < (startIndex + endIndex)/2) && secondHead < endIndex)
			{
				System.out.println("Entry 1");
				if(inputArray[firstHead] < inputArray[secondHead])
				{
					System.out.println("Entry 2");
					mergedArray[mergedIndex] = inputArray[firstHead];
					++firstHead;
				}
				else if (inputArray[secondHead] < inputArray[firstHead])
				{
					System.out.println("Entry 3");
					mergedArray[mergedIndex] = inputArray[secondHead];
					//System.out.println("Input value: " + inputArray[firstHead]);
					//System.out.println("Merged value: " + mergedArray[mergedIndex]);
					++secondHead;
				}
				else
				{
					System.out.println("Entry 4");
					if((firstHead == ((startIndex + endIndex)/2 - 1)) && secondHead == endIndex)
					{
						// System.out.println("Here?");
						break;
					}
						
					
					mergedArray[mergedIndex] = inputArray[firstHead];
					++firstHead;
					mergedArray[mergedIndex+1] = inputArray[secondHead];
					++secondHead;
				}
				
				++mergedIndex;
				//System.out.println(mergedArray[mergedIndex-1]);
			}
			//IOHandler.printArray(mergedArray);
			
			for(int offset = 0; offset < mergedArray.length; ++offset)
			{
				inputArray[startIndex + offset] = mergedArray[offset];
			}
		}
	}
}
