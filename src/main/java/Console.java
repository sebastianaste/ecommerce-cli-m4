import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Console {
    private Scanner scanner = new Scanner(System.in);
    private StoreService storeService ;

    public Console(StoreService storeService) {
        this.storeService = storeService;
    }


    public void start(){
        int option;
        do {
            System.out.println("Main menu:");
            System.out.println("1) Admin");
            System.out.println("2) User");
            System.out.println("0) Exit");
            System.out.println("Option: ");
            do {
                option = readInt();}
            while ((option < 0) && (option <= 2 ));
            switch (option){
                case 1:
                    adminMenu();
                    break;
                case 2:
                    userMenu();
                    break;
                case 0:
                    return;
            }

        } while (option != 0);
    }



    private void adminMenu(){
        int option;
        do {
            System.out.println("Admin menu:");
            System.out.println("1) List products");
            System.out.println("2) Search by name");
            System.out.println("3) Search by category");
            System.out.println("4) Create product");
            System.out.println("5) Edit product");
            System.out.println("6) Delete product");
            System.out.println("Option: ");
            do {
                option = readInt();}
            while ((option < 0) && (option <= 6 ));
            switch (option){
                case 1:
                    storeService.getCatalog().listProducts();
                    break;
                case 2:
                    searchByName();
                    break;
                case 3:
                    searchByCategory();
                    break;
                case 4:
                    createProduct();
                    break;
                case 5:
                    editProduct();
                    break;
                case 6:
                    deleteProduct();
                    break;
            }

        } while (option != 0);
    }
    private void userMenu(){

    }

    private void searchByName() {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        List<Product> results = storeService.getCatalog().searchByName(name);
        if (results.isEmpty())
            System.out.println("No results.");
        else
            for (Product p : results)
                System.out.println(p);
    }

    private void searchByCategory() {
        System.out.print("Category ");
        String category = scanner.nextLine().trim();
        List<Product> results = storeService.getCatalog().searchByCategory(category);
        if (results.isEmpty())
            System.out.println("No results.");
        else
            for (Product p : results)
                System.out.println(p);
    }

    private void editProduct() {
        System.out.print("Product ID: ");
        int id = readInt();
        Product p = storeService.getCatalog().findById(id);
        if (p == null) { System.out.println("Product not found."); return; }
        System.out.println("Editing: " + p.getName());
        System.out.print("New name: ");
        String name = scanner.nextLine().trim();
        System.out.print("New category: ");
        String category = scanner.nextLine().trim();
        System.out.print("New price: ");
        double price = readDouble();
        System.out.print("New stock: ");
        int stock = readInt();

        if (!name.isEmpty()) p.setName(name);
        if (!category.isEmpty()) p.setCategory(category);
        if (price > 0) p.setPrice(price);
        if (stock >= 0) p.setStock(stock);

        System.out.println("Updated: " + p.toString());
    }

    private void createProduct() {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Description: ");
        String description = scanner.nextLine().trim();
        System.out.print("Category: ");
        String category = scanner.nextLine().trim();
        System.out.print("Price: ");
        double price = readDouble();
        if (price <= 0) {
            System.out.println("Price must be greater than 0.");
            return;
        }
        System.out.print("Stock: ");
        int stock = readInt();
        if (stock < 0) {
            System.out.println("Stock cannot be negative.");
            return;
        }
        Product p = storeService.getCatalog().addProduct(name, description, category, price, stock);
        System.out.println("Product created: " + p.toString());
    }

    private void deleteProduct() {
        System.out.print("Product id: ");
        int id = readInt();
        Product p = storeService.getCatalog().findById(id);
        if (p == null) {
            System.out.println("Product not found.");
            return;
        }
        System.out.print("Are you sure you want to delete '" + p.getName() + "'? (y/N): ");
        String confirm = scanner.nextLine().trim();
        if (confirm.toLowerCase().equalsIgnoreCase("y")) {
            storeService.getCatalog().removeProduct(id);
            System.out.println("Product deleted.");
        } else {
            System.out.println("Deletion aborted.");
        }
    }

    private void addToCart() {
        System.out.print("Product ID: ");
        int id = readInt();
        Product p = storeService.getCatalog().findById(id);
        if (p == null) {
            System.out.println("Product not found.");
            return;
        }
        if (p.getStock() == 0) {
            System.out.println("Out of stock.");
            return;
        }
        System.out.print("Quantity: ");
        int quantity = readInt();
        try {
            storeService.getCart().addItem(p, quantity);
            System.out.println("Added to cart.");
        } catch (InvalidQuantityException e) {
            System.out.println("Couldn't add item to card due to error:");
            System.out.println(e.getMessage());
        }
    }

    private void removeFromCart() {
        System.out.print("Product ID: ");
        int id = readInt();
        if (storeService.getCart().removeItem(id))
            System.out.println("Item removed from cart.");
        else
            System.out.println("Item not in cart.");
    }

    private void viewCart() {
        if (storeService.getCart().isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println("Cart:");
        for (CartItem item : storeService.getCart().getItems()) {
            System.out.println(item);
        }
        System.out.println("Base total: $" + storeService.getCart().getBaseTotal());
    }
    private void viewDiscounts() {
        System.out.println("Active discounts:");
        for (Discount d : storeService.getDiscounts()) {
            System.out.println(d.getDescription());
        }
    }
    private void checkout() {
        if (storeService.getCart().isEmpty()) { System.out.println("Cart is empty."); return; }
        viewCart();
        System.out.print("Confirm purchase? (y/n): ");
        String confirm = scanner.nextLine().trim();
        if (!confirm.equalsIgnoreCase("y")) { System.out.println("Cancelled."); return; }
        Order order = storeService.checkout();
        System.out.println("\nPurchase confirmed: " + order);
    }









    private int readInt(){
        try {
            return scanner.nextInt();
        } catch (Exception e){
            return -1;
        }
    }
    private double readDouble() {
        try {
            return scanner.nextDouble();
        } catch (Exception e) {
            return -1;
        }
    }
}
