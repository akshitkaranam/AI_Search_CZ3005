package com.akshitkaranam.Utilities;

import java.util.ArrayList;
import java.util.Objects;

public class Node {
    private final int nodeNumber;
    private ArrayList<Integer> neighbours;
    private int[] coordinates;

    public Node(int nodeNumber) {

        this.nodeNumber = nodeNumber;
    }

    public ArrayList<Integer> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<Integer> neighbours) {
        this.neighbours = neighbours;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[] coordinates) {

        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return nodeNumber == node.nodeNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeNumber);
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeNumber=" + nodeNumber +
                ", coordinates=" + coordinates +
                '}';
    }

    public int getNodeNumber() {
        return nodeNumber;
    }
}
