package com.robobank.csp.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.robobank.csp.data.Record;
import com.robobank.csp.data.ValidationResponse;
import com.robobank.csp.process.DataImporter;
import com.robobank.csp.process.DataProcessorFactory;

/**
 * Implementation of {@link CSPService}
 * 
 * @author Karthikeyan N
 *
 */
@Service
public class CSPServiceImpl implements CSPService {

	@Override
	public List<ValidationResponse> readAndValidate(MultipartFile file) {
		
		DataImporter importer = DataProcessorFactory.getFactory(file.getOriginalFilename());
		List<Record> records = importer.doImport(file);
		
		List<Record> validationResults = new ArrayList<>();
		validationResults.addAll(collectDuplicateTransactionReferences(records));
		validationResults.addAll(validateRecordEndBalance(records));
		
		//prepare response
		return validationResults.stream().map(ValidationResponse::new).collect(Collectors.toList());
	}

	/**
	 * Validate and collect Duplicate Transaction references records
	 * 
	 * @param records
	 * @return
	 */
	private List<Record> collectDuplicateTransactionReferences(List<Record> records) {
		Map<Long, List<Record>> groupByTransactionRef = records.stream()
				.collect(Collectors.groupingBy(record -> record.getTransactionReference()));
		
		List<Record> validationResults = new ArrayList<>();
		groupByTransactionRef.forEach((k, v) -> {
			if(v.size() > 1) {
				validationResults.addAll(v);
			}
		});
		
		return validationResults;
	}
	
	/**
	 * While making transaction validate the transaction (Start Balance - Mutation
	 * Value = End Balance). End Balance should not be a NEGATIVE amount.
	 * 
	 * @param records
	 * @return
	 */
	private List<Record> validateRecordEndBalance(List<Record> records) {
		Function<Record, Record> functionToValidateTransactionAmount = record -> {
			double balanceValue = new BigDecimal(record.getStartBalance())
					.add(new BigDecimal(record.getMutationValue())).doubleValue();
			if (balanceValue < 0.0d && record.getEndBalance() < 0.0d)
				return record;
			return null;
		};

		return records.stream()
					  .map(functionToValidateTransactionAmount)
					  .filter(Objects::nonNull)
					  .collect(Collectors.toList());
	}
}
