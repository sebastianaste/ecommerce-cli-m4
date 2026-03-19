import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<CartItem> items;
    private double subTotal;
    private double amountDiscount;
    private double total;


    public Order (List<CartItem> items){
        this.items = items;
    }

    public void calculateOrder(){
        for (CartItem item : items){
            this.subTotal += item.getSubtotal();
        }

    }
    public void applyDiscounts(List<Discount> discounts){
        for (Discount d : discounts){
            if (d.checkDiscount(items)) {
                this.amountDiscount += d.applyDiscount(this.subTotal);
            }
        }
        this.total = subTotal - amountDiscount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "items=" + items +
                ", subTotal=" + subTotal +
                ", amountDiscount=" + amountDiscount +
                ", total=" + total +
                '}';
    }
}
