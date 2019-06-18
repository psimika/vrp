import java.util.ArrayList;
import java.util.Random;

public class Problem {

    private ArrayList<Node> customers;
    private ArrayList<Node> allNodes;
    private double[][] distanceMatrix;

    public void createAllNodesAndCustomerLists(int numberOfCustomers, int seed) {
        // Create the list with the customers.
        customers = new ArrayList<Node>();
        
        Random ran = new Random(seed);
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

    public void printDistanceMatrix() {
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

    public ArrayList<Node> getCustomers() {
        return customers;
    }

    public ArrayList<Node> getAllNodes() {
        return allNodes;
    }
   
    public double[][] getDistanceMatrix() {
        return distanceMatrix;
    }
}



