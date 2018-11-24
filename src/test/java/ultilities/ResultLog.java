package ultilities;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ResultLog { 
	 String namePage;  
	   
	 public ResultLog(String name){ 
		 namePage = name;  
	 }  
	
	public String myScriptPath = System.getProperty("user.dir")+ "\\log\\";
	public final Logger logger = LogManager.getLogger("");
	
	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("_dd-MMM-yy_hh-mm-ss_SSS_a");
		Date date = new Date();
		String currentDay = dateFormat.format(date);
		return currentDay;
	}
	
	public static boolean isWindows() {
		return (System.getProperty("os.name").toLowerCase().indexOf("win")>=0);
	}
	
	/*
	Generate timestamps with microsecond precision
	For example: MicroTimestamp.INSTANCE.get() = "2017-10-21 19:13:45.267128"
	*/ 
	public enum MicroTimestamp {  
		INSTANCE ;
		private long startDate ;
		private long startNanoseconds ;
		private SimpleDateFormat dateFormat ;

		private MicroTimestamp(){  
			this.startDate = System.currentTimeMillis() ;
			this.startNanoseconds = System.nanoTime() ;
			this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS") ;
		}
	   public String get(){  
		   long microSeconds = (System.nanoTime() - this.startNanoseconds) / 1000 ;
		   long date = this.startDate + (microSeconds/1000) ;
		   return this.dateFormat.format(date) + String.format("%03d", microSeconds % 1000) ;
	   }
	}
	
	/*
   	Delete log if exists
   	*/
	public void delete() throws Exception {
		File file = new File("log.txt");
		if (file.exists()) {
			file.delete();     
		}
	}
	
	private void print(String log, String fileName) throws Exception {
		boolean appendToFile = true;

		PrintWriter pw = null;
		if (appendToFile) {
			pw = new PrintWriter(new FileWriter(fileName, true));
		} else {
			pw = new PrintWriter(new FileWriter(fileName));
		}
		pw.println(MicroTimestamp.INSTANCE.get() + ":: " + log);
		pw.flush();
		pw.close();
	}
	private void print(String log, String fileName, Integer number) throws Exception {
		boolean appendToFile = true;

		PrintWriter pw = null;
		if (appendToFile) {
			pw = new PrintWriter(new FileWriter(fileName, true));
		} else {
			pw = new PrintWriter(new FileWriter(fileName));
		}
		pw.println(MicroTimestamp.INSTANCE.get() + ":: " + log + String.valueOf(number));
		pw.flush();
		pw.close();
	}
	
	/*
 	only for console log
   	*/
	public void infoHandler(String log) throws Exception {
		logger.info(log);
	}
	
	/*
    Create a one blank space in logs file
   	*/
	public void space() throws Exception {
		boolean appendToFile = true;

		PrintWriter pw = null;
		if (appendToFile) {
			pw = new PrintWriter(new FileWriter( myScriptPath + "logs.log", true));
		} else {
			pw = new PrintWriter(new FileWriter( myScriptPath + "logs.log"));
		}
		pw.println("\n");
		pw.flush();
		pw.close();
	}
	
	public  void report(String log) throws Exception {
		print(namePage +log, myScriptPath + "logs.log");
		System.out.println(namePage +log);
	}
	
	/*
    info method
   	*/
	public  void info(String log) throws Exception {
		print("INFO - "+ namePage +log, myScriptPath + "logs.log");
		System.out.println("INFO - "+ namePage +log);
	}
	public void info(String log, Integer number) throws Exception {
		print("INFO - "+ namePage +log, myScriptPath + "logs.log", number);
		System.out.println("INFO - "+ namePage +log);
	}
	
	/*
    error method
   	*/
	public  void error(String log) throws Exception {
		print("ERROR - " + namePage +log, myScriptPath + "logs.log");
		System.out.println("ERROR - "+ namePage +log);
	}
	public void error(String log, Integer number) throws Exception {
		print("ERROR - " + namePage +log, myScriptPath + "logs.log", number);
		System.out.println("ERROR - "+ namePage +log);
	}
	
	/*
    warn method
   	*/
	public  void warn(String log) throws Exception {
		print("WARN - " + namePage +log, myScriptPath + "logs.log");
		System.out.println("WARN - "+ namePage +log);
	}
	
	/*
    Beginning line
   	*/
	public void intro()throws Exception {
		print("=========================== BEGIN LISTENING ==========================", myScriptPath + "logs.log");
	}
	
	/*
    Read file
   	*/
	public String read() throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader("log.txt"));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
  
}