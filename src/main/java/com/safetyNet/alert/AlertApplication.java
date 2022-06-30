package com.safetyNet.alert;
import com.google.gson.Gson;
import com.safetyNet.alert.model.DataFireStation;
import com.safetyNet.alert.model.DataMedicalRecord;
import com.safetyNet.alert.model.DataPerson;
import com.safetyNet.alert.model.Person;
import com.safetyNet.alert.repository.FireStationRepository;
import com.safetyNet.alert.repository.MedicalRecordRepository;
import com.safetyNet.alert.repository.PersonRepository;
import com.safetyNet.alert.service.GenerateDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileReader;


@SpringBootApplication
public class AlertApplication {
	FireStationRepository fireStationRepository;
	MedicalRecordRepository medicalRecordRepository;
	PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(AlertApplication.class, args);

	}
@Bean
CommandLineRunner runner(GenerateDataService generateDataService) {

		return args -> {
			generateDataService.generateData();

		};
}
 public void bddTcheck() {

	 try{


		 var gson = new Gson();

		 BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/data.json"));

		 if (fireStationRepository.count() > 0 && medicalRecordRepository.count() > 0 && personRepository.count() > 0 ) {

			 DataPerson dataPerson = gson.fromJson(reader, DataPerson.class);
			 DataMedicalRecord dataMedicalRecord = gson.fromJson(reader, DataMedicalRecord.class);
			 DataFireStation dataFireStation = gson.fromJson(reader, DataFireStation.class);
			 for(int i = 0; i < dataPerson.getPersons().size(); i++ ){
				 personRepository.save(dataPerson.getPersons().get(i));
				 medicalRecordRepository.save(dataMedicalRecord.getMedicalrecords().get(i));

			 }
			 for(int i = 0; i < dataFireStation.getFirestations().size(); i++){
				 fireStationRepository.save(dataFireStation.getFirestations().get(i));
			 }

		 }
	 }
	 catch (Exception e){
		 System.out.println(e);
	 }
 }
}
