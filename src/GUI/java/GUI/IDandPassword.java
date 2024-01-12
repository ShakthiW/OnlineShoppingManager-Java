package GUI;

import org.example.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IDandPassword {
    HashMap<String, String> loginInfo = new HashMap<>();
    HashMap<String, User> users = new HashMap<>();

    IDandPassword() {
        loginInfo.put("Bro", "Pizza");
        loginInfo.put("Shakthi", "Shakthi123");
        loginInfo.put("Buddhima", "Buddhima123");
        loadUsers();
        for (Map.Entry<String, User> entry : users.entrySet()) {
            loginInfo.put(entry.getValue().getUserName(), entry.getValue().getPassword());
        }
    }



    public void loadUsers() {
        users.clear(); // Clear existing data
        File file = new File("users.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");
                if (parts.length == 3) {
                    String username = parts[0];
                    String password = parts[1];
                    int purchaseCount = Integer.parseInt(parts[2]);
                    users.put(username, new User(username, password, purchaseCount));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No saved users found. Starting with an empty user list.");
        }
    }

    protected HashMap getLoginInfo() {
        return loginInfo;
    }
}
