package GUI;

import org.example.Clothing;
import org.example.Electronics;
import org.example.Product;
import org.example.ShoppingCart;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GUI implements ActionListener, ListSelectionListener {

    private ArrayList<Product> products;
    private ShoppingCart shoppingCart;
    private JLabel categoryL, detailsL, productL;
    private JComboBox comboBox;
    private JTable table;
    private JButton cartBtn, addCart;
    private DefaultTableModel model;

    public GUI(ArrayList<Product> products) {
        this.products = products;
        shoppingCart = new ShoppingCart();

        JFrame frame = new JFrame("Westminister Shopping Centre");
        frame.setSize(600, 550);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        categoryL = new JLabel("Select Product Category");
        categoryL.setBounds(80, 20, 160, 25);
        panel.add(categoryL);

        comboBox = new JComboBox(new String[]{"All", "Electronics", "Clothing"});
        comboBox.setSelectedItem("All");
        comboBox.addActionListener(this);
        comboBox.setBounds(240, 20, 160, 25);
        panel.add(comboBox);

        model = new DefaultTableModel(new String[]{"Product ID", "Name", "Category", "Price(£)"}, 0);
        for (Product product : products) {
            Object[] productArray = {product.getProductID(), product.getProductName(), product.getProductCategory(), product.getPrice()};
            model.addRow(productArray);
        }

        table = new JTable(model);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(3).setPreferredWidth(150);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(25, 70, 550, 250);
        panel.add(sp);

        detailsL = new JLabel("Selected Product - Details");
        Font f = new Font("", Font.BOLD, 12);
        detailsL.setFont(f);
        detailsL.setBounds(30, 320, 200, 25);
        panel.add(detailsL);

        productL = new JLabel("<product details here>");
        productL.setBounds(30, 340, 200, 125);
        panel.add(productL);

        ListSelectionModel listModel = table.getSelectionModel();
        listModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listModel.addListSelectionListener(this);

        cartBtn = new JButton("Shopping Cart");
        cartBtn.setBounds(430, 10, 150, 25);
        cartBtn.addActionListener(this);
        panel.add(cartBtn);

        addCart = new JButton("Add To Shopping Cart");
        addCart.setBounds(200, 470, 170, 25);
        addCart.addActionListener(this);
        panel.add(addCart);


        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void shoppingCartFrame() {
        JFrame frame = new JFrame("Shopping Cart");
        frame.setSize(600, 450);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Product", "Quantity", "Price"}, 0);

        JScrollPane scrollPane = new JScrollPane(new JTable(model));
        scrollPane.setBounds(10, 10, 580, 200);
        panel.add(scrollPane);

        HashMap<Product, Integer> map = shoppingCart.getProducts();
        model.setRowCount(0);
        double total = 0;
        boolean three = false;
        int counterE = 0;
        int counterC = 0;
        double discount = 0;
        for (Map.Entry<Product, Integer> entry : map.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            Object[] arr = {product.getProductID() + ", " + product.getProductName() + ", " + quantity, (quantity * product.getPrice())};
            model.addRow(arr);
            total += (quantity * product.getPrice());
            if(product.getProductCategory().equalsIgnoreCase("Electronics")){
                counterE += entry.getValue();
            }
            else if(product.getProductCategory().equalsIgnoreCase("Clothing")){
                counterC += entry.getValue();
            }
            if(counterE >= 3 || counterC >= 3){
                three = true;
            }
        }
        JLabel totalL = new JLabel("<html>Total&nbsp;&nbsp;&nbsp;&nbsp;"
                + String.format("%.2f", total) + " £</html>");
        totalL.setBounds(400, 250, 200, 30);
        panel.add(totalL);

        if (three) {
            discount = (total * 0.20);
            JLabel discountLbl = new JLabel(
                    "<html>Three items in same Category Discount (20%)&nbsp;&nbsp;&nbsp;&nbsp;-"
                            + String.format("%.2f", discount) + " £</html>");
            discountLbl.setBounds(150, 300, 400, 25);
            frame.add(discountLbl);
        }

        JLabel finalL = new JLabel("<html>Final Total&nbsp;&nbsp;&nbsp;&nbsp;"
                + String.format("%.2f", (total - discount)) + " £</html>");
        finalL.setFont(new Font("", Font.BOLD, 12));
        finalL.setBounds(370, 350, 400, 25);
        panel.add(finalL);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("Shopping Cart")){
            shoppingCartFrame();
        }
        else if(e.getActionCommand().equalsIgnoreCase("Add To Shopping Cart")){
            int idx = table.getSelectedRow();
            if (idx != -1) {
                Product product = products.get(idx);
                int quantity = Integer.parseInt(JOptionPane.showInputDialog("Please Enter Quantity: "));
                if(products.get(idx).getQuantity() > 0 && quantity <= products.get(idx).getQuantity()){
                    products.get(idx).decreaseQuantity(quantity);
                    shoppingCart.addProduct(product, quantity);
                    JOptionPane.showMessageDialog(null, product.getProductName()+" Added To Cart!");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Insufficient Quantity!");
                }
            }
        }
        if(e.getActionCommand().equalsIgnoreCase("comboBoxChanged")){
            String category = (String) comboBox.getSelectedItem();
            model.setRowCount(0);
            for (Product product : products) {
                if(category.equalsIgnoreCase("All")){
                    Object[] arr = {product.getProductID(), product.getProductName(), product.getProductCategory(), product.getPrice()};
                    model.addRow(arr);
                }
                else if(product.getProductCategory().equalsIgnoreCase(category)){
                    Object[] arr = {product.getProductID(), product.getProductName(), product.getProductCategory(), product.getPrice()};
                    model.addRow(arr);
                }
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Product selectedProduct = products.get(selectedRow);
            String productDetails = generateProductDetails(selectedProduct);
            productL.setText(productDetails);
        }
    }

    private String generateProductDetails(Product product) {
        String category = product.getProductCategory();
        StringBuilder stringBuilder = new StringBuilder("<html>"
                + "<b>Product ID:</b> " + product.getProductID() + "<br/>"
                + "<b>Name:</b> " + product.getProductName() + "<br/>"
                + "<b>Category:</b> " + category + "<br/>"
                + "<b>Price:</b> £" + product.getPrice() + "<br/>");

        if (category.equalsIgnoreCase("Electronics")) {
            Electronics electronics = (Electronics) product;
            stringBuilder.append("<b>Brand:</b> ").append(electronics.getBrand()).append("<br/>")
                    .append("<b>Warranty Period:</b> ")
                    .append(electronics.getWarrantyPeriod()).append("<br/>");
        } else if (category.equalsIgnoreCase("Clothing")) {
            Clothing c = (Clothing) product;
            stringBuilder.append("<b>Size:</b> ").append(c.getSize()).append("<br/>")
                    .append("<b>Colour:</b> ")
                    .append(c.getColor()).append("<br/>");
        }
        stringBuilder.append("<b>Items Available:</b> ").append(product.getQuantity())
                .append("</html>");
        return stringBuilder.toString();
    }
}

