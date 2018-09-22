import org.apache.commons.csv.*;

/**
 * This class does everything IO-related, be it printing to console, writing to file etc
 * 
 * @author Jason
 *
 */
public class IOHandler
{
	// This method prints out large arrays in a nice format
	public static void printArray(int[] array)
	{
		System.out.println("-----Start of Array-----");
		
		for(int index = 0; index < array.length; ++index)
		{
			System.out.print(array[index] + "\t" + (((index+1)%10 == 0)? "\n":""));
		}
		System.out.println("------End of Array------");
		System.out.println();
	}
}
