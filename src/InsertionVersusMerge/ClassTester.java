package InsertionVersusMerge;
/**
 * This class is used to make sure other classes and their methods are working properly.
 * last-updated: 2018-10-18
 * 
 * @author Jason
 *
 */
public class ClassTester
{
	/**
	 * Classes and methods are tested here before being used in the main method of InsertionVersusMerge
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		int arraySize = 20;
		
		StatisticalResults insertResult = new StatisticalResults(arraySize);
		long[] insertTestArray = InputGenerator.generateRandomArray2(arraySize);
		
		System.out.println("Before sorting: ");
		IOHandler.printArray(insertTestArray);
		
		Sorter.insertionSort(insertTestArray, insertResult);
		//Sorter.mergeSort(testArray, 0, testArray.length-1, outputResult);
		
		System.out.println("After sorting: ");
		IOHandler.printArray(insertTestArray);
		
		System.out.println(insertResult);
		System.out.println();
		
		StatisticalResults mergeResult = new StatisticalResults(arraySize);
		long[] mergeTestArray = InputGenerator.generateRandomArray2(arraySize);
		
		System.out.println("Before sorting: ");
		IOHandler.printArray(mergeTestArray);
		
		//Sorter.insertionSort(testArray, outputResult);
		Sorter.mergeSort(mergeTestArray, 0, mergeTestArray.length-1, mergeResult);
		
		System.out.println("After sorting: ");
		IOHandler.printArray(mergeTestArray);
		
		System.out.println(mergeResult);
		System.out.println();
	}

}
