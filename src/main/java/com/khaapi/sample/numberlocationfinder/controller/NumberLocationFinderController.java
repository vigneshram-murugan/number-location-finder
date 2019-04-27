package com.khaapi.sample.numberlocationfinder.controller;

import com.khaapi.sample.numberlocationfinder.service.NumberLocationFinderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;
import java.util.List;

@RestController
public class NumberLocationFinderController {

    @Autowired
    private NumberLocationFinderService numberLocationFinderService;

    @ApiOperation(value = "Lists available numbers in a locaton / area ")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Available Numbers")})
    @GetMapping(value = "number/search/{areaCode}", produces = "application/json")
    public ResponseEntity<?> getNumbers(@PathVariable(required = true, value = "areaCode") Integer areaCode) {
        ResponseEntity response = null;
        List<?> availableNumbers = null;
        try {
            availableNumbers = numberLocationFinderService.getAvailableNumbers(areaCode);
            if (availableNumbers == null || availableNumbers.size() == 0) {
                response = new ResponseEntity("No Numbers Found", HttpStatus.OK);
            } else {
                response = new ResponseEntity(availableNumbers, HttpStatus.OK);
            }
        } catch (Exception e) {
            response = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

}
