package com.akshitkaranam.Utilities;


public class Edge {
    private final Node connectingNode1;
    private final Node connectingNode2;
    private double distance;
    private double cost;


    public Edge(Node connectingNode1, Node connectingNode2) {
        this.connectingNode1 = connectingNode1;
        this.connectingNode2 = connectingNode2;
        this.distance = 0;
        this.cost = 0;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Node getCurrentNode(int nodeNumber) {
        if (nodeNumber == connectingNode1.getNodeNumber()) {
            return connectingNode1;
        } else if (nodeNumber == connectingNode2.getNodeNumber()) {
            return connectingNode1;
        } else {
            return null;
        }


    }

    public Node getNeighbour(int nodeNumber) {
        if (nodeNumber == connectingNode1.getNodeNumber()) {
            return connectingNode2;
        }
        return connectingNode1;
    }


    @Override
    public String toString() {
        return "Edge{" +
                "connectingNode1=" + connectingNode1.getNodeNumber() +
                ", connectingNode2=" + connectingNode2.getNodeNumber() +
                ", distance=" + distance +
                ", cost=" + cost +
                '}';
    }
}
