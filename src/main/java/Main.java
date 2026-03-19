import com.ecommerce.console.Console;
import com.ecommerce.storelogic.StoreService;

public class Main {
    public static void main(String[] args) {
        StoreService storeService = new StoreService();
        Console console = new Console(storeService);
        console.start();
    }
}
