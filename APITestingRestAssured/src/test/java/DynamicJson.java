import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Paylaod;
import Files.ReUsableMethod;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson 
{

	@Test(dataProvider="BooksData")
	public void addBook(String isbn,String aisle) 
	{
		String response=RestAssured.baseURI="http://216.10.245.166";
		given().log().all().header("Content-Type","application/json")
		.body(Paylaod.Addbook(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js=ReUsableMethod.rawToJson(response);
		String id=js.getString("ID");
		System.out.println(id);
		
	}
	
	@DataProvider(name="BooksData")
	
	public Object[][] getData()
	{
		return new Object[][] {{"esie1","2510"},{"esie2","2518"},{"esie3","2519"}};
	}

}
