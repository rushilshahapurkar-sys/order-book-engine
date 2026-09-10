public class Order {
    // 1. The Properties (State)
    private String orderId;
    private boolean isBuyOrder; // true for Buy, false for Sell
    private double price;
    private int quantity;

    // 2. The Constructor (How we create a new order)
    public Order(String orderId, boolean isBuyOrder, double price, int quantity) {
        this.orderId = orderId;
        this.isBuyOrder = isBuyOrder;
        this.price = price;
        this.quantity = quantity;
    }

    // 3. Getters (How the matching engine will read the data)
    public String getOrderId() { return orderId; }
    public boolean isBuyOrder() { return isBuyOrder; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    // 4. Modifiers (For when a trade partially fills)
    public void decreaseQuantity(int amount) {
        this.quantity -= amount;
    }

    // 5. Output (So we can easily read it in the terminal)
    @Override
    public String toString() {
        String side = isBuyOrder ? "BUY" : "SELL";
        return side + " Order [" + orderId + "] -> " + quantity + " units @ $" + price;
    }
}