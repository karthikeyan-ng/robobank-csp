/**
 * 
 */
package com.robobank.csp.controller.process;

import java.nio.file.Files;
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
import com.robobank.csp.process.CsvImporter;
import com.robobank.csp.process.DataImporter;
import com.robobank.csp.process.XmlImporter;

/**
 * @author Karthikeyan N
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataImporterTest {

	@Autowired
	ResourceLoader resourceLoader;

	@Test
	public void shouldImportCsvFile() throws Exception {
		Resource resource = resourceLoader.getResource("classpath:records.csv");
		byte[] csvFileBytes = Files.readAllBytes(resource.getFile().toPath());

		MockMultipartFile mockCsvFile = new MockMultipartFile("data", "records.csv", "text/plain", csvFileBytes);

		DataImporter importer = new CsvImporter();
		List<Record> records = importer.doImport(mockCsvFile);

		Assert.assertEquals(10, records.size());
	}
	
	@Test
	public void shouldImportXmlFile() throws Exception {
		Resource resource = resourceLoader.getResource("classpath:records.xml");
		byte[] csvFileBytes = Files.readAllBytes(resource.getFile().toPath());

		MockMultipartFile mockXmlFile = new MockMultipartFile("data", "records.xml", "text/plain", csvFileBytes);

		DataImporter importer = new XmlImporter();
		List<Record> records = importer.doImport(mockXmlFile);

		Assert.assertEquals(10, records.size());
	}
}
