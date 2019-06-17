public class Node {
    public int ID;
    public int x;
    public int y;
    public int demand;
    public double serviceTime;

    public String toString() {
        return String.format(
            "{ID: %d, x: %d, y: %d, demand: %d, serviceTime: %f}", 
            this.ID, this.x, this.y, this.demand, this.serviceTime
        );
    }
}