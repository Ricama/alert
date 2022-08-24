package com.safetyNet.alert.service;

public interface GenerateDataService {

    /**
     * if the database is empty read the data.json and save it in database
     *
     * @return null
     */
    void generateData();
}
