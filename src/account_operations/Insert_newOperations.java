package account_operations;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.github.cliftonlabs.json_simple.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class Insert_newOperations {

		
		JsonObject request = new JsonObject();
		
		 
	@Test (dataProvider="testdataprovider")
	public void putData( String accountID, String amount,String currency, String date) throws Exception{
	
		 RestAssured.baseURI ="http://localhost:9999/";
		 RequestSpecification request = RestAssured.given();
		 
		 JsonObject childParams = new JsonObject();
		 JsonObject requestParams = new JsonObject();
		 
		 childParams.put("account_id", accountID); 
		 requestParams.put("statement", childParams);
		 childParams.put("amount", amount);
		 requestParams.put("statement", childParams);
		 childParams.put("currency", currency);
		 requestParams.put("statement", childParams);
		 childParams.put("date", date);
		 requestParams.put("statement", childParams);
		 
		 request.body(requestParams.toJson());
		 Response response = request.post("/statements");
		 
		  int statusCode = response.getStatusCode();
		  
		  
		  try{
			  Assert.assertEquals(statusCode, 204);
			  System.out.println("Correct Success Code was returned");
			}
		  
		   catch(AssertionError e){
			 
			   System.out.println(e);
			}
		  

	}
	
	@DataProvider(name="testdataprovider")
	String [][] getAccData() throws IOException{
		
		String excelPath = "./test-data/TestData.xlsx";
		String sheetName = "accountData";
		//String sheetName = "Sheet2";
		//String sheetName = "Sheet3";
		
		ExcelUtil excel = new ExcelUtil();
		
		int rownum =   excel.getRowCount(excelPath, sheetName);
		int colcount = excel.getCellCount(excelPath, sheetName,1);
		
		String accData[][] = new String[rownum][colcount]; 
		
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				accData[i-1][j] = excel.getCellData(excelPath, sheetName, i, j);
				
			}
			
		}
		
		return accData;
	}



}
