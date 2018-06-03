import java.util.*;

public class Store {
    private CashRegister cashRegister;
    private LinkedList<Product> products = new LinkedList<Product>();

    public static void main(String[] args){
        new Store().use();
    }


    public Store() {
        products.add(new Product("Whiteboard Marker",85,1.50));
        products.add(new Product("Whiteboard Eraser",45,5.00));
        products.add(new Product("Black Pen",100,1.50));
        products.add(new Product("Red Pen",100,1.50));
        products.add(new Product("Blue Pen",100,1.50));
        cashRegister = new CashRegister();
    }

    public void use() {
        try
        {
        char choice;

        while((choice=readChoice())!='x'){
            switch(choice){
                case 's': sell(); break;
                case 'r': restock(); break;
                case 'v': viewStock(); break;
                case 'c': viewCash(); break;
                case 'p': pruneProducts(); break;
                default:
                    help();
                    break;
            }
        }}
        catch(Exception e){
            this.use();
        }
    }

    private char readChoice() {
        System.out.print("Choice (s/r/v/c/p/x): ");
        return In.nextChar();
    }

    private void sell() {
        String name = readName();
        Product product = findProduct(name);
        if(product != null){
            System.out.println("Selling "+name);
            int number = readNumber();
            cashRegister.add(product.sell(number));
        } else {
            LinkedList<Product> list = findProducts(name);
            if(!list.isEmpty()){
                System.out.println("Multiple products match:");
                for(Product p : list){
                    System.out.println(p);
                }
            } else{
                System.out.println("No such product");
            }
        }
    }

    private void restock() {
        String name = readName();
        LinkedList<Product> list = findProducts(name);
        if(!list.isEmpty()){
            for(Product product : list){
                System.out.println("Restocking "+product.getName());
                int number = readNumber();
                product.restock(number);
            }

        } else {
            System.out.println("Adding new product");
            int number = readNumber();
            double price = readPrice();
            products.add(new Product(name, number, price));
        }
    }

    private void viewStock() {
        for(Product product:products){
            System.out.println(product);
        }
    }

    private void viewCash() {
        System.out.println(cashRegister);
    }

    private void pruneProducts() {
        for (Iterator<Product> it = products.iterator(); it.hasNext();){
            if(it.next().isEmpty()){
                it.remove();
            }
        }
    }

    private String readName() {
        System.out.print("Name: ");
        return In.nextLine();
    }

    private double readPrice() {
        System.out.print("Price: $");
        return In.nextDouble();
    }

    private int readNumber() {
        System.out.print("Number: ");
        return In.nextInt();
    }

    private Product findProduct(String name){
        for (Product product : products){
            if(product.getName().toLowerCase().equals(name.toLowerCase())){
                return product;
            }
        }
        return null;
    }

    private LinkedList<Product> findProducts(String name){
        LinkedList<Product> list = new LinkedList<Product>();
        for(Product product: products){
            if(product.getName().toLowerCase().contains(name.toLowerCase())){
                list.add(product);
            }
        }
        return list;
    }

    private void help() {
        System.out.println("Menu options");
        System.out.println("s = sell");
        System.out.println("r = restock");
        System.out.println("v = view stock");
        System.out.println("c = view cash");
        System.out.println("p = prune products");
        System.out.println("x = exit");
    }
}
