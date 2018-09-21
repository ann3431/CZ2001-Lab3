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
	private BigInteger numOfKeyComparisons;
	private BigInteger cpuTime;
	
	public StatisticalResults(int arraySize)
	{
		this.arraySize = arraySize;
		// if -1 shown for numOfKeyComparisons or cpuTime, obviously something is wrong
		numOfKeyComparisons = BigInteger.ZERO;
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
	
	public BigInteger getNumOfKeyComparisons()
	{
		return numOfKeyComparisons;
	}
	public void setNumOfKeyComparisons(BigInteger numOfKeyComparisons)
	{
		this.numOfKeyComparisons = numOfKeyComparisons;
	}
	public void incrementNumOfKeyComparisons()
	{
		numOfKeyComparisons = numOfKeyComparisons.add(BigInteger.ONE);
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
