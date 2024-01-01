package GUI;

import org.example.Electronics;
import org.example.Product;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShoppingPage {
    ShoppingPage(String userID) {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Electronics("E100", "Mobile Phone", 12, 500,
                "Samsung", 12));
        products.add(new Electronics("E101", "Laptop", 2, 1200,
                "Apple", 6));
        products.add(new Electronics("E102", "Charger", 45, 100,
                "Huawei", 12));
        products.add(new Electronics("C100", "Shirt", 23, 50,
                "Moose", 12));
        products.add(new Electronics("C101", "Frocks", 55, 150,
                "Memosa", 12));
        new GUI(products);
    }
}
