package com.akshitkaranam.Utilities;

import java.util.HashMap;
import java.util.Map;

public class Graph {

    private Map<Integer, Node> nodes;
    private Map<EdgeKey, Edge> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashMap<>();

    }

    public Map<Integer, Node> getNodes() {
        return nodes;
    }

    public void setNodes(Map<Integer, Node> nodes) {
        this.nodes = nodes;
    }

    public Map<EdgeKey, Edge> getEdges() {
        return edges;
    }

    public void setEdges(Map<EdgeKey, Edge> edges) {
        this.edges = edges;
    }
}
