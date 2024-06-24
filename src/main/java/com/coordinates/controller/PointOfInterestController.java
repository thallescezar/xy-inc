package com.coordinates.controller;

import com.coordinates.model.PointOfInterest;
import com.coordinates.services.PointOfInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/pointsofinterest")
public class PointOfInterestController {

    @Autowired
    private PointOfInterestService pointOfInterestService;

    @GetMapping
    public ResponseEntity<List<PointOfInterest>> listPointsOfInterest() {
        return new ResponseEntity<>(pointOfInterestService.findAllPointsOfInterest(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PointOfInterest> createPointOfInterest(@RequestBody @NonNull PointOfInterest pointOfInterest) {
        return new ResponseEntity<>(pointOfInterestService.createPointOfInterest(pointOfInterest), HttpStatus.CREATED);
    }

    @GetMapping("/{xCoord}/{yCoord}/{maxDistance}")
    public ResponseEntity<List<PointOfInterest>> listPointsOfInterestByProximity(@PathVariable long xCoord,
                                                                                 @PathVariable long yCoord,
                                                                                 @PathVariable long maxDistance) {

        return new ResponseEntity<>(pointOfInterestService.listByProximity(xCoord, yCoord, maxDistance), HttpStatus.OK);
    }
}
