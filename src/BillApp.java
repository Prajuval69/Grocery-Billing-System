import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BillApp extends JFrame implements ActionListener {
    private JTextField customerNameField, phoneField;
    private JTextArea billArea;
    private JButton totalButton, generateBillButton, clearButton, exitButton;
    private JLabel[] foodLabels, groceryLabels, otherLabels;
    private JTextField[] foodQuantityFields, groceryQuantityFields, otherQuantityFields;

    public BillApp() {
        setTitle("Billing System");
        setSize(1500, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Grocery Billing System");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        titleLabel.setBounds(500, 30, 400, 40);
        add(titleLabel);

        // Customer Details Panel
        JPanel customerPanel = new JPanel();
        customerPanel.setBorder(BorderFactory.createTitledBorder("Customer Details"));
        customerPanel.setBounds(20, 80, 400, 150);
        customerPanel.setLayout(null);
        add(customerPanel);

        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameLabel.setBounds(20, 30, 150, 20);
        customerPanel.add(customerNameLabel);

        customerNameField = new JTextField();
        customerNameField.setBounds(180, 30, 150, 20);
        customerPanel.add(customerNameField);

        JLabel phoneLabel = new JLabel("Phone No:");
        phoneLabel.setBounds(20, 60, 150, 20);
        customerPanel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(180, 60, 150, 20);
        customerPanel.add(phoneField);

        // Food Panel
        JPanel foodPanel = new JPanel();
        foodPanel.setBorder(BorderFactory.createTitledBorder("Food"));
        foodPanel.setBounds(20, 250, 400, 350);
        foodPanel.setLayout(new GridLayout(0, 2, 10, 10));
        add(foodPanel);

        // Add food items with quantity fields to food panel
        String[] foodItems = {"Bread: 50", "Candy: 40", "Hamburger: 100", "Sandwich: 50","Gatorade: 50","Potato chips: 20", "Instant noodles: 120", "Frozen pizza: 150", "Canned soup: 80", "Breakfast cereal: 80", "Packaged cookies: 80"};
        foodLabels = new JLabel[foodItems.length];
        foodQuantityFields = new JTextField[foodItems.length];

        for (int i = 0; i < foodItems.length; i++) {
            foodLabels[i] = new JLabel(foodItems[i]);
            foodLabels[i].setBounds(20, 30 + i * 30, 100, 20);
            foodPanel.add(foodLabels[i]);

            foodQuantityFields[i] = new JTextField();
            foodQuantityFields[i].setBounds(140, 30 + i * 30, 100, 20);
            foodPanel.add(foodQuantityFields[i]);
        }

        // Grocery Panel
        JPanel groceryPanel = new JPanel();
        groceryPanel.setBorder(BorderFactory.createTitledBorder("Grocery"));
        groceryPanel.setBounds(450, 80, 400, 520);
        groceryPanel.setLayout(null);
        add(groceryPanel);

        // Add grocery items with quantity fields to grocery panel
        String[] groceryItems = {"Rice: 39", "Food Oil: 120", "Salt: 20", "Wheat: 50", "Sugar: 40","Oats: 200", "Tomato: 30", "Carrot: 40","Milk: 30", "Eggs: 6", "Bread: 40", "Chicken: 200", "Apples: 100", "Bananas: 10", "Potatoes: 40", "Onions: 100"};
        groceryLabels = new JLabel[groceryItems.length];
        groceryQuantityFields = new JTextField[groceryItems.length];

        for (int i = 0; i < groceryItems.length; i++) {
            groceryLabels[i] = new JLabel(groceryItems[i]);
            groceryLabels[i].setBounds(20, 30 + i * 30, 100, 20);
            groceryPanel.add(groceryLabels[i]);

            groceryQuantityFields[i] = new JTextField();
            groceryQuantityFields[i].setBounds(140, 30 + i * 30, 100, 20);
            groceryPanel.add(groceryQuantityFields[i]);
        }

        // Others Panel
        JPanel othersPanel = new JPanel();
        othersPanel.setBorder(BorderFactory.createTitledBorder("Others"));
        othersPanel.setBounds(880, 80, 400, 350);
        othersPanel.setLayout(null);
        add(othersPanel);

        // Add other items with quantity fields to others panel
        String[] otherItems = {"Gatorade: 50", "Coke: 20", "Juice: 40", "Waffer: 70", "Biscuits: 30","Lemonade: 20", "Red Bull: 100", "Pepsi: 40", "Geera soda: 50", "Fruit punch: 40"};
        otherLabels = new JLabel[otherItems.length];
        otherQuantityFields = new JTextField[otherItems.length];

        for (int i = 0; i < otherItems.length; i++) {
            otherLabels[i] = new JLabel(otherItems[i]);
            otherLabels[i].setBounds(20, 30 + i * 30, 100, 20);
            othersPanel.add(otherLabels[i]);

            otherQuantityFields[i] = new JTextField();
            otherQuantityFields[i].setBounds(140, 30 + i * 30, 100, 20);
            othersPanel.add(otherQuantityFields[i]);
        }

        // Bill Area
        billArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(billArea);
        scrollPane.setBounds(1280, 80, 200, 520);
        add(scrollPane);

        // Bill Menu Panel
        JPanel billMenuPanel = new JPanel();
        billMenuPanel.setBorder(BorderFactory.createTitledBorder("Bill Menu"));
        billMenuPanel.setBounds(20, 620, 1260, 60);
        billMenuPanel.setLayout(null);
        add(billMenuPanel);

        totalButton = new JButton("Total");
        totalButton.setBounds(10, 20, 100, 30);
        totalButton.addActionListener(this);
        billMenuPanel.add(totalButton);

        generateBillButton = new JButton("Generate Bill");
        generateBillButton.setBounds(120, 20, 130, 30);
        generateBillButton.addActionListener(this);
        billMenuPanel.add(generateBillButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(260, 20, 100, 30);
        clearButton.addActionListener(this);
        billMenuPanel.add(clearButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(370, 20, 100, 30);
        exitButton.addActionListener(this);
        billMenuPanel.add(exitButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == totalButton) {
            total();
        } else if (e.getSource() == generateBillButton) {
            bill_area();
        } else if (e.getSource() == clearButton) {
            clearFields();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    private void total() {
        double total = getTotalAmount();
        JOptionPane.showMessageDialog(this, "Total Bill: Rs" + total);
    }

    private void bill_area() {
        StringBuilder billContent = new StringBuilder();
        billContent.append("Customer Name: ").append(customerNameField.getText()).append("\n");
        billContent.append("Phone No: ").append(phoneField.getText()).append("\n");
        billContent.append("Bill No: ").append(generateBillNumber()).append("\n\n");
        billContent.append("Food Items:\n");
        appendCategoryItems(billContent, foodLabels, foodQuantityFields);
        billContent.append("\nGrocery Items:\n");
        appendCategoryItems(billContent, groceryLabels, groceryQuantityFields);
        billContent.append("\nOther Items:\n");
        appendCategoryItems(billContent, otherLabels, otherQuantityFields);
        billContent.append("\nTotal Amount: Rs").append(getTotalAmount());
        billArea.setText(billContent.toString());

        // Storing data in the database
        try {
            Connection connection = DatabaseManager.getConnection();
            String customerName = customerNameField.getText();
            String phoneNumber = phoneField.getText();
            String billNumber = generateBillNumber();
            double totalAmount = getTotalAmount();
            String items = billContent.toString();

            // SQL query to insert data into the database
            String query = "INSERT INTO bills (customer_name, phone_number, bill_number, total_amount, items) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customerName);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.setString(3, billNumber);
            preparedStatement.setDouble(4, totalAmount);
            preparedStatement.setString(5, items);

            // Execute the query
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data saved to the database successfully!");

            // Close the connection
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data to the database!");
        }
    }

    private String generateBillNumber() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private void appendCategoryItems(StringBuilder billContent, JLabel[] labels, JTextField[] quantityFields) {
        for (int i = 0; i < labels.length; i++) {
            int quantity = 0;
            try {
                quantity = Integer.parseInt(quantityFields[i].getText());
            } catch (NumberFormatException e) {
                // Handle invalid input
            }
            if (quantity > 0) {
                billContent.append(labels[i].getText().split(":")[0]).append(": ").append(quantity).append("\n");
            }
        }
    }

    private double getTotalAmount() {
        double total = 0;
        // Loop through food items
        for (int i = 0; i < foodLabels.length; i++) {
            try {
                int quantity = Integer.parseInt(foodQuantityFields[i].getText());
                String[] parts = foodLabels[i].getText().split(": ");
                double price = Double.parseDouble(parts[1]);
                total += quantity * price;
            } catch (NumberFormatException ignored) {
            }
        }
        // Loop through grocery items
        for (int i = 0; i < groceryLabels.length; i++) {
            try {
                int quantity = Integer.parseInt(groceryQuantityFields[i].getText());
                String[] parts = groceryLabels[i].getText().split(": ");
                double price = Double.parseDouble(parts[1]);
                total += quantity * price;
            } catch (NumberFormatException ignored) {
            }
        }
        // Loop through other items
        for (int i = 0; i < otherLabels.length; i++) {
            try {
                int quantity = Integer.parseInt(otherQuantityFields[i].getText());
                String[] parts = otherLabels[i].getText().split(": ");
                double price = Double.parseDouble(parts[1]);
                total += quantity * price;
            } catch (NumberFormatException ignored) {
            }
        }
        return total;
    }

    private void clearFields() {
        customerNameField.setText("");
        phoneField.setText("");
        billArea.setText("");
        clearQuantityFields(foodQuantityFields);
        clearQuantityFields(groceryQuantityFields);
        clearQuantityFields(otherQuantityFields);
    }

    private void clearQuantityFields(JTextField[] fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    public static void main(String[] args) {
        DatabaseManager.createDatabase(); // Create database if not exists
        DatabaseManager.createTable(); // Create table if not exists
        new BillApp();
    }
}

class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "grocery_system";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "RuDrA@1974";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL + DATABASE_NAME, USERNAME, PASSWORD);
    }

    public static void createDatabase() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable() {
        try (Connection connection = getConnection()) {
            String sql = "CREATE TABLE IF NOT EXISTS bills (" +
                    "customer_name VARCHAR(255), " +
                    "phone_number VARCHAR(15), " +
                    "bill_number BIGINT PRIMARY KEY, " +
                    "items TEXT, " +
                    "total_amount DECIMAL(10, 2)" +
                    ")";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
