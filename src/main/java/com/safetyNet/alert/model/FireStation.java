package com.safetyNet.alert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FireStation {

    @Id
    @Column(name = "address")
    private String address;

    @Column(name = "station")
    private String station;

    public FireStation() {
    }

    public FireStation(String address, String station) {
        this.address = address;
        this.station = station;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "Firestations{" +
                "address='" + address + '\'' +
                ", station='" + station + '\'' +
                '}';
    }


}
