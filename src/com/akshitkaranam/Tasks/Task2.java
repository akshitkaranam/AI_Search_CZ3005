package com.akshitkaranam.Tasks;

import com.akshitkaranam.Utilities.EdgeKey;
import com.akshitkaranam.Utilities.Graph;
import com.akshitkaranam.Utilities.Node;
import com.akshitkaranam.Utilities.Path;
import static com.akshitkaranam.Tasks.Misc.ENERGY_BUDGET;


import java.util.*;

public class Task2 {

    public static Path ucsRestricted(Graph graph, int sourceNodeNumber, int goalNodeNumber) {
        PriorityQueue<Path> frontier = new PriorityQueue<>(Comparator.comparing(Path::getDistance));
        Node sourceNode = graph.getNodes().get(sourceNodeNumber);
        frontier.add(new Path(sourceNode));
        HashMap<Node, List<Path>> visited = new HashMap<>();
        visited.put(sourceNode,new ArrayList<>());

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
                    if (visited.containsKey(neighbouringNode)) {
                        List<Path> paths = visited.get(neighbouringNode);
                        for (Path pathInNode : paths) {
                            if (newPathDistance >= pathInNode.getDistance() && newEnergy >= pathInNode.getEnergy()) {
                                tooAdd = false;
                                break;
                            }
                        }

                        if (tooAdd) {
                            List<Node> updatedPathNodes = new ArrayList<>(currentPathNodes);
                            updatedPathNodes.add(neighbouringNode);
                            Path updatedPath = new Path(updatedPathNodes, newPathDistance, newEnergy);
                            frontier.add(updatedPath);
                            visited.get(neighbouringNode).add(updatedPath);
                        }
                    } else {
                        List<Node> updatedPathNodes = new ArrayList<>(currentPathNodes);
                        updatedPathNodes.add(graph.getNodes().get(neighbouringEdgeInteger));
                        Path updatedPath = new Path(updatedPathNodes, newPathDistance, newEnergy);
                        frontier.add(updatedPath);
                        List<Path> pathToNode = new ArrayList<>();
                        pathToNode.add(updatedPath);
                        visited.put(neighbouringNode, pathToNode);
                    }
                }
            }
        }
        return null;
    }
}
