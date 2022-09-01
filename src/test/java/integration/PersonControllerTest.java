package integration;

import com.safetyNet.alert.AlertApplication;
import com.safetyNet.alert.dao.PersonDao;
import com.safetyNet.alert.model.FireStation;
import com.safetyNet.alert.model.MedicalRecord;
import com.safetyNet.alert.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = AlertApplication.class)
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PersonDao personDao;

    @Test
    public void createTest() throws Exception {
        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"firstName\":\"Test\",\"lastName\":\"Test\",\"address\":\"8 test\",\"city\":\"Test\",\"zip\":\"1337\",\"phone\":\"425-452-562\",\"email\":\"test@email.com\"}")
        );
        personDao.delete("Test", "Test");
    }

    @Test
    public void deleteTest() throws Exception {
        MedicalRecord medicalRecord = null;
        FireStation fireStation = null;
        Person person = new Person("Test", "Test", "8 test", "Test", "1337", "425-452-562", "test@email.com", fireStation);
        personDao.create(person);
        mockMvc.perform(delete("/person")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"firstName\":\"Test\",\"lastName\":\"Test\",\"address\":\"8 test\",\"city\":\"Test\",\"zip\":\"1337\",\"phone\":\"425-452-562\",\"email\":\"test@email.com\"}")
        );
    }

    @Test
    public void putTest() throws Exception {
        MedicalRecord medicalRecord = null;
        FireStation fireStation = null;
        Person person = new Person("Test", "Test", "82 test", "Tests", "1337", "425-452-562", "test@email.com", fireStation);
        personDao.create(person);
        mockMvc.perform(put("/person")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"firstName\":\"Test\",\"lastName\":\"Test\",\"address\":\"8 test\",\"city\":\"Test\",\"zip\":\"1337\",\"phone\":\"425-452-562\",\"email\":\"test@email.com\"}")
        );
        personDao.delete("Test", "Test");
    }

    @Test
    public void getPersonByStationTest() throws Exception {
        mockMvc.perform(get("/firestation?stationNumber=4"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Lily")))
                .andExpect(content().string(containsString("Cooper")));
    }

    @Test
    public void ChildByAddressTest() throws Exception {
        mockMvc.perform(get("/childAlert?address=834 Binoc Ave"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Tessa")))
                .andExpect(content().string(containsString("Carman")));
    }

    @Test
    public void getPhoneByStationTest() throws Exception {
        mockMvc.perform(get("/phoneAlert?firestation=3"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("841-874-6512")));
    }

    @Test
    public void personByAddressTest() throws Exception {
        mockMvc.perform(get("/fire?address=834 Binoc Ave"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Tessa")))
                .andExpect(content().string(containsString("Carman")));
    }

    @Test
    public void getHomeByStationTest() throws Exception {
        mockMvc.perform(get("/flood/stations?station=3"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1509 Culver St")))
                .andExpect(content().string(containsString("834 Binoc Ave")))
                .andExpect(content().string(containsString("748 Townings Dr")))
                .andExpect(content().string(containsString("112 Steppes Pl")));
    }

    @Test
    public void personInfoTest() throws Exception {
        mockMvc.perform(get("/personInfo?firstName=Tessa&lastName=Carman"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("02/18/2012")))
                .andExpect(content().string(containsString("tenz@email.com")));
    }

    @Test
    public void getPersonByEmailTest() throws Exception {
        mockMvc.perform(get("/communityEmail?city=Culver"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("drk@email.com")));

    }
}
