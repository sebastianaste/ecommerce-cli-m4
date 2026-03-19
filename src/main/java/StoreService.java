public class StoreService {
    private Catalog catalog;
    private Cart cart;
    //private Discount discount;

    public StoreService(){
        this.catalog = new Catalog();
        this.cart = new Cart();
    }


    public Cart getCart() {
        return cart;
    }

    public Catalog getCatalog() {
        return catalog;
    }

}
