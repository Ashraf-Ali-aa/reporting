package com.qaprosoft.zafira.listener;

import java.util.List;
import java.util.Map;

import org.testng.ISuite;
import org.testng.ITestResult;

import com.qaprosoft.zafira.client.model.config.Configuration;

/**
 * Interface provided to perform better integration with Zafira reporting tool.
 * 
 * @author akhursevich
 */
public interface IConfigurator
{
	Configuration getConfiguration();
	
	String getOwner(ISuite suite);
	
	String getOwner(ITestResult test);
	
	String getTestName(ITestResult test);
	
	String getTestMethodName(ITestResult test);
	
	String getLogURL(ITestResult test);
	
	String getDemoURL(ITestResult test);
	
	List<String> getTestWorkItems(ITestResult test);
	
	int getRunCount(ITestResult test);
	
	boolean isClassMode();
	
	Map<String, Long> getTestMetrics(ITestResult test);
}
