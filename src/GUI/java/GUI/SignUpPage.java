package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPage implements ActionListener {
    JFrame frame = new JFrame();
    JButton signUpButton = new JButton("Sign Up");
    JButton resetButton = new JButton("Reset");
    JButton logInButton = new JButton("Log In");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JPasswordField reEnterPasswordField = new JPasswordField();
    JLabel userIDLabel  = new JLabel("Username");
    JLabel userPasswordLabel = new JLabel("Password :");
    JLabel reEnterPasswordLabel = new JLabel("Repeat Password :");
    JLabel messageLabel = new JLabel();
    IDandPassword iDandPassword;

    SignUpPage(IDandPassword iDandPasswordOriginal) {
        this.iDandPassword = iDandPasswordOriginal;

        userIDLabel.setBounds(50, 100, 75, 25);
        userPasswordLabel.setBounds(50, 150, 75, 25);
        reEnterPasswordLabel.setBounds(50, 200, 125, 25);

        messageLabel.setBounds(125, 250, 250, 35);
        messageLabel.setFont(new Font("POPPINS", Font.ITALIC, 25));

        userIDField.setBounds(125, 100, 200, 25);
        userPasswordField.setBounds(125, 150, 200, 25);
        reEnterPasswordField.setBounds(125, 200, 200, 25);

        signUpButton.setBounds(125, 300, 100, 25);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(this);

        resetButton.setBounds(225, 300, 100, 25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        logInButton.setBounds(125, 350, 100, 25);
        logInButton.setFocusable(false);
        logInButton.addActionListener(this);

        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(reEnterPasswordLabel);
        frame.add(messageLabel);

        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(reEnterPasswordField);

        frame.add(signUpButton);
        frame.add(resetButton);
        frame.add(logInButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            userIDField.setText("");
            userPasswordField.setText("");
            reEnterPasswordField.setText("");
            messageLabel.setText("");
        }

        if (e.getSource() == signUpButton) {
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());
            String reEnterPassword = String.valueOf(reEnterPasswordField.getPassword());

            if (!password.equals(reEnterPassword)) {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Passwords do not match");
            } else if (iDandPassword.loginInfo.containsKey(userID)) {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Username already exists");
            } else {
                iDandPassword.loginInfo.put(userID, password);
                messageLabel.setForeground(Color.GREEN);
                messageLabel.setText("Sign Up Successful");
                frame.dispose();
                LogInPage logInPage = new LogInPage(iDandPassword.getLoginInfo());
            }
        }

        if (e.getSource() == logInButton) {
            frame.dispose();
            LogInPage logInPage = new LogInPage(iDandPassword.getLoginInfo());
        }
    }
}
