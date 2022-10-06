package com.akshitkaranam.Tasks;

import com.akshitkaranam.Utilities.EdgeKey;
import com.akshitkaranam.Utilities.Graph;
import com.akshitkaranam.Utilities.Node;
import com.akshitkaranam.Utilities.Path;

import java.util.*;

import static com.akshitkaranam.Tasks.Misc.ENERGY_BUDGET;
import static com.akshitkaranam.Tasks.Misc.heuristic;

public class    Task3 {

    public static Path aStar(Graph graph, int sourceNodeNumber, int goalNodeNumber) {
        PriorityQueue<Path> frontier = new PriorityQueue<>(Comparator.comparing(Path::getScore));
        Node sourceNode = graph.getNodes().get(sourceNodeNumber);
        frontier.add(new Path(sourceNode));
        HashMap<Node, List<Path>> weights = new HashMap<>();
        weights.put(sourceNode,new ArrayList<>());

        while (!frontier.isEmpty()) {
            Path path = frontier.poll();
            List<Node> currentPathNodes = path.getPathNodes();
            Node currentNode = currentPathNodes.get(currentPathNodes.size() - 1);
            double energyUsed = path.getEnergy();

            if (currentNode.getNodeNumber() == goalNodeNumber) {
                return path;
            }

            List<Integer> neighbouringEdges = currentNode.getNeighbours();
            for (Integer neighbouringEdgeInteger : neighbouringEdges) {
                boolean tooAdd = true;
                EdgeKey edgeKey = new EdgeKey(currentNode.getNodeNumber(), neighbouringEdgeInteger);
                double newPathDistance = path.getDistance() + graph.getEdges().get(edgeKey).getDistance();
                double newEnergy = energyUsed + graph.getEdges().get(edgeKey).getCost();
                Node neighbouringNode = graph.getNodes().get(neighbouringEdgeInteger);

                if(newEnergy <= ENERGY_BUDGET){
                    if (weights.containsKey(neighbouringNode)) {
                        List<Path> paths = weights.get(neighbouringNode);
                        for (Path pathInNode : paths) {
                            if (newPathDistance >= pathInNode.getDistance() && newEnergy >= pathInNode.getEnergy()) {
                                tooAdd = false;
                                break;
                            }
                        }

                        if (tooAdd) {
                            double heuristicDistance = heuristic(graph, neighbouringEdgeInteger, goalNodeNumber);
                            double score = heuristicDistance + newPathDistance;
                            List<Node> updatedPathNodes = new ArrayList<>(currentPathNodes);
                            updatedPathNodes.add(neighbouringNode);
                            Path updatedPath = new Path(updatedPathNodes, newPathDistance, newEnergy,score);
                            frontier.add(updatedPath);
                            weights.get(neighbouringNode).add(updatedPath);
                        }
                    } else {
                        double heuristicDistance = heuristic(graph, neighbouringEdgeInteger, goalNodeNumber);
                        double score = heuristicDistance + newPathDistance;
                        List<Node> updatedPathNodes = new ArrayList<>(currentPathNodes);
                        updatedPathNodes.add(graph.getNodes().get(neighbouringEdgeInteger));
                        Path updatedPath = new Path(updatedPathNodes, newPathDistance, newEnergy,score);
                        frontier.add(updatedPath);
                        List<Path> pathToNode = new ArrayList<>();
                        pathToNode.add(updatedPath);
                        weights.put(neighbouringNode, pathToNode);
                    }
                }
            }
        }
        return null;
    }
}
