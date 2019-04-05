package com.robobank.csp.controller;

import java.nio.file.Files;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Karthikeyan N
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class CustomerStatementProcessorControllerTest {

	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.webApplicationContext);
		this.mockMvc = builder.build();
	}
	
	@Test
    public void processDataUsingCsvFile() throws Exception {
		
		Resource resource = resourceLoader.getResource("classpath:records.csv");
		byte[] csvFileBytes = Files.readAllBytes(resource.getFile().toPath());
		
        MockMultipartFile mockCsvFile = new MockMultipartFile("data", "records.csv", "text/plain", csvFileBytes);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.fileUpload("/files").file(mockCsvFile);
        
        this.mockMvc.perform(builder)
        			.andReturn();
        
    }
	
	@Test
    public void processDataUsingXmlFile() throws Exception {
		
		Resource resource = resourceLoader.getResource("classpath:records.xml");
		byte[] csvFileBytes = Files.readAllBytes(resource.getFile().toPath());
		
        MockMultipartFile mockCsvFile = new MockMultipartFile("data", "records.xml", "text/plain", csvFileBytes);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.fileUpload("/files").file(mockCsvFile);
        
        this.mockMvc.perform(builder)
        			.andReturn();
        
    }
	
}
