package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginTest extends CommonMethods {

    @Test(groups = "smoke")
    public void adminLogin(){
        // login to HRMS application
        LoginPage loginPage=new LoginPage();
        sendText(loginPage.userNameBox, ConfigReader.getPropertyValue("username"));
        sendText(loginPage.passwordBox, ConfigReader.getPropertyValue("password"));
        click(loginPage.loginBtn);

        //Validation
        //Assertion
        DashBoardPage dashBoard=new DashBoardPage();
        Assert.assertTrue(dashBoard.welcomeMsg.isDisplayed(), "welcome message is not displayed");


    }

    @Test(dataProvider = "invalidData", groups = "regression")
    public void invalidLoginErrorMessageValidation(String username, String password, String message){
        LoginPage loginPage=new LoginPage();
        sendText(loginPage.userNameBox, username);
        sendText(loginPage.passwordBox, password);
        click(loginPage.loginBtn);

        String actualError=loginPage.errorMsg.getText();
        Assert.assertEquals(actualError, message, "Error message is not matched");

    }

    public Object[][] invalidData() {
        Object[][] data = {
                {"James", "123!", "Invalid credentials"},
                {"Admin1", "Syntax123!", "Invalid credentials"},
                {"James", "", "Password cannot be empty"},
                {"", "Syntax123!", "Username cannot be empty"}
        };
        return data;
    }
}
