package org.bitnick.portfolio.controller.rest;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.Api;
import org.bitnick.portfolio.service.AlgorithmService;
import org.bitnick.portfolio.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/algorithm")
@Api(value = "AlgorithmControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlgorithmsController {
    private AlgorithmService algorithmService;

    @Autowired
    public void setAlgorithmService(AlgorithmService algorithmService) {
        this.algorithmService = algorithmService;
    }

    @RequestMapping(path = "duplicate/remove", method = RequestMethod.POST)
    public String[] removeDuplicates(@RequestBody String[] array) {
        return algorithmService.removeDuplicates(array);
    }

    @RequestMapping(path = "binarysearch/{num}", method = RequestMethod.POST)
    public Boolean binarySearch(@RequestBody Integer[] array, @PathVariable("num") int num) {
        Arrays.sort(array);

        return algorithmService.binarySearch(array, num);
    }

    @RequestMapping(path = "bubblesort", method = RequestMethod.POST)
    public Integer[] bubbleSort(@RequestBody Integer[] array) {
        return algorithmService.bubbleSort(array, array.length);
    }

    @RequestMapping(path = "shellsort", method = RequestMethod.POST)
    public Integer[] shellSort(@RequestBody Integer[] array) {
        return algorithmService.shellSort(array);
    }
}
