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
import static com.akshitkaranam.Tasks.Misc.heuristic;

public class Task3 {

    public static Path aStar(Graph graph, int sourceNodeNumber, int destinationNodeNumber) {
        PriorityQueue<Path> pq = new PriorityQueue<>(Comparator.comparing(Path::getScore));
        List<EdgeKey> visited = new ArrayList<>();
        Node sourceNode = graph.getNodes().get(sourceNodeNumber);
        pq.add(new Path(sourceNode));

        while (!pq.isEmpty()) {
            Path path = pq.poll();

            List<Node> currentPathNodes = path.getPathNodes();
            Node currentNode = currentPathNodes.get(currentPathNodes.size() - 1);
            double energyUsed = path.getEnergy();

            if (currentNode.getNodeNumber() == destinationNodeNumber) {
                return path;
            }


            List<Integer> neighbouringEdges = currentNode.getNeighbours();
            for (Integer neighbouringEdgeInteger : neighbouringEdges) {
                EdgeKey edgeKey = new EdgeKey(currentNode.getNodeNumber(), neighbouringEdgeInteger);

                if (!visited.contains(edgeKey)) {
                    visited.add(edgeKey);
                    double newEnergy = energyUsed + graph.getEdges().get(edgeKey).getCost();
                    if (newEnergy <= ENERGY_BUDGET) {
                        double pathDistance = path.getDistance() + graph.getEdges().get(edgeKey).getDistance();
                        double heuristicDistance = heuristic(graph, neighbouringEdgeInteger, destinationNodeNumber);
                        double score = heuristicDistance + pathDistance;
                        List<Node> updatedPathNodes = new ArrayList<>(currentPathNodes);
                        updatedPathNodes.add(graph.getNodes().get(neighbouringEdgeInteger));
                        Path updatedPath = new Path(updatedPathNodes, pathDistance, newEnergy, score);
//                        System.out.println(pathDistance+ ", " + heuristicDistance + ", " +  score + " path: "+updatedPath);

                        pq.add(updatedPath);
                    }
                }
            }
        }
        return null;
    }

}
