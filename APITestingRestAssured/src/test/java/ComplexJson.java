import org.testng.Assert;

import Files.Paylaod;
import io.restassured.path.json.JsonPath;

public class ComplexJson {

	public static void main(String[] args) 
	{
		int sum=0;
		JsonPath js=new JsonPath(Paylaod.CourseDetails());
		int count=js.getInt("courses.size()");
		System.out.println(count);
		
		//Course Titles and Prices
		for(int i=0;i<count;i++)
		{
			String CourseTitles=js.get("courses["+i+"].title");
			System.out.println(CourseTitles);
			int CoursePrices=js.getInt("courses["+i+"].price");
			System.out.println(CoursePrices);
		}
		
		//No.of copies for Appium course
		
		System.out.println("No.of copies for Appium course");
		for(int i=0;i<count;i++)
		{
			String CourseTitles=js.get("courses["+i+"].title");
			if(CourseTitles.equalsIgnoreCase("Appium"))
			{
				int Copies=js.get("courses["+i+"].copies");
				System.out.println(Copies);
				break;
			}

	    }
		
		for(int i=0;i<count;i++)
		{
			
			int CoursePrices=js.get("courses["+i+"].price");
			int CourseCopies=js.getInt("courses["+i+"].copies");
			int amount=CoursePrices*CourseCopies;
			System.out.println(amount);
			sum=sum+amount;
		}
		System.out.println(sum);
		int purchaseAmount =js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		Assert.assertEquals(sum, purchaseAmount);

    }
}
