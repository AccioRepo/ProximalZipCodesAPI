package com.distance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.distance.model.ApiResponse;
import com.distance.service.ZipCodeService;


@RestController
// @RequestMapping("/api/zipcodes")
public class ZipCodeController {

    private final ZipCodeService zipCodeService;

    public ZipCodeController(ZipCodeService zipCodeService) {
        this.zipCodeService = zipCodeService;
    }

    @GetMapping("/api/zipcodes/nearby")
    public ApiResponse getNearbyZipCodes(@RequestParam String zipcode, @RequestParam double distance) {
        return zipCodeService.findZipCodesWithinDistance(zipcode, distance);
    }
}