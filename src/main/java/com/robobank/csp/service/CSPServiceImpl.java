/**
 * 
 */
package com.robobank.csp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
		
		//all transaction references should be unique
		Map<Long, List<Record>> groupByTransactionRef = 
				records.stream()
	    				 .collect(Collectors.groupingBy(record -> record.getTransactionReference()));

		List<Record> duplicateTransactionRef = new ArrayList<>();
		groupByTransactionRef.forEach((k, v) -> {
			if(v.size() > 1) {
				duplicateTransactionRef.addAll(v);
			}
		});
	
		System.out.println(duplicateTransactionRef);
	}

}
