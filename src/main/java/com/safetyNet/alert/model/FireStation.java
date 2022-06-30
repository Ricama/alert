package com.safetyNet.alert.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FireStation {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "station")
    private String station;
@OneToMany(
        mappedBy = "fireStation",cascade = CascadeType.ALL,orphanRemoval = true
)
    List<Person> personList = new ArrayList<>();

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
