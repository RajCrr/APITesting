import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;

public class SerializeDemo {

	public static void main(String[] args) 
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress("29, side layout, cohen 09");
		ap.setLanguage("French-IN");
		ap.setName("Frontline house");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("https://rahulshettyacademy.com");
		
		List<String> mylist=new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		
		ap.setTypes(mylist);
		
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		ap.setLocation(l);

		String response=given().queryParam("key","qaclick123")
		.body(ap).
		when().post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response);

	}

}
