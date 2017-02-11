package genericLibraries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelData {
	
	//static String xlPath = System.getProperty("user.dir")+"\\src\\test\\resources\\DP_Sample.xlsx";
	static String xlPath = System.getProperty("user.dir")+UtilityClass.getPropertyFile("xlPath");
	static String sheetName = null;
	static String referenceSheet = UtilityClass.getPropertyFile("xlReferenceSheet");
	private static ExcelReader xl =null;
	
	private static void getSheetName(String methodName)
	{
		int rowCountRef = xl.getRowCount(referenceSheet);
		for(int i =1;i<rowCountRef;i++)
		{
			String methodColoumn = xl.readXlData(referenceSheet, i, 1);
			if(methodColoumn.equalsIgnoreCase(methodName))
			{
				sheetName = xl.readXlData(referenceSheet, i, 2);
				break;
			}
		}
	}
	
	public static List<Object[]> getXlData(String methodName) throws IOException
	{
		xl = new ExcelReader(xlPath);
		getSheetName(methodName);
		int rowCount = xl.getRowCount(sheetName);
		int cellCount = xl.getCellCount(sheetName);
		Map<String,String> map = null;
		Object[] obj =null;
		List<Object[]> ls = new ArrayList<Object[]>();
		for(int i=1;i<rowCount;i++)
		{
			map = new HashMap<String, String>();
			obj= new Object[1];
			if(xl.readXlData(sheetName, i, 3).equalsIgnoreCase("Y"))
			{
				for(int j=0;j<cellCount;j++)
				{
					String key = xl.readXlData(sheetName, 0, j);
					String value = xl.readXlData(sheetName, i, j);
					map.put(key, value);	
				}
				obj[0] = map;
				ls.add(obj);	
			}
					
		}
		return ls;
		
	}
	
	/*public static void main(String[] args) throws IOException {
		
		ExcelData data = new ExcelData();
		List<Object[]> xlData = data.getXlData();
		
	}*/

}
