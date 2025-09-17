package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider 1

//	@DataProvider(name="LoginData")
//	public String [][] getData() throws IOException
//	{
//		String path=".\\testData\\data.xlsx";//taking xl file from testData
//
//		ExcelUtility xlutil=new ExcelUtility(path);//creating an object for XLUtility
//
//		int totalrows=xlutil.getRowCount("Sheet1");	
//		int totalcols=xlutil.getCellCount("Sheet1",1);
//		System.out.println("Number of rows" + totalrows);
//		String logindata[][]=new String[totalrows][totalcols];//created for two dimension array which can store the data user and password
//
//		for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
//		{		
//			for(int j=0;j<totalcols;j++)  //0    i is rows j is col
//			{
//				logindata[i-1][j]= xlutil.getCellData("Sheet1",i, j);  //1,0
//			}
//		}
//		return logindata;//returning two dimension array
//	}
	
 @DataProvider(name="testDataLogin")
 public Object[][] logindata(){
	 return new Object[][] {
		 {"hello@yahoo.com","Password"},
		 {"sonlkghate@gmail.com","Password12"},
		 {"hi@hotmail.com","passwords"}
	 };
 }
 
 @DataProvider(name="testDataSearchProduct")
 public Object[][] searchproduct(){
	 return new Object[][] {
		 {"Fiction"},
		 {"Health Book"},
		 {"Healthcare Book"},
		 {"Diamond Tennis Bracelet"}
	 };
 }


}
