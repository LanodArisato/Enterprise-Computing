import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.io.*;

public class GUI implements ActionListener
{
    JFrame frame = new JFrame();

    int cartCounter;
    double subtotal;

    JPanel entryPanel = new JPanel();
    JLabel itemID = new JLabel("Enter item ID for Item #" + (cartCounter + 1) + ":");
    JLabel itemQuantity = new JLabel("Enter quantity for Item #" + (cartCounter + 1) + ":");
    JLabel itemDetails = new JLabel("Details for Item #" + (cartCounter + 1) + ":");
    JLabel itemSubtotal = new JLabel("Current Subtotal for " + (cartCounter) + " item(s):");
    JTextField itemIDEntry = new JTextField();
    JTextField itemQuantityEntry = new JTextField();
    JTextField itemDetailsDisplay = new JTextField();
    JTextField itemSubtotalDisplay = new JTextField();
    Dimension entryBoxDimension = new Dimension(350,30);

    JPanel cartPanel = new JPanel();
    JLabel cartStatus = new JLabel("Your Shopping Cart is Current Empty");
    JTextField firstCartItem = new JTextField();
    JTextField secondCartItem = new JTextField();
    JTextField thirdCartItem = new JTextField();
    JTextField fourthCartItem = new JTextField();
    JTextField fifthCartItem = new JTextField();
    Dimension cartBoxDimension = new Dimension(750,30);

    JPanel buttonPanel = new JPanel();
    JButton searchButton = new JButton("Search for Item #" + (cartCounter + 1));
    JButton addButton = new JButton("Add Item #"+ (cartCounter + 1) + " To Cart");
    JButton deleteButton = new JButton("Delete Last Item Added To Cart");
    JButton checkoutButton = new JButton("Check Out");
    JButton emptyButton = new JButton("Empty Cart - Start A New Order");
    JButton exitButton = new JButton("Exit (Close App)");

    HashMap<String, item> inventory = new HashMap<>();
    Stack<item> cart = new Stack<>();
    
    public GUI()
    {
        this.scanInventory();
        this.buildGUI();

        addButton.setEnabled(false);
        deleteButton.setEnabled(false);
        checkoutButton.setEnabled(false);
        }

