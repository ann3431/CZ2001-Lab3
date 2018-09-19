
public class Sorter
{
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
		if(startIndex >= endIndex)
		{
			return;
		}
		else
		{
			int[] mergedArray = new int[endIndex - startIndex + 1];
			int mergedIndex = 0;
			int firstHead = startIndex;
			int secondHead = (startIndex + endIndex)/2;
			
			while(firstHead < (startIndex + endIndex)/2 && secondHead < endIndex)
			{
				if(inputArray[firstHead] < inputArray[secondHead])
				{
					mergedArray[mergedIndex] = inputArray[firstHead];
					++firstHead;
				}
				else if (inputArray[secondHead] < inputArray[firstHead])
				{
					mergedArray[mergedIndex] = inputArray[secondHead];
					++secondHead;
				}
				else
				{
					if(firstHead == (startIndex + endIndex)/2 - 1 && secondHead == endIndex)
						break;
					
					mergedArray[mergedIndex] = inputArray[firstHead];
					++firstHead;
					mergedArray[mergedIndex+1] = inputArray[secondHead];
					++secondHead;
				}
				
				++mergedIndex;
			}
			
			for(int offset = 0; offset < mergedArray.length; ++offset)
			{
				inputArray[startIndex + offset] = mergedArray[offset];
			}
		}
	}
}
