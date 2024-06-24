package com.coordinates.services.impl;

import com.coordinates.exceptions.InvalidCoordinateException;
import com.coordinates.model.PointOfInterest;
import com.coordinates.persistence.PointOfInterestRepository;
import com.coordinates.services.PointOfInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PointOfInterestServiceImpl implements PointOfInterestService {

    @Autowired
    public PointOfInterestRepository pointOfInterestRepository;

    @Override
    public List<PointOfInterest> findAllPointsOfInterest() {
        return pointOfInterestRepository.findAll();
    }

    @Override
    public PointOfInterest createPointOfInterest(PointOfInterest poi) {

        if(poi.getxCoord() < 0 || poi.getyCoord() < 0) {
            throw new InvalidCoordinateException("Coordinates must be a non-negative number.");
        }

        return pointOfInterestRepository.save(poi);
    }

    @Override
    public List<PointOfInterest> listByProximity(long xCoord, long yCoord, long maxDistance) {

        List<PointOfInterest> pointOfInterestList = this.findAllPointsOfInterest();

        pointOfInterestList = pointOfInterestList.stream()
                .filter(poi -> isWithinRange(xCoord, yCoord, maxDistance, poi))
                .collect(Collectors.toList());

        return pointOfInterestList;
    }

    private static boolean isWithinRange(long xCoord, long yCoord, long maxDistance, PointOfInterest poi) {

        long xDistance = Math.abs(poi.getxCoord() - xCoord);
        long yDistance = Math.abs(poi.getyCoord() - yCoord);

        long totalDistance = xDistance + yDistance;

        return totalDistance <= maxDistance;
    }
}
