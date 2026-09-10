# Algorithmic Order Book Matching Engine

A high-performance financial matching engine built in Java, designed to simulate the core infrastructure of modern stock and cryptocurrency exchanges. This engine processes bids and asks in real-time, executing trades based on price priority.

## System Architecture

The core routing logic relies on two opposing **Priority Queues (Heaps)** to maintain a perfectly sorted state of the market without requiring full-array iterations:

*   **Bid Queue (Buyers):** Implemented as a Max-Heap. Bids are sorted in descending order so the highest willing buyer is always at the root.
*   **Ask Queue (Sellers):** Implemented as a Min-Heap. Asks are sorted in ascending order so the lowest willing seller is always at the root.

### Mathematical Time Complexity

By avoiding standard linear data structures like `ArrayList`, the engine achieves optimal execution speeds for continuous matching:

*   **Order Insertion:** $\mathcal{O}(\log n)$
*   **Price Discovery (Top of Book):** $\mathcal{O}(1)$
*   **Trade Execution & Removal:** $\mathcal{O}(\log n)$

*(Where $n$ is the number of active resting orders in the book).*

## Execution Flow

1. An `Order` object is instantiated with an ID, side (Buy/Sell), price, and quantity.
2. The engine routes the order to the respective Priority Queue.
3. The `matchOrders()` algorithm evaluates the roots of both heaps.
4. If the Highest Bid $\ge$ Lowest Ask, a trade executes at the resting order's price.
5. Quantities are decremented. Fully filled orders are removed via `poll()`, while partial fills remain at the root.

##  Future Roadmap

This foundational logic is the first phase of a broader quantitative infrastructure project. Upcoming iterations will include:
- **Price-Time Priority (FIFO):** Adding microsecond timestamps to ensure fair matching when bids are identical.
- **Language Migration:** Porting the core engine to **C++** to explore memory management and low-latency execution.
- **Data Analytics:** Interfacing the engine's output logs with a **Python (pandas/NumPy)** stack for backtesting and market depth visualization.

##  Running the Engine

To compile and run the market simulation locally:

```bash
javac Main.java Order.java OrderBook.java
java Main
