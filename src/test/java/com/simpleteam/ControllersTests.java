package com.simpleteam;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RegistrationServiceApplication.class)
@WebAppConfiguration
public class ControllersTests {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .build();
    }

    @Test
    public void registrationRedirectTest() throws Exception {
        this.mockMvc
                .perform(get("/")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/registration"));
    }

    @Test
    public void registrationGetTest() throws Exception {
        this.mockMvc
                .perform(get("/registration")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }

    @Test
    public void registrationPostCorrectDataTest() throws Exception {
        this.mockMvc
                .perform(post("/registration")
                        .param("email", "some@gmail.com")
                        .param("password", "pa!ss2wo2rd")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }

    @Test
    public void successGetTest() throws Exception {
        this.mockMvc
                .perform(get("/success")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("success"));
    }

    @Test
    public void confirmGetTest() throws Exception {
        this.mockMvc
                .perform(get("/confirm/{code}", "asdfalksdjfh")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/error"));
    }

}
