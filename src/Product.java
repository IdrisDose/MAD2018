import java.text.*;

public class Product {
    private String name;
    private int stock;
    private double price;

    public Product(String name, int stock, double price) {
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public boolean isEmpty() {
        if(this.stock == 0)
            return true;
        return false;
    }

    /**
     * Return true if this product has at least n stock
     */
    public boolean has(int n) {
        if(this.stock >= n)
            return true;
        return false;
    }

    /**
     * Sell n stock of this product (decrease stock by n)
     * and return the amount of money earned (price * n)
     */
    public double sell(int n) {
        if(this.has(n)){
            this.stock = this.stock-n;
            double temp = price*n;
            return temp;
        }
        System.out.println("Not enough stock");
        return 0.0;
    }

    /**
     * Increase stock by n.
     */
    public void restock(int n) {
        this.stock = this.stock + n;
    }

    @Override
    public String toString() {
        return name + " - " + stock + " at $" + formatted(price);
    }

    private String formatted(double money) {
        return new DecimalFormat("###,##0.00").format(money);
    }
}
