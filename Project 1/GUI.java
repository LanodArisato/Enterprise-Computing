import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

public class GUI
{
    JFrame frame = new JFrame();    //Creates all objects for base GUI components

    int cartCounter;
    double subtotal;

    DecimalFormat subtotalFormat = new DecimalFormat("0.00");
    DecimalFormat discountFormat = new DecimalFormat("0.0");

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

    HashMap<String, Item> inventory = new HashMap<>();
    Stack<Item> cart = new Stack<>();
    
    public GUI() //Runs methods inventory handling and base GUI construction, sets base state
    {
        this.scanInventory();
        this.buildGUI();

        addButton.setEnabled(false);
        deleteButton.setEnabled(false);
        checkoutButton.setEnabled(false);
        }

    private void buildGUI() //Constructs and places all GUI elements
    {
        cartCounter = 0;
        subtotal = 0;

        entryPanel.setBackground(new Color(0xABE6CF));
        entryPanel.setBounds(0,0,800,250);
        entryPanel.setLayout(new GridLayout(4,2,10,20));

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
        emptyButton.addActionListener(e -> emptyCart());
        checkoutButton.addActionListener(e -> checkOut());


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

    private void scanInventory() //Scans through inventory file and creates a hashmap of item objects for easy access
    {
        try 
        {
            Scanner in = new Scanner(new File("inventory.csv"));

            while (in.hasNextLine())
            {
                String curLine = in.nextLine();
                String[] infoArr = curLine.split(", ");
                Item newItem = new Item(infoArr[0], infoArr[1], infoArr[2], Integer.parseInt(infoArr[3]), Double.parseDouble(infoArr[4]));

                inventory.put(infoArr[0], newItem);
            }
        } 
        catch (IOException e) //Dialog box if inventory file is missing
        {
            JOptionPane.showMessageDialog(null, "Inventory file not found", "Nile Dot Com - ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchItem() //Searches through hashamp based on user inputs, handles any errors with user input
    {
        String itemIn = itemIDEntry.getText();
        int itemQIn = Integer.parseInt(itemQuantityEntry.getText());

        Item curItem = inventory.get(itemIn);

        if (curItem == null) //if inventory hasmap does not have an object for the given ID
        {
            JOptionPane.showMessageDialog(null, "Item ID " + itemIn + " not in file", "Nile Dot Com - ERROR", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        else if (curItem.getStock() == false) //if stock field is false
        {
            JOptionPane.showMessageDialog(null, "Sorry, that item is out of stock, please try another item", "Nile Dot Com - ERROR", JOptionPane.ERROR_MESSAGE);  
            itemIDEntry.setText("");
            itemQuantityEntry.setText("");
            return;
        }
        else if (curItem.getQuantity() < itemQIn) //if quantity not available
        {
            JOptionPane.showMessageDialog(null, "Insufficient stock. Only " + curItem.getQuantity() + " on hand. Please reduce the quantity.", "Nile Dot Com - ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if (curItem.getStock() == true && curItem.getQuantity() >= itemQIn) //if the item is available AND quantity is available
        {
            itemDetailsDisplay.setText(curItem.toString() + " " + itemQIn + " " + calcDiscount(itemQIn) + "% $" + subtotalFormat.format(((curItem.getPrice() * itemQIn) * (1 - (calcDiscount(itemQIn/100))))));
        }

        addButton.setEnabled(true); //set new button state for a found item
        searchButton.setEnabled(false);

        itemDetails.setText("Details for Item #" + (cartCounter + 1)); //display found item detail
    }

    private void addCart() //adds an item to the cart using a stack implimentation
    {
        String itemIn = itemIDEntry.getText();
        int itemQIn = Integer.parseInt(itemQuantityEntry.getText());

        Item curItem = inventory.get(itemIn);

        //creates a new version of an item object for usage in the cart
        Item cartItem = new Item(itemIn, curItem.getDesc(), curItem.getStockString(), itemQIn, curItem.getPrice(), true);

        curItem.adjustStock(-itemQIn); //reduce the quantity in the inventory by the amount added to the cart

        cart.push(cartItem); //add item to cart stack
        cartCounter++;

        switch (cartCounter) //update text boxes based on current cart position
        {
        case(1):
        firstCartItem.setText("Item 1 - SKU: " + itemIn + ", Desc: " + cartItem.getDesc() + ", Price Ea. $" + cartItem.getPrice() + ", Qty: " + itemQIn + ", Total: $" + subtotalFormat.format(((cartItem.getPrice() * itemQIn) * (1 - (calcDiscount(itemQIn/100))))));
        break;

        case(2):
        secondCartItem.setText("Item 2 - SKU: " + itemIn + ", Desc: " + cartItem.getDesc() + ", Price Ea. $" + cartItem.getPrice() + ", Qty: " + itemQIn + ", Total: $" + subtotalFormat.format(((cartItem.getPrice() * itemQIn) * (1 - (calcDiscount(itemQIn/100))))));
        break;

        case(3):
        thirdCartItem.setText("Item 3 - SKU: " + itemIn + ", Desc: " + cartItem.getDesc() + ", Price Ea. $" + cartItem.getPrice() + ", Qty: " + itemQIn + ", Total: $" + subtotalFormat.format(((cartItem.getPrice() * itemQIn) * (1 - (calcDiscount(itemQIn/100))))));
        break;

        case(4):
        fourthCartItem.setText("Item 4 - SKU: " + itemIn + ", Desc: " + cartItem.getDesc() + ", Price Ea. $" + cartItem.getPrice() + ", Qty: " + itemQIn + ", Total: $" + subtotalFormat.format(((cartItem.getPrice() * itemQIn) * (1 - (calcDiscount(itemQIn/100))))));
        break;

        case(5):
        fifthCartItem.setText("Item 5 - SKU: " + itemIn + ", Desc: " + cartItem.getDesc() + ", Price Ea. $" + cartItem.getPrice() + ", Qty: " + itemQIn + ", Total: $" + subtotalFormat.format(((cartItem.getPrice() * itemQIn) * (1 - (calcDiscount(itemQIn/100))))));
        break;
        }

        subtotal += (cartItem.getPrice() * itemQIn) * (1 - (calcDiscount(itemQIn/100)));

        //update text fields based on new cart state
        itemSubtotalDisplay.setText("$" + subtotalFormat.format(subtotal));
        cartStatus.setText("Your Cart Currently Contains " + cartCounter + " Item(s)");
        itemIDEntry.setText("");
        itemQuantityEntry.setText("");

        //change button state for next item entry
        addButton.setEnabled(false);
        deleteButton.setEnabled(true);
        checkoutButton.setEnabled(true);
        searchButton.setEnabled(true);
        addButton.setEnabled(false);

        //update text fields based on new cart state
        itemID.setText("Enter Item ID for Item #" + (cartCounter + 1));
        itemQuantity.setText("Enter Quantity for Item #" + (cartCounter + 1));
        itemSubtotal.setText("Current Subtotal for " + cartCounter + " Item(s)");
        searchButton.setText("Search for Item #" + (cartCounter + 1));
        addButton.setText("Add Item #" + (cartCounter + 1) + " To Cart");

        if (cartCounter >= 5) //check for a full cart and change state if needed
        {
            itemIDEntry.setEnabled(false);
            itemQuantityEntry.setEnabled(false);

            searchButton.setEnabled(false);
            addButton.setEnabled(false);
        }
        }

    private void deleteLast() //deletes the last item added to the cart while maintaining proper inventory values
    {
        Item toDelete = cart.peek(); 

        String delID = toDelete.getID();

        inventory.get(delID).adjustStock(toDelete.getQuantity()); //add stock back from cart item to inventory item

        switch (cartCounter) //adjust text field based on current cart position
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

        cart.pop(); //remove from stack and adjust counter
        cartCounter--;

        //update text fields for new entry
        itemIDEntry.setText("");
        itemQuantityEntry.setText("");
        itemDetailsDisplay.setText("");

        //calculte new subtotal
        subtotal = subtotal - ((toDelete.getPrice() * toDelete.getQuantity()) * (1 - (calcDiscount((toDelete.getQuantity())/100))));
        
        if (cartCounter == 0) //update subtotal display based on current cart item count
        {
            itemSubtotalDisplay.setText("");
        }
        else
        {
            itemSubtotalDisplay.setText("$" + subtotalFormat.format(subtotal));
        }

        //update GUI component states for new item entry
        cartStatus.setText("Your Cart Currently Contains " + cartCounter + " Item(s)");
        itemID.setText("Enter Item ID for Item #" + (cartCounter + 1));
        itemQuantity.setText("Enter Quantity for Item #" + (cartCounter + 1));
        itemSubtotal.setText("Current Subtotal for " + cartCounter + " Item(s)");
        searchButton.setText("Search for Item #" + (cartCounter + 1));
        addButton.setText("Add Item #" + (cartCounter + 1) + " To Cart");

        searchButton.setEnabled(true);
        addButton.setEnabled(false);

        itemIDEntry.setEnabled(true);
        itemQuantityEntry.setEnabled(true);
    }

    public void emptyCart() //resets GUI to a fresh state, returns all items to inventory
    {
        for(int i = cart.size(); i > 0; i--)
        {
            this.deleteLast();
        }

        itemIDEntry.setEnabled(true);
        itemQuantityEntry.setEnabled(true);

        itemID.setText("Enter Item ID for Item #" + (cartCounter + 1));
        itemQuantity.setText("Enter Quantity for Item #" + (cartCounter + 1));
        itemSubtotal.setText("Current Subtotal for " + cartCounter + " Item(s)");
        itemDetails.setText("Details for Item #" + (cartCounter + 1) + ":");
        itemDetailsDisplay.setText("");
        itemSubtotalDisplay.setText("");

        cartStatus.setText("Your Cart is Currently Empty");
        firstCartItem.setText("");
        secondCartItem.setText("");
        thirdCartItem.setText("");
        fourthCartItem.setText("");
        fifthCartItem.setText("");

        searchButton.setText("Search for Item #" + (cartCounter + 1));
        addButton.setText("Add Item #" + (cartCounter + 1) + " To Cart");
        
    }

    public void checkOut() //Create checkout dialogue as well as writes to output file
    {
        BufferedWriter out;
        try
        {
            out = new BufferedWriter(new FileWriter("transaction.csv",true));

            DateTimeFormatter checkoutFormat = DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm:ss a z"); //set formats for later use
            DateTimeFormatter transactionFormat = DateTimeFormatter.ofPattern("ddmmyyyhhmmss");
            ZonedDateTime current = ZonedDateTime.now();

            Stack<Item> checkoutStack = new Stack<>(); //create new stack of cart objects in added order

            String timeString = current.format(checkoutFormat);
            String transactionString = current.format(transactionFormat);

            for(int i = cart.size(); i > 0; i--) //clears the cart and reversed item order for printing
            {
                checkoutStack.push(cart.pop());
            }

            String invoiceString = "Date: " + timeString + "\n\nNumber of line items: " + checkoutStack.size() + "\n\nItem# / ID / Title / Price / Qty / Disc % / Subtotal:\n\n";

            for(int i = 0; i < cartCounter; i++) //goes through reversed stack and writes to diaglogue and output file
            {
                Item curItem = checkoutStack.pop();

                out.write(transactionString + ", " + curItem.toTransaction() + ", " + discountFormat.format(this.calcDiscount(curItem.getQuantity())/100) + ", $" + subtotalFormat.format(((curItem.getPrice() * curItem.getQuantity()) * (1 - (calcDiscount(curItem.getQuantity()/100))))) +" " + timeString + "\n");
                invoiceString += (i+1) + ". " + curItem.toString() + " " + curItem.getQuantity() + " " + calcDiscount(curItem.getQuantity()) + "% $" + subtotalFormat.format(((curItem.getPrice() * curItem.getQuantity()) * (1 - (calcDiscount(curItem.getQuantity()/100))))) + "\n";
            }

            out.write("\n");
            out.flush();
            invoiceString += "\nOrder Subtotal: $" + subtotalFormat.format(subtotal) + "\n\nTax rate: \t6%\n\nTax amount:\t$" + subtotalFormat.format(subtotal * 0.06) + "\n\nORDER TOTAL:\t$" + subtotalFormat.format(subtotal * 1.06) + "\n\nThanks for shopping at Nile Dot Com!";


            JOptionPane.showMessageDialog(null, new JTextArea(invoiceString), "Nile Dot Com - FINAL INVOICE", JOptionPane.PLAIN_MESSAGE);

            cartCounter = 0;
            this.emptyCart();
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int calcDiscount(int quantity) //helper method for bulk discount calculaton
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
