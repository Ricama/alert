package com.safetyNet.alert.service;

import org.springframework.stereotype.Service;

@Service
public class GenerateDataServiveImpl implements GenerateDataService {

    @Override
    public void generateData() {
        System.out.println("Hello World");
    }
}
