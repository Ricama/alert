package integration;

import com.safetyNet.alert.AlertApplication;
import com.safetyNet.alert.model.FireStation;
import com.safetyNet.alert.model.MedicalRecord;
import com.safetyNet.alert.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = AlertApplication.class)
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createTest() throws Exception{
        MedicalRecord medicalRecord = new MedicalRecord();
        FireStation fireStation = new FireStation();
        Person person = new Person("Test","Test","8 test","Test","1337","425-452-562","test@email.com",medicalRecord,fireStation);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/person",person)
                                .contentType(MediaType.APPLICATION_JSON)

        );
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
