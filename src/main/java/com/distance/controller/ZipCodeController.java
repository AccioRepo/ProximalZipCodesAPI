package com.distance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.distance.service.ZipCodeService;


@RestController
@RequestMapping("/api/zipcodes")
public class ZipCodeController {

    @Autowired
    private ZipCodeService zipCodeService;

    @GetMapping("/nearby")
    public List<String> getNearbyZipCodes(@RequestParam String zipcode, @RequestParam double distance) {
        return zipCodeService.findZipCodesWithinDistance(zipcode, distance);
    }
}