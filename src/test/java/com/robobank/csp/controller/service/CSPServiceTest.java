package com.robobank.csp.controller.service;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import com.robobank.csp.data.Record;
import com.robobank.csp.data.ValidationResponse;
import com.robobank.csp.service.CSPService;

/**
 * @author Karthikeyan N
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CSPServiceTest {

	@Autowired
	ResourceLoader resourceLoader;

	@Autowired
	private CSPService cspService;

	@Test
	public void shouldreadAndValidate() throws Exception {
		List<ValidationResponse> results = new ArrayList<>();
		ValidationResponse result1 = new ValidationResponse(
				new Record(112806L, "NL27SNSB0917829871", 91.23, "+", +15.57, "Clothes for Willem Dekker", 106.8));
		ValidationResponse result2 = new ValidationResponse(
				new Record(112806L, "NL69ABNA0433647324", 90.83, "-", -10.91, "Clothes for Richard de Vries", 79.92));
		ValidationResponse result3 = new ValidationResponse(
				new Record(112806L, "NL93ABNA0585619023", 102.12, "+", +45.87, "Tickets from Richard Bakker", 147.99));
		ValidationResponse result4 = new ValidationResponse(
				new Record(194261L, "NL91RABO0315273637", 21.6, "-", -41.83, "Clothes from Jan Bakker", -20.23));
		ValidationResponse result5 = new ValidationResponse(
				new Record(179430L, "NL93ABNA0585619023", 23.96, "-", -27.43, "Clothes for Vincent Bakker", -3.47));
		results.add(result1);
		results.add(result2);
		results.add(result3);
		results.add(result4);
		results.add(result5);

		Resource resource = resourceLoader.getResource("classpath:records.csv");
		byte[] csvFileBytes = Files.readAllBytes(resource.getFile().toPath());

		MockMultipartFile mockCsvFile = new MockMultipartFile("data", "records.csv", "text/plain", csvFileBytes);

		Assert.assertEquals(results.get(0).getDescription(),
				cspService.readAndValidate(mockCsvFile).get(0).getDescription());

	}

}
