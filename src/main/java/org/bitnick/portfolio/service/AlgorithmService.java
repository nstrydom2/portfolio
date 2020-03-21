package org.bitnick.portfolio.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class AlgorithmService {
    public String[] removeDuplicates(String[] array) {
        return Arrays.stream(new HashSet(Arrays.asList(array)).toArray()).toArray(String[]::new);
    }
}
