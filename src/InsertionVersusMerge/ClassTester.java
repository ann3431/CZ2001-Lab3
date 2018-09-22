package InsertionVersusMerge;
/**
 * This class is used to make sure other classes and their methods are working properly.
 * 
 * @author Jason
 *
 */
public class ClassTester
{
	/**
	 * This stuff here before adding them to the main program so you won't break things.
	 * @param args
	 */
	public static void main(String[] args)
	{
		int arraySize = 20;
		
		StatisticalResults insertResult = new StatisticalResults(arraySize);
		int[] insertTestArray = InputGenerator.generateDescendArray(arraySize);
		
		System.out.println("Before sorting: ");
		IOHandler.printArray(insertTestArray);
		
		Sorter.insertionSort(insertTestArray, insertResult);
		//Sorter.mergeSort(testArray, 0, testArray.length-1, outputResult);
		
		System.out.println("After sorting: ");
		IOHandler.printArray(insertTestArray);
		
		System.out.println(insertResult);
		System.out.println();
		
		StatisticalResults mergeResult = new StatisticalResults(arraySize);
		int[] mergeTestArray = InputGenerator.generateDescendArray(arraySize);
		
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
