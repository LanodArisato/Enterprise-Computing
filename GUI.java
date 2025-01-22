import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class GUI implements ActionListener
{
    JFrame frame = new JFrame();

    JPanel entryPanel = new JPanel();
    JLabel itemID = new JLabel("                                                                                       Enter item ID for Item #:");
    JLabel itemQuantity = new JLabel("                                                                                     Enter quantity for Item #:");
    JLabel itemDetails = new JLabel("                                                                                                  Details for Item #:");
    JLabel itemSubtotal = new JLabel("                                                                           Current Subtotal for x item(s):");
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
    JButton searchButton = new JButton("Search for item #");
    JButton addButton = new JButton("Add Item # To Cart");
    JButton deleteButton = new JButton("Delete Last Item Added To Cart");
    JButton checkoutButton = new JButton("Check Out");
    JButton emptyButton = new JButton("Empty Cart - Start A New Order");
    JButton exitButton = new JButton("Exit (Close App)");

    HashMap<String, item> inventory = new HashMap<>();
    
    public GUI()
    {
        this.scanInventory();
        this.buildGUI();
    }

    private void buildGUI()
    {
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
}
