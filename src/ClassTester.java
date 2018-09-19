/**
 * This class is used to make sure other classes and their methods are working properly.
 * 
 * @author Jason
 *
 */
public class ClassTester
{

	public static void main(String[] args)
	{
		int arraySize = 1000;
		
		StatisticalResults outputResult = new StatisticalResults(arraySize);
		int[] testArray = InputGenerator.generateDescendArray(arraySize);
		
		System.out.println("Before sorting: ");
		IOHandler.printArray(testArray);
		
		//Sorter.insertionSort(testArray, outputResult);
		Sorter.mergeSort(testArray, 0, testArray.length-1, outputResult);
		
		System.out.println("After sorting: ");
		IOHandler.printArray(testArray);
		
		System.out.println(outputResult);
	}

}
