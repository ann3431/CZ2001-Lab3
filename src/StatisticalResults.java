import java.util.ArrayList;

public class StatisticalResults
{
	private int arraySize;
	private int numOfKeyComparisons;
	private long cpuTime;
	
	public StatisticalResults(int arraySize)
	{
		this.arraySize = arraySize;
		// if -1 shown for numOfKeyComparisons or cpuTime, obviously something is wrong
		numOfKeyComparisons = -1;
		cpuTime = -1;
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
	
	public long getCPUTime()
	{
		return cpuTime;
	}
	public void setCPUTime(long cpuTime)
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