    private void buildGUI()
    {
        cartCounter = 0;
        subtotal = 0;

        entryPanel.setBackground(new Color(0xABE6CF));
        entryPanel.setBounds(0,0,800,250);
        entryPanel.setLayout(new GridLayout(4,2,10,20)); //TODO Place text closer to boxes (GridBagLayout maybe?)

        itemIDEntry.setPreferredSize(entryBoxDimension); 
        itemQuantityEntry.setPreferredSize(entryBoxDimension);
        itemDetailsDisplay.setPreferredSize(entryBoxDimension);
        itemSubtotalDisplay.setPreferredSize(entryBoxDimension);

        itemDetailsDisplay.setEditable(false);
        itemSubtotalDisplay.setEditable(false);

        itemDetailsDisplay.setCaretColor(Color.WHITE);
        itemSubtotalDisplay.setCaretColor(Color.WHITE);

        itemDetails.setForeground(new Color(0xFF677D));
        itemSubtotal.setForeground(Color.MAGENTA);

        entryPanel.add(itemID); 
        entryPanel.add(itemIDEntry);
        entryPanel.add(itemQuantity);
        entryPanel.add(itemQuantityEntry);
        entryPanel.add(itemDetails);
        entryPanel.add(itemDetailsDisplay);
        entryPanel.add(itemSubtotal);
        entryPanel.add(itemSubtotalDisplay);


        cartPanel.setBackground(new Color(0xD4A5A5));
        cartPanel.setBounds(0,250,800,350);
        cartPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 25));

        cartStatus.setForeground(Color.RED);
        cartStatus.setFont(new Font("Arial",Font.BOLD,20));

        firstCartItem.setPreferredSize(cartBoxDimension);
        secondCartItem.setPreferredSize(cartBoxDimension);
        thirdCartItem.setPreferredSize(cartBoxDimension);
        fourthCartItem.setPreferredSize(cartBoxDimension);
        fifthCartItem.setPreferredSize(cartBoxDimension);

        firstCartItem.setEditable(false);   //TODO change font size of text boxes
        secondCartItem.setEditable(false);
        thirdCartItem.setEditable(false);
        fourthCartItem.setEditable(false);
        fifthCartItem.setEditable(false);

        firstCartItem.setCaretColor(Color.WHITE);
        secondCartItem.setCaretColor(Color.WHITE);
        thirdCartItem.setCaretColor(Color.WHITE);
        fourthCartItem.setCaretColor(Color.WHITE);
        fifthCartItem.setCaretColor(Color.WHITE);
        
        cartPanel.add(cartStatus);
        cartPanel.add(firstCartItem);
        cartPanel.add(secondCartItem);
        cartPanel.add(thirdCartItem);
        cartPanel.add(fourthCartItem);
        cartPanel.add(fifthCartItem);


        buttonPanel.setBackground(new Color(0xC4FBF5));
        buttonPanel.setBounds(0,600,785,350);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));

        searchButton.setPreferredSize(new Dimension(350,50));
        searchButton.setBackground(new Color(0xFF677D));
        searchButton.setForeground(Color.BLACK);
        searchButton.setFocusable(false);

        addButton.setPreferredSize(new Dimension(350,50));
        addButton.setBackground(new Color(0xFF677D));
        addButton.setForeground(Color.BLACK);
        addButton.setFocusable(false);

        deleteButton.setPreferredSize(new Dimension(350,50));
        deleteButton.setBackground(new Color(0xFF677D));
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setFocusable(false);

        checkoutButton.setPreferredSize(new Dimension(350,50));
        checkoutButton.setBackground(new Color(0xFF677D));
        checkoutButton.setForeground(Color.BLACK);
        checkoutButton.setFocusable(false);

        emptyButton.setPreferredSize(new Dimension(350,50));
        emptyButton.setBackground(new Color(0xFF677D));
        emptyButton.setForeground(Color.BLACK);
        emptyButton.setFocusable(false);

        exitButton.setPreferredSize(new Dimension(350,50));
        exitButton.setBackground(new Color(0xFF677D));
        exitButton.setForeground(Color.BLACK);
        exitButton.setFocusable(false);

        buttonPanel.add(searchButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(checkoutButton);
        buttonPanel.add(emptyButton);
        buttonPanel.add(exitButton);

        exitButton.addActionListener(e -> System.exit(0));
        searchButton.addActionListener(e -> searchItem());
        addButton.addActionListener(e -> addCart());
        deleteButton.addActionListener(e -> deleteLast());


        frame.setTitle("Nile Dot Com");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setSize(800,900);
        frame.add(entryPanel);
        frame.add(cartPanel);
        frame.add(buttonPanel);
        frame.setVisible(true);

    }

    private void scanInventory()
    {
        try 
        {
            Scanner in = new Scanner(new File("inventory.csv"));

            while (in.hasNextLine())
            {
                String curLine = in.nextLine();
                String[] infoArr = curLine.split(", ");
                item newItem = new item(infoArr[0], infoArr[1], infoArr[2], Integer.parseInt(infoArr[3]), Double.parseDouble(infoArr[4]));

                inventory.put(infoArr[0], newItem);
            }
        } 
        catch (IOException e) 
        {
            //TODO add a notification for not found
        }
    }

    private void searchItem()
    {
        String itemIn = itemIDEntry.getText();
        int itemQIn = Integer.parseInt(itemQuantityEntry.getText());

        item curItem = inventory.get(itemIn);

        if (curItem == null)
        {
            JOptionPane.showMessageDialog(null, "Item ID " + itemIn + " not in file", "Nile Dot Com - ERROR", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        else if (curItem.getStock() == false)
        {
            JOptionPane.showMessageDialog(null, "Sorry, that item is out of stock, please try another item", "Nile Dot Com - ERROR", JOptionPane.ERROR_MESSAGE);  
            return;
        }
        else if (curItem.getQuantity() < itemQIn)
        {
            JOptionPane.showMessageDialog(null, "Insufficient stock. Only " + curItem.getQuantity() + " on hand. Please reduce the quantity.", "Nile Dot Com - ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if (curItem.getStock() == true && curItem.getQuantity() >= itemQIn)
        {
            itemDetailsDisplay.setText(curItem.toString() + " " + itemQIn + " " + calcDiscount(itemQIn) + "% $" + ((curItem.getPrice() * itemQIn) * (1 - (calcDiscount(itemQIn/100)))));
        }
        addButton.setEnabled(true);
        searchButton.setEnabled(false);

        itemDetails.setText("Details for Item #" + (cartCounter + 1));
    }

    private void addCart()
    {
        String itemIn = itemIDEntry.getText();
        int itemQIn = Integer.parseInt(itemQuantityEntry.getText());

        item curItem = inventory.get(itemIn);

        item cartItem = new item(itemIn, curItem.getDesc(), curItem.getStockString(), itemQIn, curItem.getPrice(), true);

        curItem.adjustStock(-itemQIn);

        cart.add(cartItem);
        cartCounter++;

        switch (cartCounter)
        {
        case(1):
        firstCartItem.setText("Item 1 - SKU: " + itemIn + ", Desc: " + cartItem.getDesc() + ", Price Ea. $" + cartItem.getPrice() + ", Qty: " + itemQIn + ", Total: $" + ((cartItem.getPrice() * itemQIn) * (1 - (calcDiscount(itemQIn/100)))));
        break;

        case(2):
        secondCartItem.setText("Item 2 - SKU: " + itemIn + ", Desc: " + cartItem.getDesc() + ", Price Ea. $" + cartItem.getPrice() + ", Qty: " + itemQIn + ", Total: $" + ((cartItem.getPrice() * itemQIn) * (1 - (calcDiscount(itemQIn/100)))));
        break;

        case(3):
        thirdCartItem.setText("Item 3 - SKU: " + itemIn + ", Desc: " + cartItem.getDesc() + ", Price Ea. $" + cartItem.getPrice() + ", Qty: " + itemQIn + ", Total: $" + ((cartItem.getPrice() * itemQIn) * (1 - (calcDiscount(itemQIn/100)))));
        break;

        case(4):
        fourthCartItem.setText("Item 4 - SKU: " + itemIn + ", Desc: " + cartItem.getDesc() + ", Price Ea. $" + cartItem.getPrice() + ", Qty: " + itemQIn + ", Total: $" + ((cartItem.getPrice() * itemQIn) * (1 - (calcDiscount(itemQIn/100)))));
        break;

        case(5):
        fifthCartItem.setText("Item 5 - SKU: " + itemIn + ", Desc: " + cartItem.getDesc() + ", Price Ea. $" + cartItem.getPrice() + ", Qty: " + itemQIn + ", Total: $" + ((cartItem.getPrice() * itemQIn) * (1 - (calcDiscount(itemQIn/100)))));
        break;
        }

        subtotal += (cartItem.getPrice() * itemQIn) * (1 - (calcDiscount(itemQIn/100)));


        itemSubtotalDisplay.setText("$" + subtotal);
        cartStatus.setText("Your Cart Currently Contains " + cartCounter + " Item(s)");
        itemIDEntry.setText("");
        itemQuantityEntry.setText("");

        addButton.setEnabled(false);
        deleteButton.setEnabled(true);
        checkoutButton.setEnabled(true);
        searchButton.setEnabled(true);
        addButton.setEnabled(false);

        itemID.setText("Enter Item ID for Item #" + (cartCounter + 1));
        itemQuantity.setText("Enter Quantity for Item #" + (cartCounter + 1));
        itemSubtotal.setText("Current Subtotal for " + cartCounter + " Item(s)");
        searchButton.setText("Search for Item #" + (cartCounter + 1));
        addButton.setText("Add Item #" + (cartCounter + 1) + " To Cart");

        //TODO cart is full if statement
        }

    private void deleteLast()
    {
        item toDelete = cart.peek();

        String delID = toDelete.getID();

        inventory.get(delID).adjustStock(toDelete.getQuantity());

        switch (cartCounter)
        {
        case(1):
        firstCartItem.setText("");
        break;

        case(2):
        secondCartItem.setText("");
        break;

        case(3):
        thirdCartItem.setText("");
        break;

        case(4):
        fourthCartItem.setText("");
        break;

        case(5):
        fifthCartItem.setText("");
        break;
        }

        cart.pop();
        cartCounter--;

        itemIDEntry.setText("");
        itemQuantityEntry.setText("");
        itemDetailsDisplay.setText("");

        subtotal = subtotal - ((toDelete.getPrice() * toDelete.getQuantity()) * (1 - (calcDiscount((toDelete.getQuantity())/100))));
        
        if (cartCounter == 0)
        {
            itemSubtotalDisplay.setText("");
        }
        else
        {
            itemSubtotalDisplay.setText("$" + subtotal);
        }
        cartStatus.setText("Your Cart Currently Contains " + cartCounter + " Item(s)");
        itemID.setText("Enter Item ID for Item #" + (cartCounter + 1));
        itemQuantity.setText("Enter Quantity for Item #" + (cartCounter + 1));
        itemSubtotal.setText("Current Subtotal for " + cartCounter + " Item(s)");
        searchButton.setText("Search for Item #" + (cartCounter + 1));
        addButton.setText("Add Item #" + (cartCounter + 1) + " To Cart");

        searchButton.setEnabled(true);
        addButton.setEnabled(false);
    }

    private int calcDiscount(int quantity)
    {
        if (quantity <= 4)
        {
            return 0;
        }
        else if(quantity <= 9)
        {
            return 10;
        }
        else if(quantity <= 14)
        {
            return 15;
        }
        else if(quantity >= 15)
        {
            return 20;
        }
        else
        {
            return 0;
        }

    }
}
