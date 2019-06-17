import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    private ArrayList<Node> customers;
    private ArrayList<Node> allNodes;
    private double[][] distanceMatrix;

    public static void main(String[] args) {
        Main m = new Main();
        int numberOfCustomers = 10;
        m.createAllNodesAndCustomerLists(numberOfCustomers);
        //System.out.println(m.customers);
        //System.out.println(m.allNodes);

        m.sortByDemandReverse(m.customers);
        //System.out.println(m.customers);
        ArrayList<Truck> routes = new ArrayList<Truck>();
        Node depot = m.allNodes.get(0);
        Truck truck = new Truck(depot);
        for (Node cust : m.customers) {
            boolean ok = truck.addCustomer(cust);
            if (!ok) {
                routes.add(truck);
                truck = new Truck(depot);
                continue;
            }
        }
        routes.add(truck);
        for (Truck t : routes) {
            System.out.println(t);
        }
    }

    public void createAllNodesAndCustomerLists(int numberOfCustomers) {
        // Create the list with the customers.
        customers = new ArrayList<Node>();
        int birthday = 9021998; // if your bday is on 9 feb 1998
        Random ran = new Random(birthday);
        for (int i = 0 ; i < numberOfCustomers; i++) {
            Node cust = new Node();
            cust.x = ran.nextInt(100);
            cust.y = ran.nextInt(100);
            cust.demand = 100*(1 + ran.nextInt(5));
            cust.serviceTime = 0.25;
            customers.add(cust);
        }
        // Build the allNodes array and the corresponding distance matrix.
        allNodes = new ArrayList<Node>();
        Node depot = new Node();
        depot.x = 50;
        depot.y = 50;
        depot.demand = 0;
        allNodes.add(depot);
        for (int i = 0 ; i < customers.size(); i++) {
            Node cust = customers.get(i);
            allNodes.add(cust);
        }
        for (int i = 0 ; i < allNodes.size(); i++) {
            Node nd = allNodes.get(i);
            nd.ID = i;
        }

        this.distanceMatrix = new double [allNodes.size()][allNodes.size()];
        this.calculateDistanceMatrix(this.allNodes);
    }

    private void printDistanceMatrix() {
        for (int i = 0; i < this.distanceMatrix.length; i++) {
            for (int j = 0; j < this.distanceMatrix[i].length; j++) {
                System.out.print(this.distanceMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private void calculateDistanceMatrix(ArrayList<Node> allNodes) {
        for (int i = 0 ; i < allNodes.size(); i++) {
            Node from = allNodes.get(i);
            
            for (int j = 0 ; j < allNodes.size(); j++) {
                Node to = allNodes.get(j);
                
                double deltaX = (from.x - to.x);
                double deltaY = (from.y - to.y);
                double distance = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
                
                distance = Math.round(distance);
                
                this.distanceMatrix[i][j] = distance;
            }
        }
    }

    public void sortByDemandReverse(ArrayList<Node> customers) {
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



