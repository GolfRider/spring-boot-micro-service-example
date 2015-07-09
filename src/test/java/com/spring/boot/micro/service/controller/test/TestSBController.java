package com.spring.boot.micro.service.controller.test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.spring.boot.micro.services.example.MainApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainApplication.class)
@WebAppConfiguration
public class TestSBController {
	
	
	private static String URI = "/services/sample";
	private MockMvc mvc;
	private MediaType contentType = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype());

	@Autowired
	private WebApplicationContext context;
	
	@Before
    public void setUp() throws Exception {
        mvc=webAppContextSetup(context).build();
    }
	
	@Test
    public void getSBCode() throws Exception {
		
        mvc.perform(MockMvcRequestBuilders
        	.get(URI).param("area_code", "ABC_123")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$[0].code",is("ABC")))
            .andExpect(jsonPath("$[0].area",notNullValue()));
    }
	

}
