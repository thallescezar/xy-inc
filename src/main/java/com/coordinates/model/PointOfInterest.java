package com.coordinates.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PointOfInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private long xCoord;
    private long yCoord;

    public PointOfInterest() {

    }

    public PointOfInterest(String name, long xCoord, long yCoord) {
        this.name = name;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getxCoord() {
        return xCoord;
    }

    public void setxCoord(long xCoord) {
        this.xCoord = xCoord;
    }

    public long getyCoord() {
        return yCoord;
    }

    public void setyCoord(long yCoord) {
        this.yCoord = yCoord;
    }
}
