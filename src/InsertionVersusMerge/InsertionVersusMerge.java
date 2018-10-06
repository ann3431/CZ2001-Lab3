package InsertionVersusMerge;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This class actually runs the program and allow us to compare the performance of Insertion Sort vs Merge Sort
 * 
 * @author Annie, Jason, Kenrick, Lewis, Stephen
 *
 */
public class InsertionVersusMerge
{
	/**
	 * these values are declared constants for easy manipulation
	 * can be changed to console input later on
	 */
	private static final int MIN_ARRAY_SIZE = 1000;
	private static final int MAX_ARRAY_SIZE = 100_000;
	private static final int STEP_SIZE = 1000;
	
	private static final int NUM_OF_TESTINGS_RANDOM = 100;
	
	/**
	 * We need to compare between insertion sort and merge sort for arrays of different types and sizes.
	 * So we generate 
	 * @param args
	 */
	public static void main(String[] args) throws Exception // dealing with Exceptions is troublesome
	{
		long programStartTime = System.currentTimeMillis();
		
		/**
		 * resultsWorkbookArray[0] stores the results of sorting through all randomly-ordered arrays using insertion sort
		 * resultsWorkbookArray[1] stores the results of sorting through all ascending arrays using insertion sort
		 * resultsWorkbookArray[2] stores the results of sorting through all descending arrays using insertion sort
		 * 
		 * resultsWorkbookArray[3] stores the results of sorting through all randomly-ordered arrays using merge sort
		 * resultsWorkbookArray[4] stores the results of sorting through all ascending arrays using merge sort
		 * resultsWorkbookArray[5] stores the results of sorting through all descending arrays using merge sort
		 */
		// create XSSF Workbooks to store results in correct format before writing to files
		XSSFWorkbook[] resultsWorkbookArray = new XSSFWorkbook[6];
		for (int index = 0; index < resultsWorkbookArray.length; ++index)
		{
			resultsWorkbookArray[index] = IOHandler.generateNewXSSFWorkbook();
		}
		
		// outer loop varies the array size of arrays to be sorted
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
			
			/**
			 * We do not want the result of sorting through a randomly-ordered array to be skewed by extreme cases
			 * e.g. an array randomly generated turns out to be in descending order
			 * Therefore, for a particular array size, we sort a number of randomly-generated arrays of that size
			 * and take the average as the final result.
			 */
			StatisticalResults[] insertRandomResultsArray = new StatisticalResults[NUM_OF_TESTINGS_RANDOM];
			StatisticalResults[] mergeRandomResultsArray = new StatisticalResults[NUM_OF_TESTINGS_RANDOM];
			
			// generate, sort and store all testings' results
			for(int testingsIndex = 0; testingsIndex < NUM_OF_TESTINGS_RANDOM; ++testingsIndex)
			{
				insertRandomResultsArray[testingsIndex] = new StatisticalResults(arraySize);
				mergeRandomResultsArray[testingsIndex] = new StatisticalResults(arraySize);
				
				long[] insertRandomArray = InputGenerator.generateRandomArray2(arraySize);
				long[] mergeRandomArray = InputGenerator.generateRandomArray2(arraySize);
				
				Sorter.insertionSort(insertRandomArray, insertRandomResultsArray[testingsIndex]);
				Sorter.mergeSort(mergeRandomArray, 0, arraySize-1, mergeRandomResultsArray[testingsIndex]);
				
				if(testingsIndex % (NUM_OF_TESTINGS_RANDOM/10) == 0)
					System.out.println("Random Sort Progress: " + 
							(testingsIndex/(NUM_OF_TESTINGS_RANDOM/10)+1) + " of 10");
			}
			
			/**
			 * no need to do a number of testings for ascending and descending array
			 */
			// average and store final result of sorting randomly-ordered array using insertion and merge sort
			Sorter.quickAndInsertionSort(insertRandomResultsArray,0,NUM_OF_TESTINGS_RANDOM-1);
			sortingResultsArray[0].setAverageCPUTimeAndNumOfKeyCmp(insertRandomResultsArray);
			
			Sorter.quickAndInsertionSort(mergeRandomResultsArray,0,NUM_OF_TESTINGS_RANDOM-1);
			sortingResultsArray[3].setAverageCPUTimeAndNumOfKeyCmp(mergeRandomResultsArray);
			
			// generate ascending and descending arrays with insertion and merge sort
			long[] insertAscendArray = InputGenerator.generateAscendArray(arraySize);
			long[] insertDescendArray = InputGenerator.generateDescendArray(arraySize);
			
			long[] mergeAscendArray = InputGenerator.generateAscendArray(arraySize);
			long[] mergeDescendArray = InputGenerator.generateDescendArray(arraySize);

			// sort ascending and descending arrays with insertion and merge sort
			Sorter.insertionSort(insertAscendArray, sortingResultsArray[1]);
			Sorter.insertionSort(insertDescendArray, sortingResultsArray[2]);
			
			Sorter.mergeSort(mergeAscendArray, 0, arraySize-1, sortingResultsArray[4]);
			Sorter.mergeSort(mergeDescendArray, 0, arraySize-1, sortingResultsArray[5]);
			
			// transfer of sorting results to Excel workbook
			for(int index = 0; index < sortingResultsArray.length; ++ index)
			{
				IOHandler.transferResultsToWorkbook(sortingResultsArray[index], resultsWorkbookArray[index]);
			}
			
			if(arraySize % (MAX_ARRAY_SIZE/100) == 0)
				System.out.println("Overall Progress: " + arraySize/(MAX_ARRAY_SIZE/100) + 
									" of 100\n--------------------------");
		}
		
		// create appropriate file names, write results to files and close workbooks
		String[] outputFileNames = new String[6];
		for(int index = 0; index < outputFileNames.length; ++index)
		{
			outputFileNames[index] = "Output Data\\" + ((index < 3)? "Insertion Sort":"Merge Sort") + " on ";
									
			if(index % 3 == 0)
			{
				outputFileNames[index] += "Randomly-Ordered";
			}
			else if (index % 3 == 1)
			{
				outputFileNames[index] += "Ascending";
			}
			else
			{
				outputFileNames[index] += "Descending";
			}
			
			outputFileNames[index] += " Array Results.xlsx";
			
			IOHandler.closeWorkbook(resultsWorkbookArray[index],outputFileNames[index]);
		}
		
		long programEndTime = System.currentTimeMillis();
		long programTimeTaken = programEndTime - programStartTime;
		
		System.out.println("Done");
		
		/**
		 * 1 second = 1000 milliseconds
		 * 1 minute = 60 seconds
		 * 1 hour = 60 minutes
		 */
		long numOfHoursTaken = programTimeTaken/(60*60*1000);
		programTimeTaken %= 60*60*1000;
		
		long numOfMinTaken = programTimeTaken/(60*1000);
		programTimeTaken %= 60*1000;
		
		long numOfSecTaken = programTimeTaken/1000;
		
		System.out.println("Time taken to run entire program: " + 
							numOfHoursTaken + "h " + 
							numOfMinTaken + "min " +
							numOfSecTaken + "s");
	}

}
