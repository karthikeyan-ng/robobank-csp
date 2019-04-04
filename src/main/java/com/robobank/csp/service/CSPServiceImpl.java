/**
 * 
 */
package com.robobank.csp.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.robobank.csp.data.Record;
import com.robobank.csp.data.ValidationResponse;
import com.robobank.csp.process.DataImporter;
import com.robobank.csp.process.DataProcessorFactory;

/**
 * @author Karthikeyan N
 *
 */
@Service
public class CSPServiceImpl implements CSPService {

	@Override
	public List<ValidationResponse> validateAndProcess(MultipartFile file) {
		
		DataImporter importer = DataProcessorFactory.getFactory(file.getOriginalFilename());
		List<Record> records = importer.doImport(file);
		
		//all transaction references should be unique
		Map<Long, List<Record>> groupByTransactionRef = 
				records.stream()
	    				 .collect(Collectors.groupingBy(record -> record.getTransactionReference()));
		
		List<Record> validationResults = new ArrayList<>();
		groupByTransactionRef.forEach((k, v) -> {
			if(v.size() > 1) {
				validationResults.addAll(v);
			}
		});
	
		//the end balance needs to be validated
		records.stream().forEach(record -> {
			double balanceValue = new BigDecimal(record.getStartBalance()).add(new BigDecimal(record.getMutationValue())).doubleValue();
			if(balanceValue < 0.0d && record.getEndBalance() < 0.0d) {
				validationResults.add(record);
			}
		});
		
		//prepare response
		return validationResults.stream().map(ValidationResponse::new).collect(Collectors.toList());
		
	}

}
