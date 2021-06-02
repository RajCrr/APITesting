import org.testng.Assert;
import org.testng.annotations.Test;

import Files.Paylaod;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
   public void sumofCourses()
   {
		int sum=0;
		JsonPath js=new JsonPath(Paylaod.CourseDetails());
		int count=js.getInt("courses.size()");
		System.out.println(count);
		
		
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
