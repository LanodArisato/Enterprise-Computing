public class item {
    String itemID;
    String itemDesc;
    String stockStatus;
    int curStock;
    double price;
    boolean inCart;

    public item(String itemID, String itemDesc,String stockStatus, int curStock, double price) //item object for inventory hashmap
    {
        this.itemID = itemID;
        this.itemDesc = itemDesc;
        this.stockStatus = stockStatus;
        this.curStock = curStock;
        this.price = price;
        this.inCart = false;
    }

    public item(String itemID, String itemDesc,String stockStatus, int curStock, double price, boolean inCart) //item object for when an item is in cart
    {
        this.itemID = itemID;
        this.itemDesc = itemDesc;
        this.stockStatus = stockStatus;
        this.curStock = curStock;
        this.price = price;
        this.inCart = inCart;
    }

    public void adjustStock(int stockChange) //add/subtract stock method for use in handling cart vs inventory item quantities
    {
        this.curStock += stockChange;
    }

    //Getters for object members in different types
    public boolean getStock()
    {
        return this.stockStatus.equalsIgnoreCase("true");
    }

    public int getQuantity()
    {
        return this.curStock;
    }

    public double getPrice()
    {
        return this.price;
    }

    public String getDesc()
    {
        return this.itemDesc;
    }

    public String getID()
    {
        return this.itemID;
    }

    public String getStockString()
    {
        return this.stockStatus;
    }

    public String toString()
    {
        return this.itemID + " " + this.itemDesc + " $" + this.price;
    }

}

