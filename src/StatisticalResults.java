import java.math.BigInteger;

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
	private int numOfKeyComparisons;
	private BigInteger cpuTime;
	
	public StatisticalResults(int arraySize)
	{
		this.arraySize = arraySize;
		// if -1 shown for numOfKeyComparisons or cpuTime, obviously something is wrong
		numOfKeyComparisons = 0;
		cpuTime = BigInteger.ZERO;
	}
	
//	public int getArraySize()
//	{
//		return arraySize;
//	}
//	public void setArraySize(int arraySize)
//	{
//		this.arraySize = arraySize;
//	}
	
	public int getNumOfKeyComparisons()
	{
		return numOfKeyComparisons;
	}
	public void setNumOfKeyComparisons(int numOfKeyComparisons)
	{
		this.numOfKeyComparisons = numOfKeyComparisons;
	}
	
	public BigInteger getCPUTime()
	{
		return cpuTime;
	}
	public void setCPUTime(BigInteger cpuTime)
	{
		this.cpuTime = cpuTime;
	}
	
	public String toString()
	{
		return "[Sorting result for array size " + arraySize + 
				": Number of key comparisons=" + numOfKeyComparisons +
				", CPU time=" + cpuTime + "]";
	}
}
