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
        int mid = array.length;
        int last = array.length - 1;

        if (targetNum < array[mid]) {
            return binarySearch(Arrays.copyOfRange(array, first, mid), targetNum);
        } else if (targetNum > array[mid]) {
            return binarySearch(Arrays.copyOfRange(array, mid, last), targetNum);
        } else if (targetNum == array[mid]) {
            return true;
        } else {
            return false;
        }
    }
}
