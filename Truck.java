import java.util.ArrayList;

public class Truck {
    public static final int MAX_CARGO = 1500; // Max capacity in kg.

    private ArrayList<Node> route;
    private int cargo = 0;

    public Truck(Node depot) {
        this.route = new ArrayList<Node>();
        this.route.add(depot);
    }

    public boolean addCustomer(Node customer) {
        if (this.cargo + customer.demand > MAX_CARGO) {
            return false;
        }
        this.cargo += customer.demand;
        route.add(customer);
        return true;
    }
}

