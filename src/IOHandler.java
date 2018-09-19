
public class IOHandler
{
	public static void printArray(int[] array)
	{
		System.out.println("-----Start of Array-----");
		
		for(int index = 0; index < array.length; ++index)
		{
			System.out.print(array[index] + "	" + (((index+1)%10 == 0)? "\n":""));
		}
		
		System.out.println("------End of Array------");
	}
}
