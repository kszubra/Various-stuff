package org.coursesandsandbox.algorithms.various;
import java.util.Random;

public class QuickSort {

    private static void swap(int[] array, int indexA, int indexB){

        int temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
    }

    private static int getRandomPivotIndex(int leftIndex, int rightIndex){

        Random rng = new Random();
        return rng.nextInt((rightIndex - leftIndex)+1)+leftIndex; // gives the random value between left and right index
    }

    private static int partition(int[] array, int leftIndex, int rightIndex){

        swap(array, leftIndex, getRandomPivotIndex(leftIndex, rightIndex));
        int leftBorder = leftIndex+1;
        for (int i = leftBorder; i <= rightIndex; i++){
            if(array[i] < array[leftIndex]){
                swap(array, i, leftBorder++);
            }
        }
        swap(array, leftIndex, leftBorder-1);

        return leftBorder-1;

    }

    private static void quickSort(int[] arrayToSort, int leftIndex, int rightIndex){

        if (leftIndex < rightIndex+1){
            int p = partition(arrayToSort, leftIndex, rightIndex);
            quickSort(arrayToSort, leftIndex, p-1);
            quickSort(arrayToSort, p+1, rightIndex);
        }
    }

    public static void sort(int[] arrayToSort){
        quickSort(arrayToSort, 0, arrayToSort.length-1);
    }
}
