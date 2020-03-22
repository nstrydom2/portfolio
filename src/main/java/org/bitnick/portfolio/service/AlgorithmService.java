package org.bitnick.portfolio.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class AlgorithmService {
    public String[] removeDuplicates(String[] array) {
        return Arrays.stream(new HashSet(Arrays.asList(array)).toArray()).toArray(String[]::new);
    }

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
