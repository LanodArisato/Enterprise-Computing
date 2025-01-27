public class item {
    String itemID;
    String itemDesc;
    String stockStatus;
    int curStock;
    double price;
    boolean inCart;

    public item(String itemID, String itemDesc,String stockStatus, int curStock, double price)
    {
        this.itemID = itemID;
        this.itemDesc = itemDesc;
        this.stockStatus = stockStatus;
        this.curStock = curStock;
        this.price = price;
        this.inCart = false;
    }

    public item(String itemID, String itemDesc,String stockStatus, int curStock, double price, boolean inCart)
    {
        this.itemID = itemID;
        this.itemDesc = itemDesc;
        this.stockStatus = stockStatus;
        this.curStock = curStock;
        this.price = price;
        this.inCart = inCart;
    }

    public void adjustStock(int stockChange)
    {
        this.curStock += stockChange;
    }

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

    public String getStockString()
    {
        return this.stockStatus;
    }



    public String toString()
    {
        return this.itemID + " " + this.itemDesc + " $" + this.price;
    }

}

