package com.akshitkaranam.Tasks;

import com.akshitkaranam.Utilities.Edge;
import com.akshitkaranam.Utilities.EdgeKey;
import com.akshitkaranam.Utilities.Graph;
import com.akshitkaranam.Utilities.Node;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Misc {

    public static int ENERGY_BUDGET = 287931;

    public static String readFileAsString(String file) {
        try {
            return new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Graph initialiseGraph() {

        Graph graph = new Graph();

        Map<Integer, Node> nodes = new HashMap<>();
        Map<EdgeKey, Edge> edges = new HashMap<>();
        String graph_dict_json = readFileAsString("src/com/akshitkaranam/Data/G.json");
        String dist_json = readFileAsString("src/com/akshitkaranam/Data/Dist.json");
        String cost_json = readFileAsString("src/com/akshitkaranam/Data/Cost.json");
        String coord_json = readFileAsString("src/com/akshitkaranam/Data/Coord.json");
        // convert JSON string to Java Map
        Map<String, Object> G = new Gson().fromJson(graph_dict_json, Map.class);
        Map<String, Object> Cost = new Gson().fromJson(cost_json, Map.class);
        Map<String, Object> Dist = new Gson().fromJson(dist_json, Map.class);
        Map<String, Object> Coord = new Gson().fromJson(coord_json, Map.class);


        for (Map.Entry<String, Object> entry : G.entrySet()) {
            int currentNodeNumber = Integer.parseInt(entry.getKey());
            Node mainNode;

            if (!nodes.containsKey(currentNodeNumber)) {
                mainNode = new Node(currentNodeNumber);
                nodes.put(currentNodeNumber, mainNode);
            } else {
                mainNode = nodes.get(currentNodeNumber);
            }
            ArrayList<String> stringList = (ArrayList<String>) entry.getValue();
            ArrayList<Integer> integerNeighbourNodesList = new ArrayList<>();
            for (String neighbourNodeString : stringList) {
                integerNeighbourNodesList.add(Integer.parseInt(neighbourNodeString));
            }
            mainNode.setNeighbours(integerNeighbourNodesList);

            for (Integer neighbour : integerNeighbourNodesList) {
                Node neighbourNode;
                if (nodes.containsKey(neighbour)) {
                    neighbourNode = nodes.get(neighbour);
                } else {
                    neighbourNode = new Node(neighbour);
                    nodes.put(neighbour, neighbourNode);
                }

                int mainNodeNumber = mainNode.getNodeNumber();
                int neighbourNodeNumber = neighbourNode.getNodeNumber();
                EdgeKey edgeKey = new EdgeKey(mainNodeNumber, neighbourNodeNumber);

                if (!edges.containsKey(edgeKey)) {
                    edges.put(edgeKey, new Edge(mainNode, neighbourNode));
                }
            }
        }

        for (Map.Entry<String, Object> entry : Coord.entrySet()) {
            int currentNodeNumber = Integer.parseInt(entry.getKey());
            Node currentNode = nodes.get(currentNodeNumber);
            ArrayList<Double> coordinateList = (ArrayList<Double>) entry.getValue();

            int[] array = {coordinateList.get(0).intValue(), coordinateList.get(1).intValue()};
            currentNode.setCoordinates(array);
        }


        for (Map.Entry<String, Object> entry : Cost.entrySet()) {
            String s = entry.getKey();
            String[] split = s.split(",");
            String[] list = split;
            ArrayList<Integer> edgeNodes = new ArrayList<>();
            for (String edgeNodeString : list) {
                edgeNodes.add(Integer.parseInt(edgeNodeString));
            }
            EdgeKey edgeKey = new EdgeKey(edgeNodes.get(0), edgeNodes.get(1));
            edges.get(edgeKey).setCost((Double) entry.getValue());
        }

        for (Map.Entry<String, Object> entry : Dist.entrySet()) {
            String s = entry.getKey();
            String[] split = s.split(",");
            String[] list = split;
            ArrayList<Integer> edgeNodes = new ArrayList<>();
            for (String edgeNodeString : list) {
                edgeNodes.add(Integer.parseInt(edgeNodeString));
            }
            EdgeKey edgeKey = new EdgeKey(edgeNodes.get(0), edgeNodes.get(1));
            edges.get(edgeKey).setDistance((Double) entry.getValue());
        }
        graph.setNodes(nodes);
        graph.setEdges(edges);
        return graph;
    }

    public static double heuristic(Graph graph, int sourceNodeNumber, int destinationNodeNumber) {
        Node sourceNode = graph.getNodes().get(sourceNodeNumber);
        Node destinationNode = graph.getNodes().get(destinationNodeNumber);
        double distanceX = Math.abs(sourceNode.getCoordinates()[0] - destinationNode.getCoordinates()[0]);
        double distanceY = Math.abs(sourceNode.getCoordinates()[1] - destinationNode.getCoordinates()[1]);
        return Math.sqrt((Math.pow(distanceX, 2) + Math.pow(distanceY, 2)));
    }
}
