package org.coursesandsandbox.algorithms.various;

public class BinarySearch {

    public static int performBinarySearchIteratively(int sortedArray[], int searchedValue){

        int lowerIndex = 0;
        int higherIndex = sortedArray.length-1;
        int result = -1; //default value if searchedValue doesn't exist

        while(lowerIndex <= higherIndex){
            int middleIndex = (lowerIndex+higherIndex)/2;

            if(sortedArray[middleIndex] < searchedValue) {
            lowerIndex = middleIndex+1;
            } else if (sortedArray[middleIndex] > searchedValue){
                higherIndex = middleIndex - 1;
            } else if (sortedArray[middleIndex] == searchedValue){
                result = middleIndex;
                break;
            }
        }

        return result;
    }

    public static int performBinarySearchRecursively(int sortedArray[], int searchedValue, int lowerIndex, int higherIndex){
        int middleIndex = (lowerIndex+higherIndex) / 2;

        if (higherIndex < lowerIndex){return -1;}
        else if (sortedArray[middleIndex]<searchedValue){
            return performBinarySearchRecursively(sortedArray, searchedValue, middleIndex+1, higherIndex);
        } else if (sortedArray[middleIndex]>searchedValue) {
            return performBinarySearchRecursively(sortedArray, searchedValue, lowerIndex, middleIndex-1);
        } else if (sortedArray[middleIndex] == searchedValue){
            return middleIndex;
        } else {return -1;}
    }
}
