package org.example;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNG {

    @Test (priority = 1)
    @Parameters({"username", "password"})
   public void firstTest1(String username, String password){
       System.out.println("Hello I am test 1 and my username is: " + username + " and my password is: " + password);
   }

    @Deprecated // მოძველებულია, მაგრამ მუშაობს
    public void firstTest2(){
        System.out.println("Hello I am test 2");
    }

    @Test (priority = 2, enabled = false, groups = "negative") // ტესტქეისის გათიშვა, აღარ გაეშვება
    public void firstTest3(){
        System.out.println("Hello I am test 3");
    }

    @Test
    public void firstTest4(){
        System.out.println("Hello I am test 4");
    }

    @Test (groups = "e2e")
    public void firstTest5(){
        System.out.println("Hello I am test 5");
    }

    @DataProvider(name = "usersForSmoke")
    public Object[][] userData(){
        return new Object[][]{
                {"user1", "password1"},
                {"user2", "password2"},
                {"user3", "password3"},

        };
    }

    @DataProvider(name = "usersForRegression")
    public Object[][] userData1() {
        return new Object[][]{
                {"user1", "password1"},
                {"user2", "password2"},
                {"user3", "password3"},

        };
    }

    @Test(dataProvider = "usersForSmoke") // 3 მომხმარებლით ლოგინ მეთოდის გატესტვა datapovider-ით
    public void loginTest(String username, String password){
        System.out.println("User: " + username + " Password: " + password);
    }

    @Test
    public void statusCode(){
        int statusCode = 200;
        int expectedStatusCode = 200;
        Assert.assertEquals(statusCode, expectedStatusCode, "სტატუს კოდი არ არის სწორი");// hard assertion, წყვეტს დაფეილებისას
    }


    @Test
    public void statusCode2(){
        SoftAssert softAssert = new SoftAssert();
        int statusCode = 404;
        int expectedStatusCode = 200;
        softAssert.assertEquals(statusCode, expectedStatusCode, "სტატუს კოდი არ არის სწორი");

        System.out.println("აქ ვარ");
        softAssert.assertAll(); // soft ის შემთხვევაში და Fail ლების მიუხედავად აგრძელებს მუშაობას
    }



}
