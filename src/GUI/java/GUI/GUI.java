package GUI;

import org.example.*;

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
    private User currentUser;

    private ArrayList<Product> products;
    private ShoppingCart shoppingCart;
    private JLabel categoryL, detailsL, productL;
    private JComboBox comboBox;
    private JTable table;
    private JButton viewCartBtn, addToCart, checkOut;
    private DefaultTableModel model;

    public GUI(ArrayList<Product> products, User currentUser) {
        this.products = products;
        this.currentUser = currentUser;
        shoppingCart = new ShoppingCart();

        JFrame frame = new JFrame("Westminster Shopping Center");
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

        model = new DefaultTableModel(new String[]{"Product ID", "Name", "Category", "Price(Rs.)", "Info"}, 0);
        for (Product product : products) {
            Object[] productArray = {product.getProductID(), product.getProductName(), product.getProductCategory(), product.getPrice(), product.getInfo()};
            model.addRow(productArray);
        }

        table = new JTable(model);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(4).setPreferredWidth(150);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(25, 70, 550, 250);
        panel.add(scrollPane);

        detailsL = new JLabel("Selected Product - Details");
        detailsL.setFont(new Font("POPPINS", Font.BOLD, 12));
        detailsL.setBounds(30, 320, 200, 25);
        panel.add(detailsL);

        productL = new JLabel("Select a product to view details");
        productL.setBounds(30, 340, 200, 125);
        panel.add(productL);

        ListSelectionModel listModel = table.getSelectionModel();
        listModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listModel.addListSelectionListener(this);

        viewCartBtn = new JButton("Shopping Cart");
        viewCartBtn.setBounds(430, 10, 150, 25);
        viewCartBtn.addActionListener(this);
        panel.add(viewCartBtn);

        addToCart = new JButton("Add To Shopping Cart");
        addToCart.setBounds(200, 470, 170, 25);
        addToCart.addActionListener(this);
        panel.add(addToCart);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
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

        HashMap<Product, Integer> map = shoppingCart.getProducts();
        model.setRowCount(0);

        double total = 0;
        boolean threeItems = false;
        boolean firstPurchase = false;
        int electronicsCount = 0;
        int clothingCount = 0;
        double discount = 0;

        for (Map.Entry<Product, Integer> entry : map.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            Object[] arr = {product.getProductID() + ", " + product.getProductName() + ", " + product.getInfo(), quantity, (quantity * product.getPrice())};
            model.addRow(arr);
            total += (quantity * product.getPrice());

            if (product.getProductCategory().equalsIgnoreCase("Electronics")) {
                electronicsCount += entry.getValue();
            } else if (product.getProductCategory().equalsIgnoreCase("Clothing")) {
                clothingCount += entry.getValue();
            }

            if (electronicsCount >= 3 || clothingCount >= 3) {
                threeItems = true;
            }

            if (currentUser.getPurchaseCount() < 1) {
                firstPurchase = true;
            }
        }

        JLabel totalL = new JLabel("Total: Rs. " + String.format("%.2f", total));
        totalL.setBounds(400, 250, 200, 30);

        if (threeItems) {
            discount = (total * 0.20);
            JLabel discountLbl20 = new JLabel("Three items in the same Category Discount (20%): -Rs. " + String.format("%.2f", discount));
            discountLbl20.setBounds(150, 280, 400, 25);
            panel.add(discountLbl20);
        }

        if (firstPurchase) {
            discount = (total * 0.10);
            JLabel discountLbl10 = new JLabel("First Purchase Discount (10%): -Rs. " + String.format("%.2f", discount));
            discountLbl10.setBounds(150, 320, 400, 25);
            panel.add(discountLbl10);
        }

        JLabel finalL = new JLabel("Final Total: Rs. " + String.format("%.2f", (total - discount)));
        finalL.setFont(new Font("", Font.BOLD, 12));
        finalL.setBounds(370, 350, 400, 25);

        checkOut = new JButton("Check Out");
        checkOut.addActionListener(this);
        checkOut.setBounds(430, 380, 100, 25);
        checkOut.setFont(new Font("", Font.BOLD, 12));

        panel.add(scrollPane);
        panel.add(totalL);
        panel.add(finalL);
        panel.add(checkOut);

        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //if(e.getActionCommand().equalsIgnoreCase("View Cart")){
        if(e.getSource() == viewCartBtn) {
            shoppingCartFrame();
        }

        //else if(e.getActionCommand().equalsIgnoreCase("Add To Cart")){
        else if(e.getSource() == addToCart){
            int idx = table.getSelectedRow();
            if (idx != -1) {
                Product product = products.get(idx);

                // Show input dialog to get quantity
                String input = JOptionPane.showInputDialog("Please Enter Quantity: ");

                // Check if the input is not empty
                if (input != null && !input.isEmpty()) {
                    try {
                        int quantity = Integer.parseInt(input);

                        if(products.get(idx).getQuantity() > 0 && quantity <= products.get(idx).getQuantity()){
                            products.get(idx).decreaseQuantity(quantity);
                            shoppingCart.addProduct(product, quantity);
                            JOptionPane.showMessageDialog(null, product.getProductName()+" Added To Cart!");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Insufficient Quantity!");
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Quantity! Please enter a valid number.");
                    }
                }
            }
        }

        else if (e.getSource() == checkOut) {
            // Perform checkout actions
            clearShoppingCart();
            incrementUserPurchaseCount();
            JOptionPane.showMessageDialog(null, "Checkout successful!\nYour purchases count has been increased by one.");
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

    private void clearShoppingCart() {
        shoppingCart.clearCart();
    }

    private void incrementUserPurchaseCount() {
        currentUser.incrementPurchaseCount();
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


    // JLabel does not interpret the '\n' character... so used HTML
    // references - https://stackoverflow.com/questions/9071389/setting-jtextpane-to-content-type-html-and-using-string-builders
    private String generateProductDetails(Product product) {
        String category = product.getProductCategory();
        StringBuilder stringBuilder = new StringBuilder(
                "<html>"
                        + "<b>Product ID:</b> " + product.getProductID() + "<br/>"
                        + "<b>Name:</b> " + product.getProductName() + "<br/>"
                        + "<b>Category:</b> " + category + "<br/>"
                        + "<b>Price: </b> Rs. " + product.getPrice() + "<br/>");

        if (category.equalsIgnoreCase("Electronics")) {
            Electronics electronics = (Electronics) product;
            stringBuilder.append("<b>Brand:</b> ").append(electronics.getBrand()).append("<br/>")
                    .append("<b>Warranty Period:</b> ")
                    .append(electronics.getWarrantyPeriod()).append(" months<br/>");
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

