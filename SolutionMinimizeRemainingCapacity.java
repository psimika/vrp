import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SolutionMinimizeRemainingCapacity {

    private Problem p;

    private ArrayList<Truck> routes;

    public static void main(String[] args) {
        SolutionMinimizeRemainingCapacity s = new SolutionMinimizeRemainingCapacity();
        s.solution();
    }

    public void solution() {
        int numberOfCustomers = 10;

        p = new Problem();
        // Generate problem, using birthday (9 feb 1998) as random seed.
        int seed = 9021998;
        p.createAllNodesAndCustomerLists(numberOfCustomers, seed);    

        // We need to start adding the customers with the highest demand first
        // so that we can minimize the remaining capacity in our trucks.
        sortByHighestDemand(p.getCustomers());
        
        routes = new ArrayList<Truck>();
        Node depot = p.getAllNodes().get(0);
        Truck truck = new Truck(depot);
        for (Node cust : p.getCustomers()) {
            // Will only add a customer if capacity allows.
            boolean ok = truck.addCustomer(cust);   
            // When we reach full capacity, we need a new truck.
            if (!ok) {  
                routes.add(truck);
                truck = new Truck(depot);   
                continue;
            }
        }
        // Add remaining truck.
        routes.add(truck);  

        System.out.println("To minimize our truck remaining capacity, we added the customers with highest demand first.");
        System.out.printf("We ended up with %d trucks:\n\n", routes.size());
        for (Truck t : routes) {
            System.out.println("-> " + t);
        }
    }

    private void sortByHighestDemand(ArrayList<Node> customers) {
        Collections.sort(customers, new Comparator<Node>(){
            public int compare(Node a, Node b){
                if (a.demand == b.demand) {
                    return 0;
                } 
                return a.demand > b.demand ? -1 : 1;
            }
        });
    }
}



