package ultilities;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

public class ExtractDataFromTxtFile {
	public String configPath = "test-data/";// Define path of config.txt file	
	public String configFile="";
	public static String testDataFile="";
	
	
	/**
	 * @author Hanh Pham
	 * @Reviewer Nhat Phan
	 * @purpose This mehtod will be used to get information of a user following input id
	 * @date
	 * @History:
	 * Modified by			Date			Note
	 * Hanh Pham			2014-May-23		Support userA get specified information of userB or get information from common information
	 * @param userID
	 * @return
	 * @throws IOException
	 */
	public Hashtable readTxtFileByID(String userID) throws IOException{
        BufferedReader buffReader = null;
        Hashtable hTable = new Hashtable();
        String[] strTemp;
        String line, s;         
        loadTestDataPropertiesFile(configPath+"testData.properties");
        try{        	
        	File f = new File(configPath);
			configFile = configPath+testDataFile;
	        buffReader = new BufferedReader(new FileReader(configFile));
	        line = buffReader.readLine();                   
	        while(line!=null){
	        	if((line.equals("")==false)&&(line.contains("#")==false)){
	        		int begin =line.indexOf("*");
	                int end = line.indexOf("=");
	                s = line.substring(begin+1, end); 
	                //System.out.println("====> s: " + s);
	                if ((s.matches("\\d+")==true)){
	                	if((Integer.parseInt(userID)==Integer.parseInt(s))){
	                		strTemp = line.split("=");
	                		if(strTemp[0]!=null){
		                		//System.out.println("====> strTemp[0]: " + strTemp[0]);
		                		hTable.put(strTemp[0], strTemp[1]);
	                		}
	                	}
	                }
	                else if(line.contains("=")==true){
	                	strTemp = line.split("=");
	                	if(strTemp[0]!=null){
		                	//System.out.println("====>> strTemp[0]: " + strTemp[0]);
		                	hTable.put(strTemp[0], strTemp[1]);
	                	}
	                }
	              }
	        	  line=buffReader.readLine();
	        } 
        }
        catch (Exception e){
        	System.out.print(e);
        }
        finally{
        	buffReader.close();
        }
        return hTable;
	}
	
	public Hashtable readTxtFileByID(String userID, String hostID) throws IOException{
        BufferedReader buffReader = null;
        Hashtable hTable = new Hashtable();
        String[] strTemp;
        String line, s;         
        try{        	
        	File f = new File(configPath);
			configFile = configPath+"configData.txt";

	        buffReader = new BufferedReader(new FileReader(configFile));
	        line = buffReader.readLine();                   
	        while(line!=null){
	        	if((line.equals("")==false)&&(line.contains("#")==false)){
	        		int begin =line.indexOf("*");
	                int end = line.indexOf("=");
	                s = line.substring(begin+1, end);             
	                if ((s.matches("\\d+")==true)){
	                	strTemp = line.split("=");
	                	int userID1 = Integer.parseInt(userID);
	                	int hostID1 = Integer.parseInt(hostID);
	                	int currentID = Integer.parseInt(s);
	                	if((userID1 == currentID) || (hostID1 == currentID)){
	                		//System.out.println("====> strTemp[0]: " + strTemp[0]);
	                		hTable.put(strTemp[0], strTemp[1]);
	                	}
	                }
	                else if(line.contains("=")==true){
	                	
	                	strTemp = line.split("=");
	                	//System.out.println("====>> strTemp[0]: " + strTemp[0]);
	                	hTable.put(strTemp[0], strTemp[1]);
	                }
	            }
	        	line=buffReader.readLine();
	        } 
        }
        catch (Exception e){
        	System.out.print(e);
        }
        finally{
        	buffReader.close();
        }
        return hTable;
	}	
	
	
	/**
	 * Load properties file.
	 * @param path Properties file's path
	 */
	public static void loadTestDataPropertiesFile(String path) {
		Properties prop = new Properties();
		 
    	try {
            //load a properties file
    		prop.load(new FileInputStream(path));
    		
    		testDataFile = prop.getProperty("TEST_DATA");

    	}
    	catch (IOException ex) {
    		ex.printStackTrace();
        }
	}

}
