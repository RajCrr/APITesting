import static io.restassured.RestAssured.given;

import Files.ReUsableMethod;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class OAuthTest {

	public static void main(String[] args) 
	{

		String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AY0e-g5e03tY1dmh1EJBcT5W2K1VnbNRA-Unw7I9UNZtC_EWIo134uC30vwS_DTq8N2jOA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent#";
		
		String partialcode=url.split("code=")[1];

		String code=partialcode.split("&scope")[0];

		System.out.println(code);
		
		String accessTokenResponse=given().urlEncodingEnabled(false)
		 .queryParams("code","code")
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("grant_type","authorization_code")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js=ReUsableMethod.rawToJson(accessTokenResponse);
		String AccessToken=js.getString("access_token");
		System.out.println(AccessToken);
		
		String response=given().queryParam("access_token", "AccessToken")
		.expect().defaultParser(Parser.JSON)
		.when().get("https://rahulshettyacademy.com/getCourse.php").asString();
		
		System.out.println(response);
		

	}

}
