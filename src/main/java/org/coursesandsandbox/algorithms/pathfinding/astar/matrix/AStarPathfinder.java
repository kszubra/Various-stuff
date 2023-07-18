package org.coursesandsandbox.algorithms.pathfinding.astar.matrix;

import java.util.*;

public class AStarPathfinder {
    private final List<PathNode> openNodes;
    private final List<PathNode> closedNodes;
    private final List<PathNode> path;
    private final int[][] travelCostMatrix;
    private PathNode currentNode;
    private final int startX;
    private final int startY;
    private int endX;
    private int endY;
    private final boolean diagonalCrossingAllowed;

    AStarPathfinder(int[][] travelCostMatrix, int startX, int startY, boolean diagonalCrossingAllowed) {
        this.openNodes = new ArrayList<>();
        this.closedNodes = new ArrayList<>();
        this.path = new ArrayList<>();
        this.travelCostMatrix = travelCostMatrix;
        this.currentNode = new PathNode(null, startX, startY, 0, 0);
        this.startX = startX;
        this.startY = startY;
        this.diagonalCrossingAllowed = diagonalCrossingAllowed;
    }

    public List<PathNode> findPathTo(int endX, int endY) {
        this.endX = endX;
        this.endY = endY;
        closedNodes.add(currentNode);
        addNeighborsToOpenList();
        while (currentNode.coordinateX != endX || currentNode.coordinateY != endY) {
            if (openNodes.isEmpty()) {
                return null;
            }
            currentNode = openNodes.get(0);
            openNodes.remove(0);
            closedNodes.add(currentNode);
            addNeighborsToOpenList();
        }
        path.add(0, currentNode);
        while (currentNode.coordinateX != startX || currentNode.coordinateY != startY) {
            currentNode = currentNode.parentNode;
            path.add(0, currentNode);
        }
        return path;
    }

    public static void printMatrixAsChars(int[][] m, List<PathNode> path){
        try{
            int rows = m.length;
            int columns = m[0].length;
            String str = "|\t";

            for(int i=0;i<rows;i++){
                for(int j=0;j<columns;j++){
                    if(path.contains(PathNode.builder().coordinateX(i).coordinateY(j).build())) {
                        str += "*" + "\t";
                    } else  {
                        str += "-" + "\t";
                    }
                }

                System.out.println(str + "|");
                str = "|\t";
            }

        }catch(Exception e){System.out.println("Matrix is empty!!");}
    }


    private PathNode createPathNode(int x, int y) {
        return PathNode.builder()
                .parentNode(currentNode)
                .coordinateX(currentNode.coordinateX + x)
                .coordinateY(currentNode.coordinateY + y)
                .movementCostToNode(currentNode.movementCostToNode)
                .estimatedMovementCostToDestination(distance(x, y))
                .build();
    }

    private boolean nodeBounds(int x, int y) {
        return currentNode.coordinateX + x >= 0 && currentNode.coordinateX + x < travelCostMatrix[0].length
                && currentNode.coordinateY + y >= 0 && currentNode.coordinateY + y < travelCostMatrix.length;
    }

    private boolean nodeNotBlocked (int x, int y) {
        return travelCostMatrix[currentNode.coordinateY + y][currentNode.coordinateX + x] != -1;
    }

    private boolean nodeNotProcessedAlready(PathNode pathNode) {
        return !findNeighborInList(openNodes, pathNode) && !findNeighborInList(closedNodes, pathNode);
    }

    private void addNeighborsToOpenList() {
        PathNode pathNode;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (!diagonalCrossingAllowed && x != 0 && y != 0) {
                    continue;
                }
                pathNode = createPathNode(x, y);
                if (
                        (x != 0 || y != 0) // not this.now
                                && nodeBounds(x, y)
                                && nodeNotBlocked(x, y)
                                && nodeNotProcessedAlready(pathNode)
                ) {
                    int BASE_MOVEMENT_COST = 1;
                    pathNode.movementCostToNode = pathNode.parentNode.movementCostToNode + BASE_MOVEMENT_COST; // Horizontal/vertical cost = 1.0
                    pathNode.movementCostToNode += travelCostMatrix[currentNode.coordinateY + y][currentNode.coordinateX + x]; // add movement cost for this square

                    this.openNodes.add(pathNode);
                }
            }
        }
        Collections.sort(this.openNodes);
    }

    private static boolean findNeighborInList(List<PathNode> array, PathNode pathNode) {
        return array.stream().anyMatch((n) -> (n.coordinateX == pathNode.coordinateX && n.coordinateY == pathNode.coordinateY));
    }

    private double distance(int dx, int dy) {
        if (this.diagonalCrossingAllowed) {
            return Math.hypot(currentNode.coordinateX + dx - endX, currentNode.coordinateY + dy - endY);
        } else {
            return Math.abs(currentNode.coordinateX + dx - endX) + Math.abs(currentNode.coordinateY + dy - endY);
        }
    }
}
