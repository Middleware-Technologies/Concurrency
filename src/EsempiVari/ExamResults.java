package EsempiVari;

import java.util.Hashtable;
import java.util.Map;

public class ExamResults {
    private Map<String, Integer> data;

    public ExamResults() 
	{
		data = new Hashtable<String, Integer>();
    }
    public synchronized void addResult(String studName, int grade) 
	{
		data.put(studName, grade);
    }
    public synchronized int getResult(String studName) 
	{
		return data.get(studName);
    }
}

