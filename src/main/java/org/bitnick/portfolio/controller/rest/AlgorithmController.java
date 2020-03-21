package org.bitnick.portfolio.controller.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RequestMapping(path = "/api/algorithm")
public class AlgorithmController {

    @RequestMapping(path = "dubplicate/remove")
    public String[] removeDuplicates(@RequestBody String[] array) {
        return (String[]) new HashSet(Arrays.asList(array)).toArray();
    }
}
