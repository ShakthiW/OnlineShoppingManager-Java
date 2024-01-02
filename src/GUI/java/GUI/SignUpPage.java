package GUI;

import org.example.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SignUpPage implements ActionListener {
    JFrame frame = new JFrame();
    JButton signUpButton = new JButton("Sign Up");
    JButton resetButton = new JButton("Reset");
    JButton logInButton = new JButton("Log In");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JPasswordField reEnterPasswordField = new JPasswordField();
    JLabel signUpFrom = new JLabel("Sign Up", SwingConstants.CENTER);
    JLabel userIDLabel  = new JLabel("Username");
    JLabel userPasswordLabel = new JLabel("Password :");
    JLabel reEnterPasswordLabel = new JLabel("Repeat Password :");
    JLabel messageLabel = new JLabel();
    JLabel returningUser = new JLabel("Already a user? CLick on LogIn: ");
    IDandPassword iDandPassword;

    SignUpPage(IDandPassword iDandPasswordOriginal) {
        this.iDandPassword = iDandPasswordOriginal;

        signUpFrom.setBounds(120, 40, 200, 35);
        signUpFrom.setFont(new Font("POPPINS", Font.BOLD, 30));

        userIDLabel.setBounds(50, 100, 75, 25);
        userPasswordLabel.setBounds(50, 150, 75, 25);
        reEnterPasswordLabel.setBounds(50, 200, 125, 25);

        messageLabel.setBounds(125, 250, 250, 35);
        messageLabel.setFont(new Font("POPPINS", Font.ITALIC, 25));

        userIDField.setBounds(165, 100, 200, 25);
        userPasswordField.setBounds(165, 150, 200, 25);
        reEnterPasswordField.setBounds(165, 200, 200, 25);

        signUpButton.setBounds(165, 300, 100, 25);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(this);

        resetButton.setBounds(265, 300, 100, 25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        returningUser.setBounds(50, 350, 200, 25);

        logInButton.setBounds(265, 350, 100, 25);
        logInButton.setFocusable(false);
        logInButton.addActionListener(this);

        frame.add(signUpFrom);

        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(reEnterPasswordLabel);
        frame.add(messageLabel);

        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(reEnterPasswordField);

        frame.add(returningUser);

        frame.add(signUpButton);
        frame.add(resetButton);
        frame.add(logInButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 550);
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
                iDandPassword.users.put(userID, new User(userID, password));
                try {
                    iDandPassword.saveUsers();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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