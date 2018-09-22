package InsertionVersusMerge;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This class actually runs the program and allow us to compare the performance
 * of Insertion Sort vs Merge Sort
 * @author Annie, Jason, Kenrick, Lewis, Stephen
 *
 */
public class InsertionVersusMerge
{
	/**
	 * this values are declared constants for easy manipulation
	 * can be changed to console input later on
	 */
	private static final int MIN_ARRAY_SIZE = 1000;
	private static final int MAX_ARRAY_SIZE = 100_000;
	private static final int STEP_SIZE = 1000;
	
	/**
	 * We need to compare between insertion sort and merge sort for arrays of different types and sizes.
	 * So we generate 
	 * @param args
	 */
	public static void main(String[] args) throws Exception // dealing with Exceptions is troublesome
	{
		XSSFWorkbook[] resultsWorkbookArray = new XSSFWorkbook[6];
		for(int index = 0; index < resultsWorkbookArray.length; ++index)
		{
			resultsWorkbookArray[index] = IOHandler.generateNewXSSFWorkbook();
		}
		/**
		 * resultsWorkbookArray[0] holds the results of sorting through all randomly-ordered arrays using insertion sort
		 * resultsWorkbookArray[1] holds the results of sorting through all ascending arrays using insertion sort
		 * resultsWorkbookArray[2] holds the results of sorting through all descending arrays using insertion sort
		 * 
		 * resultsWorkbookArray[3] holds the results of sorting through all randomly-ordered arrays using merge sort
		 * resultsWorkbookArray[4] holds the results of sorting through all ascending arrays using merge sort
		 * resultsWorkbookArray[5] holds the results of sorting through all descending arrays using merge sort
		 */
		
		// outer loop varies array sizes
		for(int arraySize = MIN_ARRAY_SIZE; arraySize <= MAX_ARRAY_SIZE; arraySize += STEP_SIZE)
		{
			StatisticalResults[] sortingResultsArray = new StatisticalResults[6]; // 6 combinations of sorting results
			
			for(int index = 0; index < sortingResultsArray.length; ++index)
			{
				sortingResultsArray[index] = new StatisticalResults(arraySize);
			}
			/**
			 * sortingResultsArray[0] holds the results of sorting through a randomly-ordered array using insertion sort
			 * sortingResultsArray[1] holds the results of sorting through a ascending array using insertion sort
			 * sortingResultsArray[2] holds the results of sorting through a descending array using insertion sort
			 * 
			 * sortingResultsArray[3] holds the results of sorting through a randomly-ordered array using merge sort
			 * sortingResultsArray[4] holds the results of sorting through a ascending array using merge sort
			 * sortingResultsArray[5] holds the results of sorting through a descending array using merge sort
			 */
			
			// to-do: place an inner loop here to get multiple instances of results to get the average for random order		
//			/int[] randomArray = InputGenerator.generateRandomArray(arraySize);
			
			int[] insertAscendArray = InputGenerator.generateAscendArray(arraySize);
			int[] insertDescendArray = InputGenerator.generateDescendArray(arraySize);
			
			int[] mergeAscendArray = InputGenerator.generateAscendArray(arraySize);
			int[] mergeDescendArray = InputGenerator.generateDescendArray(arraySize);

			// the actual sorting occurs here
			Sorter.insertionSort(insertAscendArray, sortingResultsArray[1]);
			Sorter.insertionSort(insertDescendArray, sortingResultsArray[2]);
			
			Sorter.mergeSort(mergeAscendArray, 0, arraySize-1, sortingResultsArray[4]);
			Sorter.mergeSort(mergeDescendArray, 0, arraySize-1, sortingResultsArray[5]);
			
//			System.out.println("Insertion sort (ascending): " + sortingResultsArray[1]);
//			System.out.println("Insertion sort (descending): " + sortingResultsArray[2]);
//			System.out.println();
//			
//			System.out.println("Merge sort (ascending): " + sortingResultsArray[4]);
//			System.out.println("Merge sort (descending): " + sortingResultsArray[5]);
//			System.out.println();
			
			// transfer of sorting results to Excel file
			for(int index = 0; index < sortingResultsArray.length; ++ index)
			{
				IOHandler.transferResultsToWorkbook(sortingResultsArray[index], resultsWorkbookArray[index]);
			}
			
			if(arraySize % 10_000 == 0)
				System.out.println("Progress: " + arraySize/10_000 + " of " + MAX_ARRAY_SIZE/10_000);
		}
		
		
		IOHandler.closeWorkbook(resultsWorkbookArray[1],"Output Data\\Insertion Sort on Ascending Array Results.xlsx");
		IOHandler.closeWorkbook(resultsWorkbookArray[2],"Output Data\\Insertion Sort on Descending Array Results.xlsx");
		
		IOHandler.closeWorkbook(resultsWorkbookArray[4],"Output Data\\Merge Sort on Ascending Array Results.xlsx");
		IOHandler.closeWorkbook(resultsWorkbookArray[5],"Output Data\\Merge Sort on Descending Array Results.xlsx");
		
		System.out.println("Done");
	}

}
