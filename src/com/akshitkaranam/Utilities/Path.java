package com.akshitkaranam.Utilities;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private final List<Node> pathNodes;
    private final double distance;
    private final double energy;
    private double score;

    // For initialisation
    public Path(Node sourceNode) {
        this.pathNodes = new ArrayList<>();
        this.pathNodes.add(sourceNode);
        this.distance = 0.00;
        this.energy = 0.00;
        this.score = 0.00;
    }

    //For Task 1 and Task 2
    public Path(List<Node> pathNodes, double distance, double energy) {
        this.pathNodes = pathNodes;
        this.distance = distance;
        this.energy = energy;
    }

    //For Task 3
    public Path(List<Node> pathNodes, double distance, double energy, double score) {
        this.pathNodes = pathNodes;
        this.distance = distance;
        this.energy = energy;
        this.score = score;
    }

    public double getEnergy() {
        return energy;
    }

    public double getDistance() {
        return distance;
    }



    public double getScore() {
        return score;
    }

    public List<Node> getPathNodes() {
        return pathNodes;
    }

    @Override
    public String toString() {
        StringBuilder stringToPrint = new StringBuilder();
        stringToPrint.append("Shortest path: ");
        int i = 0;
        for (Node node : pathNodes) {
            i++;

            if (i % 20 == 0) {
                stringToPrint.append("\n");
            }

            if (pathNodes.get(pathNodes.size() - 1) == node) {
                stringToPrint.append(node.getNodeNumber());
            } else {
                stringToPrint.append(node.getNodeNumber()).append(" -> ");
            }
        }
        stringToPrint.append("\n");
        stringToPrint.append("Shortest distance: ").append(this.distance);
        stringToPrint.append("\n");
        stringToPrint.append("Total energy cost: ").append(this.energy);


        return stringToPrint.toString();
    }

}
