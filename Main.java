public class Main {
    public static void main(String[] args) {
        OrderBook engine = new OrderBook();

        System.out.println("--- MARKET OPEN ---");
        
        // 1. Seller enters the market wanting $100
        engine.addOrder(new Order("S1", false, 100.0, 10));
        
        // 2. Buyer enters only willing to pay $95 (No trade should happen)
        engine.addOrder(new Order("B1", true, 95.0, 5));
        
        // 3. A desperate buyer enters willing to pay $105! (Trade should execute)
        engine.addOrder(new Order("B2", true, 105.0, 15));
    }
}