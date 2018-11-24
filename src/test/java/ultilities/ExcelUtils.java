package ultilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public static ResultLog logger = new ResultLog("ExcelUtils - ");
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	
	//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
	public static void setExcelFile(String Path,String SheetName) throws Exception {
		try {
	 		// Open the Excel file
 			FileInputStream ExcelFile = new FileInputStream(Path);
 
			// Access the required test data sheet
 			ExcelWBook = new XSSFWorkbook(ExcelFile);
 			ExcelWSheet = ExcelWBook.getSheet(SheetName);
 			} catch (Exception e){
 				throw (e);
 			}
		}
	 
		public static Object[][] getTableArray(String FilePath, String SheetName, String iTestCaseName, int totalCols)    throws Exception
 		{   
			String[][] tabArray = null;
			try{
				FileInputStream ExcelFile = new FileInputStream(FilePath);
				
				// Access the required test data sheet
				ExcelWBook = new XSSFWorkbook(ExcelFile);
				ExcelWSheet = ExcelWBook.getSheet(SheetName);
				int startRow = 1;
				int startCol = 1;
 			   	int ci=0,cj=0,rowCount=0;
 			   	int totalRows = ExcelWSheet.getLastRowNum();
 			   	
 			   	//Define the size of tabArray by loopping through all rows and 
 			   	//return the counted number of row which has test case's name equal to iTestCaseName  
 			   	for (int i=startRow;i<=totalRows;i++) {           	   
 			   		if(getCellData(i, 0).equals(iTestCaseName)){
 			   			rowCount++;
			   		}
			   	}
 			   	tabArray=new String[rowCount][totalCols];

 			   	for (int i=startRow;i<=totalRows;i++) {           	   
 			   		cj=0;
 			   		if(getCellData(i, 0).equals(iTestCaseName)){
	 			   		for (int j=startCol;j<=totalCols;j++, cj++){
 			   				tabArray[ci][cj]=getCellData(i,j);
// 			   				logger.info(tabArray[ci][cj]);
 			   			}
	 			   		ci++;
			   		}
 			   	}	
			}
			catch (FileNotFoundException e)
			{
				logger.error("Could not read the Excel sheet");
				e.printStackTrace();
			}
			catch (IOException e)
			{
				logger.error("Could not read the Excel sheet");
				e.printStackTrace();
			}
			return(tabArray);
 		}
	 
		//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
		public static String getCellData(int RowNum, int ColNum) throws Exception{
			try{
				DataFormatter formatter = new DataFormatter();
				String CellData = formatter.formatCellValue(ExcelWSheet.getRow(RowNum).getCell(ColNum));
				return CellData;
			}catch (Exception e){
				return "";
			}
		}
		//This method return a two dimensional arrays that can be call in DataProvider method to provide data for Test methods
		public static Object[][] getTableArray(String FilePath, String SheetName, String iTestCaseName) throws Exception
	 		{   
				Object[][] tabArray = null;
				try{
					FileInputStream ExcelFile = new FileInputStream(FilePath);
					
					// Access the required test data sheet
					ExcelWBook = new XSSFWorkbook(ExcelFile);
					ExcelWSheet = ExcelWBook.getSheet(SheetName);
					int startRow = 1;
					int startCol = 1;
	 			   	int ci=0,cj=0,rowCount=0;
	 			   	int totalRows = ExcelWSheet.getLastRowNum();
	 			   	
	 			   	System.out.println("TOTAL ROWS:" + totalRows);
	 			   	//Define the size of tabArray by looping through all rows and 
	 			   	//return the counted number of row which has test case's name equal to iTestCaseName  
	 			   	for (int i=startRow;i<=totalRows;i++) {           	   
	 			   		if(getCellData(i, 0).equals(iTestCaseName)){
	 			   			rowCount++;
				   		}
				   	}
	 			   	
	 			   	tabArray=new Object[rowCount][100];

	 			   	for (int i=startRow;i<=totalRows;i++) {           	   
	 			   		cj=0;
	 			   		if(getCellData(i, 0).equals(iTestCaseName)){
	 			   			tabArray[ci] = new Object[countColumn(i)-1];
	 			   			//System.out.println(tabArray[ci].length);
		 			   		for (int j=startCol;j<=countColumn(i)-1;j++, cj++){
		 			   			//System.out.println(ci + "," + cj);
		 			   			tabArray[ci][cj] = Integer.parseInt(getCellData(i,j));
	 			   			}
		 			   		ci++;
				   		}
	 			   	}	
				}
				catch (FileNotFoundException e)
				{
					System.out.println("Could not read the Excel sheet");
					e.printStackTrace();
				}
				catch (IOException e)
				{
					System.out.println("Could not read the Excel sheet");
					e.printStackTrace();
				}
				return(tabArray);
	 		}
			
		//This method is to count the column from a particular Excel row
		public static int countColumn(int RowNum) throws Exception{
			try{
				int noOfColumns = ExcelWSheet.getRow(RowNum).getPhysicalNumberOfCells();
				return noOfColumns;
			}catch (Exception e){
				return 0;
			}
		}	
}
