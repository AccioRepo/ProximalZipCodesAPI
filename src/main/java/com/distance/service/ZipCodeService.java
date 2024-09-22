package com.distance.service;



import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.distance.model.ZipCode;
import com.opencsv.CSVReader;

@Service
public class ZipCodeService {

    private List<ZipCode> zipCodes;

    // Constructor to initialize the zip codes when the service is created
    public ZipCodeService() {
        this.zipCodes = loadZipCodes();
    }

    // Method to load zip codes from a CSV file
    private List<ZipCode> loadZipCodes() {
        List<ZipCode> zipCodes = new ArrayList<>();
        try (InputStream is = getClass().getResourceAsStream("/zipcodes.csv");
             InputStreamReader reader = new InputStreamReader(is);
             CSVReader csvReader = new CSVReader(reader)) {
             
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                // Ensure we have valid data
                if (nextLine.length < 3) {
                    System.out.println("Skipping line: " + String.join(",", nextLine));
                    continue;  // Skip invalid lines
                }
                String zipcode = nextLine[0];
                double latitude = Double.valueOf(nextLine[1]);
                double longitude = Double.valueOf(nextLine[2]);
                zipCodes.add(new ZipCode(zipcode, latitude, longitude));
            }
        } catch (Exception e) {
            e.printStackTrace();  // Handle any exceptions (e.g., file not found, parsing issues)
        }
        System.out.println("Loaded ZipCodes: " + zipCodes);  // Debugging output
        return zipCodes;
    }

    // Method to calculate the distance between two ZipCodes
    private double calculateDistance(ZipCode zip1, ZipCode zip2) {
        // Haversine formula for distance calculation
        final int R = 3959; // Radius of Earth in miles
        double latDistance = Math.toRadians(zip2.getLatitude() - zip1.getLatitude());
        double lonDistance = Math.toRadians(zip2.getLongitude() - zip1.getLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(zip1.getLatitude())) * Math.cos(Math.toRadians(zip2.getLatitude())) *
                Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Convert to miles
    }

    // Method to find zip codes within a specified distance from a target zip code
    public List<String> findZipCodesWithinDistance(String targetZip, double distance) {
        ZipCode target = zipCodes.stream()
                .filter(z -> z.getZipcode().equals(targetZip))
                .findFirst()
                .orElse(null);

        if (target == null) {
            return new ArrayList<>();  // Return empty list if target zip code is not found
        }

        List<String> result = new ArrayList<>();
        for (ZipCode zip : zipCodes) {
            if (!zip.getZipcode().equals(targetZip)) {
                double calculatedDistance = calculateDistance(target, zip);
                if (calculatedDistance <= distance) {
                    result.add(zip.getZipcode());
                }
            }
        }
        return result;
    }
}