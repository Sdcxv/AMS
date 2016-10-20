package com.sdcxv.bs.controller;

import com.sdcxv.bs.service.EQDemoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by Xudong.Liu on 2016/10/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:springConfig/applicationContext.xml")
public class EQDemoControllerTest {
    private MockMvc mockMvc;

    @Mock
    private EQDemoService eqDemoService;

    @InjectMocks
    private EQDemoController eqDemoController;

    @Before
    public void setup() {
        // initialize mock object
        MockitoAnnotations.initMocks(this);
        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(eqDemoController).build();
    }

    @Test
    public void test() throws Exception {
//        MockMvc mockMvc = webAppContextSetup(context).build();
//        RequestBuilder setEq = post("/eq").contentType("application/json;charset=UTF-8").content(new ObjectMapper().writeValueAsString(new EQ()));
//        RequestBuilder getEq = get("/eq/client");
//        mockMvc.perform(setEq)
//                .andExpect(content().contentType("application/json;charset=UTF-8"));
        System.out.println("test");
    }
}