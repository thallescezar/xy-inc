package com.coordinates.services;

import com.coordinates.model.PointOfInterest;

import java.util.List;

public interface PointOfInterestService {

    List<PointOfInterest> findAllPointsOfInterest();

    PointOfInterest createPointOfInterest(PointOfInterest poi);

    List<PointOfInterest> listByProximity(long xCoord, long yCoord, long maxDistance);
}
