package ultilities;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

public class ReportListener implements ITestListener,IReporter{
	private static ResultLog Logger = new ResultLog("TEST ");

    @Override
    public void onStart(ITestContext arg0) {
    	try {
    		Logger.report("===============================================");
			Logger.report("Start Of Execution --> "+arg0.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void onTestStart(ITestResult arg0) {
    	try {
			//Logger.report("Started --> "+arg0.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void onTestSuccess(ITestResult arg0) {
    	try {
			Logger.report("PASSED --> "+arg0.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void onTestFailure(ITestResult arg0) {
    	try {
			Logger.report("FAILED --> "+arg0.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void onTestSkipped(ITestResult arg0) {
    	try {
			Logger.report("SKIPPED --> "+arg0.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void onFinish(ITestContext arg0) {
    	try {
			Logger.report("END Of Execution --> "+arg0.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

        // TODO Auto-generated method stub
    }

	@Override
	public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1, String outputDirectory) {
		// TODO Auto-generated method stub
		// Second parameter of this method ISuite will contain all the suite executed.
        for (ISuite iSuite : arg1) {
	        //Get a map of result of a single suite at a time
	        Map<String,ISuiteResult> results =    iSuite.getResults();
	
	        //Get the key of the result map
	        Set<String> keys = results.keySet();
	
	        //Go to each map value one by one
	        for (String key : keys) {
	            //The Context object of current result
	            ITestContext context = results.get(key).getTestContext();
	            //Print Suite detail in Console
	            try {
					Logger.report("Suite Name->"+context.getName());
					Logger.report("::Report output Ditectory->"+context.getOutputDirectory());
					Logger.report("::Suite Name->"+ context.getSuite().getName());
					Logger.report("::Start Date Time for execution->"+context.getStartDate());
					Logger.report("::End Date Time for execution->"+context.getEndDate());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}            
	
	            //Get Map for only failed test cases
	            IResultMap resultMap = context.getFailedTests();
	
	            //Get method detail of failed test cases
	            Collection<ITestNGMethod> failedMethods = resultMap.getAllMethods();
	
	            //Loop one by one in all failed methods
	            try {
					Logger.report("--------FAILED TEST CASE---------");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            for (ITestNGMethod iTestNGMethod : failedMethods) {
	                //Print failed test cases detail
	            	try {
						Logger.report("TESTCASE NAME->"+iTestNGMethod.getMethodName());
						Logger.report("Description->"+iTestNGMethod.getDescription());
						Logger.report("Priority->"+iTestNGMethod.getPriority());
						Logger.report(":Date->"+new Date(iTestNGMethod.getDate()));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }
        }
        try {
			Logger.report("\n\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
