package utility;

import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import actaulPayload.ActualPayload;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyData;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Util {

	String propFilePath="G:\\github_workspace\\RestAssuredFramework\\src\\test\\java\\utility\\globalParams.properties";
	String logPath="G:\\github_workspace\\RestAssuredFramework\\target\\logging.txt";
	
	//ActualPayload actualPayload;
	
	static RequestSpecification requestSpecification;
	//static ResponseSpecification responseSpecification;
	
	int count=0;
	
	public RequestSpecification requestSpec() throws IOException {
		/*
		 * RequestSpecification reqSpecBuilder=RequestSpecBuilder() .queryParam("key",
		 * "qaclick123").header("content-type","application/json")
		 * .body(actualPayload.AddPlace(name, address, language));
		 */
		if(requestSpecification==null) {
			
			PrintStream log=new PrintStream(new FileOutputStream(logPath));
			
			requestSpecification=new RequestSpecBuilder().setBaseUri(getProperty("baseURI")).addQueryParam("key", "qaclick123")	
			.addFilter(RequestLoggingFilter.logRequestTo(log))
			.addFilter(ResponseLoggingFilter.logResponseTo(log))
			.setContentType(ContentType.JSON)
			.build();
			
			return requestSpecification;
		}
		return requestSpecification;
	}
	
//	public ResponseSpecification responseSpec() {
//		
//	
//		 responseSpecification=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
//		
//		return responseSpecification;
//		
//		
//	}
	
	public String getProperty(String parameter) throws IOException {
		Properties prop=new Properties();
		FileInputStream fis= new FileInputStream(propFilePath);
		prop.load(fis);
		
		return prop.getProperty(parameter);
	}
	
	public String getJsonPath(String key,Response response)
	{
		  String resp=response.asString();
		  
		
		  if(count==0) {
			  System.out.println("response :" + resp);
			  count++;
		  }
		JsonPath   js = new JsonPath(resp);
		return js.get(key);
	}
}
