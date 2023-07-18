package org.coursesandsandbox.algorithms.pathfinding.astar.matrix;

import lombok.Builder;

import java.util.Objects;

@Builder
public class PathNode implements Comparable {
    public PathNode parentNode;
    public int coordinateX;
    public int coordinateY;
    public double movementCostToNode;
    public double estimatedMovementCostToDestination;

    @Override
    public int compareTo(Object o) {
        PathNode that = (PathNode) o;
        double result =
                (this.movementCostToNode + this.estimatedMovementCostToDestination) - (that.movementCostToNode + that.estimatedMovementCostToDestination);
        System.out.flush();
        if (result > 0) {
            return 1;
        } else if (result < 1) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PathNode pathNode = (PathNode) o;
        return coordinateX == pathNode.coordinateX &&
                coordinateY == pathNode.coordinateY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinateX, coordinateY);
    }
}
