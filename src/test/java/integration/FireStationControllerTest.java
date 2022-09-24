package integration;

import com.safetyNet.alert.AlertApplication;
import com.safetyNet.alert.model.dao.FirestationDao;

import com.safetyNet.alert.model.FireStation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(classes = AlertApplication.class)
@AutoConfigureMockMvc
class FireStationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    FirestationDao firestationDao;


    @Test
    void postFirestation() throws Exception {
        mockMvc.perform(post("/firestation")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{ \"address\":\"Test\", \"station\":\"8\" }")
        );
        firestationDao.delete("Test");
    }

    @Test
    void putFirestation() throws Exception {
        FireStation fireStation = new FireStation("Test", "7");
        firestationDao.create(fireStation);
        mockMvc.perform(put("/firestation")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{ \"address\":\"Test\", \"station\":\"8\" }")
        );
        firestationDao.delete("Test");

    }

    @Test
    void deleteStationFirestation() throws Exception {
        FireStation fireStation = new FireStation("Test", "8");
        firestationDao.create(fireStation);
        mockMvc.perform(delete("/firestation/Test")

        );
    }
}