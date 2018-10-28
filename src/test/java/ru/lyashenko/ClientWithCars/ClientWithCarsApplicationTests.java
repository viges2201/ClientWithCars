package ru.lyashenko.ClientWithCars;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.lyashenko.ClientWithCars.controller.ClientController;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = "classpath:db/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:db/after_data_for_unit_test_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ClientWithCarsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientController controller;

    @Test
    public void contextLoads() throws Exception {

        assertThat(controller).isNotNull();
    }

    /*@Test
    public void getAllClients() throws Exception {
        MvcResult mvcResult = this.mockMvc
                .perform(get("/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();


    }*/

   /* @Test
    public void getClient() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(\);
    }*/
}
