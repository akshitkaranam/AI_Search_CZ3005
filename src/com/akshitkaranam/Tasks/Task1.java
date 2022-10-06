package com.akshitkaranam.Tasks;

import com.akshitkaranam.Utilities.EdgeKey;
import com.akshitkaranam.Utilities.Graph;
import com.akshitkaranam.Utilities.Node;
import com.akshitkaranam.Utilities.Path;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static com.akshitkaranam.Tasks.Misc.ENERGY_BUDGET;

public class Task1 {

    public static Path ucsUnrestricted(Graph graph, int sourceNodeNumber, int destinationNodeNumber) {

        PriorityQueue<Path> frontier = new PriorityQueue<>(Comparator.comparing(Path::getDistance));
        List<Integer> visited = new ArrayList<>();
        Node sourceNode = graph.getNodes().get(sourceNodeNumber);
        frontier.add(new Path(sourceNode));

        while (!frontier.isEmpty()) {
            Path path = frontier.poll();
            List<Node> currentPathNodes = path.getPathNodes();
            Node currentNode = currentPathNodes.get(currentPathNodes.size() - 1);
            double energyUsed = path.getEnergy();


            if (!visited.contains(currentNode.getNodeNumber())) {

                if (currentNode.getNodeNumber() == destinationNodeNumber) {
                    return path;
                }

                visited.add(currentNode.getNodeNumber());
                List<Integer> neighbouringEdges = currentNode.getNeighbours();
                for (Integer neighbouringEdgeInteger : neighbouringEdges) {
                    EdgeKey edgeKey = new EdgeKey(currentNode.getNodeNumber(), neighbouringEdgeInteger);
                    double pathDistance = path.getDistance() + graph.getEdges().get(edgeKey).getDistance();
                    double newEnergy = energyUsed + graph.getEdges().get(edgeKey).getCost();


                        List<Node> updatedPathNodes = new ArrayList<>(currentPathNodes);
                        updatedPathNodes.add(graph.getNodes().get(neighbouringEdgeInteger));
                        Path updatedPath = new Path(updatedPathNodes, pathDistance, newEnergy);
                        frontier.add(updatedPath);

                }
            }
        }
        return null;
    }
}
