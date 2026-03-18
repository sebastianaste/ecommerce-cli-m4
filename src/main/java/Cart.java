import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int quantity){
        if (quantity <= 0) {
            throw new InvalidQuantityException(quantity);
        }

        for (CartItem item : items){
            if (item.getProduct().getId() == product.getId()){
                item.setQuantity(item.getQuantity()+quantity);
                return;
            }
        }
        items.add(new CartItem(product,quantity));
    }

    public boolean removeItem(int productId) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == productId) {
                items.remove(item);
                return true;
            }
        }
        return false;
    }

    public List<CartItem> getItems(){
        return items;
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public double getBaseTotal(){
        double total = 0;
        for (CartItem item: items){
            total += item.getSubtotal();
        }
        return total;
    }

    public void clear() {
        items.clear();
    }
}