package dataProvider;


import genericLibraries.ExcelData;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;


public class GenericDataProvider {
	@DataProvider(name = "DataProvider_Excel")
	public static Iterator<Object[]> dataProvider4(Method m) throws IOException
	{		
		System.out.println(m.getName());
		List<Object[]> ls = ExcelData.getXlData(m.getName());
		return ls.iterator();
	}

}
