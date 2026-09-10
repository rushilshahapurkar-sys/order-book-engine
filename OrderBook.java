import java.util.PriorityQueue;
import java.util.Comparator;

public class OrderBook {
    // The core data structures
    private PriorityQueue<Order> buyOrders;
    private PriorityQueue<Order> sellOrders;

    public OrderBook() {
        // Sort buys in DESCENDING order (Highest price gets priority)
        buyOrders = new PriorityQueue<>(Comparator.comparingDouble(Order::getPrice).reversed());
        
        // Sort sells in ASCENDING order (Lowest price gets priority)
        sellOrders = new PriorityQueue<>(Comparator.comparingDouble(Order::getPrice));
    }

    // Step 1: Receiving an order and putting it in the right queue
    public void addOrder(Order order) {
        if (order.isBuyOrder()) {
            buyOrders.add(order);
        } else {
            sellOrders.add(order);
        }
        System.out.println("Received: " + order.toString());
        
        // Trigger the matching engine
        matchOrders(); 
    }
    private void matchOrders() {
        // Keep matching as long as there are both buyers and sellers
        while (!buyOrders.isEmpty() && !sellOrders.isEmpty()) {
            Order highestBuy = buyOrders.peek(); // Just look at the top, don't remove yet
            Order lowestSell = sellOrders.peek();

            // If the buyer is willing to pay equal to or more than the seller's asking price
            if (highestBuy.getPrice() >= lowestSell.getPrice()) {
                
                // Find out how many units we can actually trade
                int tradeQuantity = Math.min(highestBuy.getQuantity(), lowestSell.getQuantity());
                
                // Execute the trade (usually at the seller's resting price)
                System.out.println("✅ TRADE EXECUTED: " + tradeQuantity + " units @ $" + lowestSell.getPrice() + 
                                   " (Buyer: " + highestBuy.getOrderId() + ", Seller: " + lowestSell.getOrderId() + ")");

                // Decrease the quantities
                highestBuy.decreaseQuantity(tradeQuantity);
                lowestSell.decreaseQuantity(tradeQuantity);

                // If an order is fully completely filled, remove it from the queue
                if (highestBuy.getQuantity() == 0) {
                    buyOrders.poll();
                }
                if (lowestSell.getQuantity() == 0) {
                    sellOrders.poll();
                }
            } else {
                // The highest buyer won't pay the lowest seller's price. The market is at a standstill.
                break;
            }
        }
    }
}