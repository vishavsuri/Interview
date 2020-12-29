package weatherCheck;


import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class PhaseTwo extends PhaseOne{

	
	public static String getResponseBody(){
	RestAssured.baseURI ="api.openweathermap.org/data/2.5/"; 
	 RequestSpecification request = RestAssured.given();
	 
	 Response response = request.queryParam("q", "Amritsar") 
	                    .queryParam("appid", "7fe67bf08c80ded756e598d6f8fedaea") 
	                    .get("/weather");
	 
	 String jsonString = response.asString();
	 System.out.println(response.getStatusCode()); 
	 Assert.assertEquals(jsonString.contains("Amritsar"), true);
	return jsonString;
	 
	 
}

public static void main(String s[]) {
	PhaseOne p1=new PhaseOne();
	String cityName=p1.WeatherCheck();	
	String json=getResponseBody();
	
	if(json.contains(cityName)) {
		System.out.println("Data matching for both json and UI");
	}else {
		System.out.println("Data not matching for both json and UI");
	}
}
}