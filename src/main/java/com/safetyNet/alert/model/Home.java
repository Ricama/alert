package com.safetyNet.alert.model;

import java.util.List;

public class Home {

    List<HomePerson> home;

    public Home(List<HomePerson> home) {
        this.home = home;
    }

    public List<HomePerson> getHome() {
        return home;
    }

    public void setHome(List<HomePerson> home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "Home{" +
                "home=" + home +
                '}';
    }
}
