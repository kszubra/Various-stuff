package org.coursesandsandbox.algorithms.pathfinding.astar.network;

public interface CostCalculator<T extends GraphNode> {
    double computeCost(T startPoint, T endPoint);
}
