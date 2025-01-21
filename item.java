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
}

