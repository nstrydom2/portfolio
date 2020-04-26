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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.bitnick.portfolio.model.User;
import org.bitnick.portfolio.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Api(value = "UsersControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {
    private UserService userService;

    private Logger LOG = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    @ApiOperation("Gets the product with specific id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = User.class)})
    public User getUser(@PathVariable(name = "id") String id) {
        return userService.getUser(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User saveUser(@RequestBody User userToSave) {
        return userService.saveUser(userToSave);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User userToUpdate, @PathVariable(name = "id") String id) {
        return userService.updateUser(userToUpdate, id);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable(name = "id") String id) {
        userService.deleteUser(id);
    }
}
