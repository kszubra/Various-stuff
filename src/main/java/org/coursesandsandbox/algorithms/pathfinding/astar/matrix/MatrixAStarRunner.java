package org.coursesandsandbox.algorithms.pathfinding.astar.matrix;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.coursesandsandbox.algorithms.pathfinding.astar.matrix.AStarPathfinder.printMatrixAsChars;
import static org.coursesandsandbox.algorithms.pathfinding.astar.matrix.TestData.getRandomMatrix;

public class MatrixAStarRunner {

    public static void main(String[] args) {
        LocalDateTime start =  LocalDateTime.now();
        int xSize = 30;
        int ySize = 30;
        System.out.println("Process started at: " + start.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH::mm:ss")));
        int[][] maze = getRandomMatrix(xSize, ySize);

        AStarPathfinder aStar = new AStarPathfinder(maze, 0, 0, true);
        List<PathNode> path = aStar.findPathTo(xSize-1, ySize-1);

        if (path != null) {
            path.forEach((n) -> {
                System.out.print("[" + n.coordinateX + ", " + n.coordinateY + "] ");
                maze[n.coordinateY][n.coordinateX] = -1;
            });
            path.forEach(node -> maze[node.coordinateX][node.coordinateY] = 9);
            printMatrixAsChars(maze, path);
            System.out.print("\nTotal steps: " + path.size());
            System.out.printf("\nTotal cost: %.02f\n", path.get(path.size() - 1).movementCostToNode);
        } else {
            System.out.println("NO PATH");
        }
        LocalDateTime finish = LocalDateTime.now();
        System.out.println("Process finished at: " + finish.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH::mm:ss")));
        System.out.printf("Total time: %d s", (ChronoUnit.SECONDS.between(start, finish)));
    }
}
