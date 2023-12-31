import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LogInPage implements ActionListener {
    JFrame frame = new JFrame();
    JButton logInButton = new JButton("Log In");
    JButton resetButton = new JButton("Reset");
    JButton signUpButton = new JButton("Sign Up");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel loginForm = new JLabel("Login", SwingConstants.CENTER);
    JLabel userIDLabel  = new JLabel("User ID :");
    JLabel userPasswordLabel = new JLabel("Password :");
    JLabel newUser = new JLabel("New user? CLick on Sign Up: ");
    JLabel messageLabel = new JLabel("");
    HashMap<String, String> loginInfo = new HashMap<String, String>();

    LogInPage(HashMap<String, String> loginInfoOriginal) {

        loginInfo = loginInfoOriginal;

        loginForm.setBounds(115, 50, 200, 35);
        loginForm.setFont(new Font("POPPINS", Font.BOLD, 30));

        userIDLabel.setBounds(50, 100, 75, 25);
        userPasswordLabel.setBounds(50, 150, 75, 25);

        messageLabel.setBounds(90, 300, 300, 35);
        messageLabel.setFont(new Font("POPPINS", Font.ITALIC, 25));

        userIDField.setBounds(125, 100, 200, 25);
        userPasswordField.setBounds(125, 150, 200, 25);

        logInButton.setBounds(125, 200, 100, 25);
        logInButton.setFocusable(false);
        logInButton.addActionListener(this);

        resetButton.setBounds(225, 200, 100, 25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        newUser.setBounds(50, 250, 200, 25);

        signUpButton.setBounds(225, 250, 100, 25);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(this);

        frame.add(loginForm);

        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);

        frame.add(userIDField);
        frame.add(userPasswordField);

        frame.add(newUser);

        frame.add(logInButton);
        frame.add(resetButton);
        frame.add(signUpButton);

        frame.setTitle("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            userIDField.setText("");
            userPasswordField.setText("");
        }

        if (e.getSource() == logInButton) {
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            if (loginInfo.containsKey(userID)) {
                if (loginInfo.get(userID).equals(password)) {
                    messageLabel.setForeground(Color.GREEN);
                    messageLabel.setText("Login Successful");

                    frame.dispose();

                    ShoppingPage shoppingPage = new ShoppingPage(userID);
                } else {
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Wrong Password");
                }
            } else {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Username Not Found");
            }
        }

        if (e.getSource() == signUpButton) {
            frame.dispose();
            SignUpPage signUpPage = new SignUpPage(new IDandPassword());
        }

    }
}
