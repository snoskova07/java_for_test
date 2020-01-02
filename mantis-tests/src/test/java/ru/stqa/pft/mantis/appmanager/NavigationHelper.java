package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void goToManageUsersPage() {
        click((By.xpath("//a[text()='Manage']")));
        click((By.xpath("//a[text()='Manage Users']")));
    }

    public void selectUser(String username) {
        click((By.xpath("//a[text()='" + username + "']")));

    }

    public void resetPassword() {
        click((By.xpath("//input[@value='Reset Password']")));
    }
}
