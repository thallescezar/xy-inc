package com.coordinates.services.impl;

import com.coordinates.exceptions.InvalidCoordinateException;
import com.coordinates.model.PointOfInterest;
import com.coordinates.persistence.PointOfInterestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class PointOfInterestServiceImplTest {

    @Mock
    private PointOfInterestRepository pointOfInterestRepository;

    @InjectMocks
    private PointOfInterestServiceImpl pointOfInterestService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllPointsOfInterest() {
        List<PointOfInterest> expectedPoints = Arrays.asList(
                new PointOfInterest("POI1", 10, 20),
                new PointOfInterest("POI2", 30, 40)
        );
        when(pointOfInterestRepository.findAll()).thenReturn(expectedPoints);

        List<PointOfInterest> actualPoints = pointOfInterestService.findAllPointsOfInterest();

        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testCreatePointOfInterest() {
        PointOfInterest poi = new PointOfInterest("POI", 5, 15);
        when(pointOfInterestRepository.save(poi)).thenReturn(poi);

        PointOfInterest createdPoi = pointOfInterestService.createPointOfInterest(poi);

        assertEquals(poi, createdPoi);
    }

    @Test
    public void testCreatePointOfInterestWithNegativeCoordinates() {
        PointOfInterest poi = new PointOfInterest("POI", -5, 15);

        assertThrows(InvalidCoordinateException.class, () -> {
            pointOfInterestService.createPointOfInterest(poi);
        });
    }

    @Test
    public void testListByProximity() {
        List<PointOfInterest> points = Arrays.asList(
                new PointOfInterest("Lanchonete", 27, 12),
                new PointOfInterest("Posto", 31, 18),
                new PointOfInterest("Joalheria", 15, 12),
                new PointOfInterest("Floricultura", 19, 21),
                new PointOfInterest("Pub", 12, 8),
                new PointOfInterest("Supermercado", 23, 6),
                new PointOfInterest("Churrascaria", 28, 2)
        );
        when(pointOfInterestRepository.findAll()).thenReturn(points);

        List<PointOfInterest> nearPoints = pointOfInterestService.listByProximity(20, 10, 10);

        assertEquals(4, nearPoints.size()); // Lanchonete, Joalheria, Pub e Supermercado
    }
}
