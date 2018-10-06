package InsertionVersusMerge;

//import org.apache.commons.csv.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This class does everything IO-related, be it printing to console, writing to file etc
 * 
 * @author Jason
 *
 */
public class IOHandler
{
	// This method prints out large arrays (arraySize >= 10) to the console in a nice format
	// Let us check if the array is sorted correctly
	public static void printArray(long[] array)
	{
		System.out.println("-----Start of Array-----");
		
		for(int index = 0; index < array.length; ++index)
		{
			System.out.print(array[index] + "\t" + (((index+1)%10 == 0)? "\n":""));
		}
		System.out.println("------End of Array------");
		System.out.println();
	}
	
	/**
	 * This method generates a new XSSF WorkBook.
	 * In each Workbook, there are 3 columns that record the array size, number of key comparisons and CPU time.
	 * 
	 * @param fileName
	 * @return 
	 * @throws InvalidFormatException 
	 * @throws EncryptedDocumentException 
	 */
	public static XSSFWorkbook generateNewXSSFWorkbook() 
			throws EncryptedDocumentException, InvalidFormatException
	{
		// create a new WorkBook (the main Excel file)
		XSSFWorkbook newWorkBook = new XSSFWorkbook();
		
		// create a Sheet within WorkBook that records results for sorting through the array
		Sheet newSheet = newWorkBook.createSheet();
		
		// create a new Font for header text
		Font headerFont = newWorkBook.createFont();
		headerFont.setBold(true);
//		headerFont.setFontHeightInPoints((short) 14);
		
		// create a new CellStyle for header text
		CellStyle headerCellStyle = newWorkBook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		
		Row headerRow = newSheet.createRow(0);
			
		// create header for the "array size" column (column 0)
		Cell arraySizeHeader = headerRow.createCell(0);
		arraySizeHeader.setCellValue("Array Size");
		arraySizeHeader.setCellStyle(headerCellStyle);
			
		// create header for the "number of key comparisons" column (column 1)
		Cell numOfKeyCmpHeader = headerRow.createCell(1);
		numOfKeyCmpHeader.setCellValue("Number of Key Comparisons");
		numOfKeyCmpHeader.setCellStyle(headerCellStyle);

		// create header for the "CPU time" column (column 2)
		Cell cpuTimeHeader = headerRow.createCell(2);
		cpuTimeHeader.setCellValue("CPU time taken for sorting");
		cpuTimeHeader.setCellStyle(headerCellStyle);
		
		return newWorkBook;
	}
	
	/**
	 * Transfer results of sorting stored in outputResults to workbook (Excel file), which would be used for graphing
	 * @param outputResults
	 * @param workbook
	 */
	public static void transferResultsToWorkbook(StatisticalResults outputResults, XSSFWorkbook resultsWorkbook)
	{
		// only 1 sheet in the Workbook, so just get the first sheet
		Sheet resultsSheet = resultsWorkbook.getSheetAt(0);
		
		// every time we have a new statistical result, we add this result to the last row
		Row lastRow = resultsSheet.createRow(resultsSheet.getPhysicalNumberOfRows());
		
		// create a cell style to align numbers to the right to easily see the grow in numbers
		CellStyle dataCellStyle = resultsWorkbook.createCellStyle();
		dataCellStyle.setAlignment(HorizontalAlignment.RIGHT);
		
		// place array size in the Workbook
		Cell arraySizeCell = lastRow.createCell(0);
		arraySizeCell.setCellValue(outputResults.getArraySize());
		
		// place number of key comparisons in the Workbook
		Cell numOfKeyCmpCell = lastRow.createCell(1);
		numOfKeyCmpCell.setCellValue(outputResults.getNumOfKeyCmp());
		numOfKeyCmpCell.setCellStyle(dataCellStyle);
		
		// place CPU time taken for sorting in the Workbook
		Cell cpuTimeCell = lastRow.createCell(2);
		cpuTimeCell.setCellValue(outputResults.getCPUTime());
		cpuTimeCell.setCellStyle(dataCellStyle);
	}
	
	public static void closeWorkbook(XSSFWorkbook resultsWorkbook, String fileName) throws Exception
	{
		Sheet resultsSheet = resultsWorkbook.getSheetAt(0);
		
		// resize column width automatically to fit the length of the numbers
		resultsSheet.autoSizeColumn(0);
		resultsSheet.autoSizeColumn(1);
		resultsSheet.autoSizeColumn(2);
		
		// actual transfer of output data
		File fileOut = new File(fileName);
		FileOutputStream fileOutStream = new FileOutputStream(fileOut);
		resultsWorkbook.write(fileOutStream);
		fileOutStream.close();
		
		resultsWorkbook.close();
	}
}
