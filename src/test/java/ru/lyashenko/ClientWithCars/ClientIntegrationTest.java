package ru.lyashenko.ClientWithCars;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.lyashenko.ClientWithCars.controller.ClientController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.lyashenko.ClientWithCars.TestData.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = "classpath:db/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:db/after_data_for_unit_test_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ClientIntegrationTest {
    private static final String REST_URL = "/client";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientController controller;

    @Test
    public void contextLoads() throws Exception {

        assertThat(controller).isNotNull();
    }

    @Test
    public void getAllClients() throws Exception {
        mockMvc
                .perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string("[{\"id\":1,\"name\":\"Genya\",\"birthday\":\"1989-01-22\",\"auto\":{\"id\":1,\"name\":\"Mazda\",\"birthday\":\"2000-05-11\"}},{\"id\":2,\"name\":\"Tanya\",\"birthday\":\"1994-12-25\",\"auto\":{\"id\":2,\"name\":\"Lada\",\"birthday\":\"2015-09-23\"}},{\"id\":3,\"name\":\"Lusi\",\"birthday\":\"2018-06-07\",\"auto\":{\"id\":3,\"name\":\"Tesla\",\"birthday\":\"2011-11-28\"}},{\"id\":4,\"name\":\"RuSoft\",\"birthday\":\"2005-05-11\",\"auto\":{\"id\":4,\"name\":\"Audi\",\"birthday\":\"2007-10-01\"}}]"));


    }

    @Test
    public void getClient() throws Exception {
        mockMvc.perform(get(REST_URL + "/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"id\":1,\"name\":\"Genya\",\"birthday\":\"1989-01-22\",\"auto\":{\"id\":1,\"name\":\"Mazda\",\"birthday\":\"2000-05-11\"}}"));

    }

    @Test
    public void createClient() throws Exception {
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Fedya\",\"birthday\":\"2005-01-17\",\"auto\":{\"name\":\"Mercedes\",\"birthday\":\"2014-12-23\"}}"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteClient() throws Exception{
        mockMvc.perform(delete(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Genya\",\"birthday\":\"1989-01-22\",\"auto\":{\"name\":\"Mazda\",\"birthday\":\"2000-05-11\"}}"))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertMatch(controller.list(), CLIENT_2,CLIENT_4,CLIENT_5) ;
    }
}
