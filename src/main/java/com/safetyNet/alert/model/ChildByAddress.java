package com.safetyNet.alert.model;

import java.util.List;

public class ChildByAddress {

    List<Child> child;
    List<Person> personList;

    public ChildByAddress(List<Child> child, List<Person> personList) {
        this.child = child;
        this.personList = personList;
    }

    public List<Child> getChild() {
        return child;
    }

    public void setChild(List<Child> child) {
        this.child = child;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public String toString() {
        return "ChildByAddress{" +
                "child=" + child +
                ", personList=" + personList +
                '}';
    }
}
