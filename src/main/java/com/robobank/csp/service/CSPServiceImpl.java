/**
 * 
 */
package com.robobank.csp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.robobank.csp.data.Record;
import com.robobank.csp.process.DataImporter;
import com.robobank.csp.process.DataProcessorFactory;

/**
 * @author Karthikeyan N
 *
 */
@Service
public class CSPServiceImpl implements CSPService {

	@Override
	public void validateAndProcess(MultipartFile file) {
		
		DataImporter importer = DataProcessorFactory.getFactory(file.getOriginalFilename());
		List<Record> records = importer.doImport(file);
		System.out.println(records);

		
	}

}
