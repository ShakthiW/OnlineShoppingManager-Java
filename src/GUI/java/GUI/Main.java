package GUI;

import org.example.Clothing;
import org.example.Electronics;
import org.example.Product;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        IDandPassword iDandPassword = new IDandPassword();
        LogInPage logInPage = new LogInPage(iDandPassword.getLoginInfo());
    }
}
