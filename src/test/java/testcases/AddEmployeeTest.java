package testcases;

import org.testng.annotations.Test;
import pages.AddEmployeePage;
import pages.DashBoardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

public class AddEmployeeTest extends CommonMethods {
    @Test(groups = "smoke")
    public void addEmployee() {
        LoginPage loginpage = new LoginPage();
        loginpage.login(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));

        DashBoardPage dash=new DashBoardPage();
        click(dash.pimOption);
        click(dash.addEmployeeBtn);

        AddEmployeePage add=new AddEmployeePage();
        sendText(add.firstName, "test");
        sendText(add.lastName, "test12345");
        click(add.saveBtn);
    }
}
