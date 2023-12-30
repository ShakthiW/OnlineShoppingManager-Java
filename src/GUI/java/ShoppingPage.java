import javax.swing.*;
import java.awt.*;

public class ShoppingPage {
    JFrame frame = new JFrame();
    JLabel welcomeMessage = new JLabel();

    ShoppingPage(String userID) {

        welcomeMessage.setBounds(0, 0, 200, 35);
        welcomeMessage.setFont(new Font("Poppins", Font.PLAIN, 25));
        welcomeMessage.setText("Hello! " + userID);

        frame.add(welcomeMessage);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
