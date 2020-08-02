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
package org.bitnick.portfolio.controller.rest;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.bitnick.portfolio.model.Product;
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
    @ApiOperation("Removes duplicate values from an string array")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = String[].class)})
    public String[] removeDuplicates(@RequestBody String[] array) {
        return algorithmService.removeDuplicates(array);
    }

    @RequestMapping(path = "binarysearch/{num}", method = RequestMethod.POST)
    @ApiOperation("Performs a binary search on an integer array")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = BinarySearchResult.class)})
    public BinarySearchResult binarySearch(@RequestBody Integer[] array, @PathVariable("num") int num) {
        Arrays.sort(array);

        BinarySearchResult binarySearchResult = new BinarySearchResult();
        binarySearchResult.setResult(algorithmService.binarySearch(array, num));

        return binarySearchResult;
    }

    private class BinarySearchResult {
        private boolean result;

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }
    }

    @RequestMapping(path = "bubblesort", method = RequestMethod.POST)
    @ApiOperation("Performs a standard bubble sort on an integer array")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Integer[].class)})
    public Integer[] bubbleSort(@RequestBody Integer[] array) {
        return algorithmService.bubbleSort(array, array.length);
    }

    @RequestMapping(path = "shellsort", method = RequestMethod.POST)
    @ApiOperation("Performs a standard shell sort on an integer array")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Integer[].class)})
    public Integer[] shellSort(@RequestBody Integer[] array) {
        return algorithmService.shellSort(array);
    }
}
