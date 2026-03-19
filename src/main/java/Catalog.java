import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Catalog {
    private List<Product> products = new ArrayList<>();
    private int nextId = 1;

    public Catalog() {
        addProduct("N64 OEM Controller - Gray", "Original hardware. Minor cosmetic wear on shell.", "N64 Controllers", 15000, 68);
        addProduct("N64 OEM Controller - Yellow", "Original hardware. Excellent condition.", "N64 Controllers", 15000, 30);
        addProduct("N64 HoriPAD Controller - Red", "Enthusiast-grade hardware. Ergonomic grip. Near-mint, tight thumbstick.", "N64 Controllers", 45000, 52);
        addProduct("N64 HoriPAD Controller - Green", "Enthusiast-grade hardware. Ergonomic grip. Tight stick, light surface scratches.", "N64 Controllers", 35000, 25);
        addProduct("Conker Bad Fur Day N64", "Rare-developed adventure. Mature themes. Authentic cartridge, clean label.", "N64 Games", 40000, 31);
        addProduct("Zelda Ocarina of Time N64", "Action-adventure RPG. Gold cartridge, minor label wear.", "N64 Games", 40000, 64);
        addProduct("Mischief Makers N64", "2D side-scrolling platformer. Cartridge only, tested working.", "N64 Games", 40000, 56);
        addProduct("Pokemon LeafGreen GBA", "GBA remake of Kanto region. Original internal battery tested. Label intact.", "GBA Games", 40000, 47);
        addProduct("Harvest Moon 64 N64", "Agricultural simulation RPG. High-collectible. Clean contacts, saves functional.", "N64 Games", 40000, 42);
        addProduct("Diddy Kong Racing 64 N64", "Multi-vehicle racing title. Adventure Mode supported. Cartridge shows shelf wear.", "N64 Games", 40000, 59);
        addProduct("Pokemon Sapphire GBA", "Third-generation entry. Hoenn region. Original translucent shell, intact label.", "GBA Games", 40000, 23);
        addProduct("Pokemon FireRed GBA", "Gen 1 remake on GBA. Updated visuals. Authentic Nintendo PCB, verified working.", "GBA Games", 40000, 35);
        addProduct("Banjo-Tooie N64", "Banjo-Kazooie sequel. Expanded world maps. Excellent cartridge and label condition.", "N64 Games", 40000, 0);
    }
    public void listProducts(){
        for (Product p : products){
            System.out.println(p.toString());
        }
    }

    public Product addProduct(String name, String description, String category, double price, int stock) {
        Product p = new Product(nextId++,name,description, category, price, stock);
        products.add(p);
        return p;
    }

    public Product findById(int id) {
        for (Product p : products){
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }

    public boolean removeProduct(int id) {
        for (Product p : products){
            if (p.getId() == id){
                products.remove(p);
                return true;
            }
        }
        return false;
    }

    public List<Product> searchByName(String name) {
        List<Product> productsFound = new ArrayList<>();
        for (Product p : products){
            if (p.getName().toLowerCase().contains(name.toLowerCase())){
                productsFound.add(p);
            }
        }
        return productsFound;
    }

    public List<Product> searchByCategory(String category) {
        List<Product> productsFound = new ArrayList<>();
        for (Product p : products){
            if (p.getCategory().toLowerCase().contains(category.toLowerCase())){
                productsFound.add(p);
            }
        }
        return productsFound;
    }

    public List<Product> getProductsSortedByName() {
        List<Product> sorted = new ArrayList<>(products);
        sorted.sort(Comparator.comparing(p -> p.getName()));
        return sorted;
    }

    public List<Product> getProductsSortedByPrice() {
        List<Product> sorted = new ArrayList<>(products);
        sorted.sort(Comparator.comparingDouble(p -> p.getPrice()));
        return sorted;
    }
}