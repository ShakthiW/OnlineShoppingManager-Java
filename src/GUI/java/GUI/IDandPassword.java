package GUI;

import java.util.HashMap;

public class IDandPassword {
    HashMap<String, String> loginInfo = new HashMap<String, String>();

    IDandPassword() {
        loginInfo.put("Bro", "Pizza");
        loginInfo.put("Shakthi", "Shakthi123");
        loginInfo.put("Buddhima", "Buddhima123");
    }

    protected HashMap getLoginInfo() {
        return loginInfo;
    }
}
