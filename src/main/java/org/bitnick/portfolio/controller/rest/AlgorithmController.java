package org.bitnick.portfolio.controller.rest;

import io.swagger.annotations.Api;
import org.bitnick.portfolio.service.AlgorithmService;
import org.bitnick.portfolio.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;

@RestController
@RequestMapping(path = "/api/algorithm")
@Api(value = "AlgorithmControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlgorithmController {
    private AlgorithmService algorithmService;

    @Autowired
    public void setAlgorithmService(AlgorithmService algorithmService) {
        this.algorithmService = algorithmService;
    }

    @RequestMapping(path = "dubplicate/remove", method = RequestMethod.POST)
    public String[] removeDuplicates(@RequestBody String[] array) {
        return algorithmService.removeDuplicates(array);
    }
}
