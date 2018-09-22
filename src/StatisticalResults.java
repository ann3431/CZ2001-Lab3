import java.math.BigInteger;
import java.util.ArrayList;

/**
 * This class holds the results of sorting through an array,
 * results being the number of key comparisons and CPU time taken for sorting
 * 
 * @author Jason
 *
 */
public class StatisticalResults
{
	private int arraySize;
	private long numOfKeyComparisons;
	private long cpuTime;
	
	public StatisticalResults(int arraySize)
	{
		this.arraySize = arraySize;
		numOfKeyComparisons = 0;
		cpuTime = 0;
	}
	
//	public int getArraySize()
//	{
//		return arraySize;
//	}
//	public void setArraySize(int arraySize)
//	{
//		this.arraySize = arraySize;
//	}
	
	public long getNumOfKeyComparisons()
	{
		return numOfKeyComparisons;
	}
	public void setNumOfKeyComparisons(long numOfKeyComparisons)
	{
		this.numOfKeyComparisons = numOfKeyComparisons;
	}
	public void incrementNumOfKeyComparisons()
	{
		++numOfKeyComparisons;
	}
	
	public long getCPUTime()
	{
		return cpuTime;
	}
	public void setCPUTime(long cpuTime)
	{
		this.cpuTime = cpuTime;
	}
	/**
	 * Sets the average CPU Time for the sorting of a randomly-ordered array by
	 * 1) taking in an ArrayList of Results sorted base on CPU Time (sorted using quickSort())
	 * 2) removing the top and bottom 25% of results
	 * 3) finding the average of the middle portion (remaining 50%)
	 * 4) setting cpuTime to the average found
	 * 
	 * This average is also known as interquartile mean.
	 * 
	 * @param randomArrayResults
	 */
	public void setAverageCPUTime(ArrayList<StatisticalResults> randomResultsArray)
	{
		long totalCPUTime = 0;
		
		int TwentyFifthPercentilePosition = randomResultsArray.size()/4;
		int SeventyFifthPercentilePosition = 3*TwentyFifthPercentilePosition;
		
		for(int index = TwentyFifthPercentilePosition; index < SeventyFifthPercentilePosition; ++index)
		{
			totalCPUTime += randomResultsArray.get(index).getCPUTime();
		}
		
		this.cpuTime = totalCPUTime/(randomResultsArray.size()/2);
	}
	
	public String toString()
	{
		return "[Sorting result for array size " + arraySize + 
				": Number of key comparisons=" + numOfKeyComparisons +
				", CPU time=" + cpuTime + "]";
	}
}
