package com.safetyNet.alert.model;

import java.util.List;

public class Home {

    String address;
    List<HomePerson> home;

    public Home(String address, List<HomePerson> home) {
        this.address = address;
        this.home = home;
    }

    public Home() {
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<HomePerson> getHome() {
        return home;
    }

    public void setHome(List<HomePerson> home) {
        this.home = home;
    }

}
