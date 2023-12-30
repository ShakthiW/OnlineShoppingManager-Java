public class Main {
    public static void main(String[] args) {
        IDandPassword iDandPassword = new IDandPassword();
        LogInPage logInPage = new LogInPage(iDandPassword.getLoginInfo());
    }
}
