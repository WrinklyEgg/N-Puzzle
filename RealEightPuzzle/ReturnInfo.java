package RealEightPuzzle;

public class ReturnInfo {

    private long executionTime;
    private int executionCount;
    public ReturnInfo(long executionTime, int executionCount)
    {
        this.executionTime = executionTime;
        this.executionCount = executionCount;
    }
    public ReturnInfo(ReturnInfo rInfo)
    {
        this.executionTime = rInfo.getExeTime();
        this.executionCount = rInfo.getExeCount();
    }

    @Override
    public String toString()
    {
        return "Execution time: "+ (((double)(executionTime/100000))/10)+" milliseconds \nNumber of executions: "+executionCount;
    }
    public double getExeTimeTruncated()
    {
        return (((double)(executionTime/100000))/10);
    }
    public long getExeTime()
    {
        return executionTime;
    }
    public int getExeCount()
    {
        return executionCount;
    }
}
