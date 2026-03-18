public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException(int quantity) {
        super("Invalid quantity: " + quantity + ". Should be greater than 0.");
    }
}
