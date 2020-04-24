/*
 * The MIT License
 *
 * Copyright (c) 2020, Nicholas Bruce Strydom
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.bitnick.portfolio.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

/**
 * This service is to demo examples of implementing different types of
 * algorithms including sorting, searching, and more.
 */
@Service
public class AlgorithmService {
    /**
     * One liner example of removing duplicate values from a given array.
     * @param array Target array to perform the operation
     * @return String[], Resulting array.
     */
    public String[] removeDuplicates(String[] array) {
        return Arrays.stream(new HashSet(Arrays.asList(array)).toArray()).toArray(String[]::new);
    }

    /**
     * Example of a binary search algorithm. This method takes the target
     * Integer array and the target Integer. Then performs a binary search
     * returning True is the number is present or False if it is not.
     * @param array Target array in which to perform the binary search
     * @param targetNum Target Integer we are searching for
     * @return Boolean, Result of the search
     */
    public Boolean binarySearch(Integer[] array, int targetNum) {
        int first = 0;
        int mid = array.length / 2;
        int last = array.length;

        if (last > 1) {
            if (targetNum < array[mid]) {
                return binarySearch(Arrays.copyOfRange(array, first, mid), targetNum);
            } else if (targetNum > array[mid]) {
                return binarySearch(Arrays.copyOfRange(array, mid, last), targetNum);
            } else {
                return targetNum == array[mid];
            }
        } else {
            return false;
        }
    }

    /**
     * Recursive example of a bubble sort algorithm. The method takes the
     * target Integer array and the arrays length as "n". Then it loops through
     * the array swapping the current Integer with the next one if the current
     * Integer is greater. Then subtracts 1 from "n" and recursively calls
     * itself.
     * @param array Target array to perform the sort
     * @param n
     * @return Integer[], Returns the resulting array after the sort
     */
    public Integer[] bubbleSort(Integer[] array, int n) {
        if (n == 1)
            return array;

        for (int iter = 0; iter < n - 1; iter++) {
            if (array[iter] > array[iter + 1]) {
                int temp = array[iter + 1];
                array[iter + 1] = array[iter];
                array[iter] = temp;
            }
        }

        return bubbleSort(array, n - 1);
    }

    /**
     * Example of a shell sort algorithm
     * @param array Target array
     * @return Integer[], Returns the resulting array after the sort
     */
    public Integer[] shellSort(Integer[] array) {
        int n = array.length;

        for (int gap = n/2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                int temp = array[i];
                int j;

                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }

                array[j] = temp;
            }
        }

        return array;
    }
}
