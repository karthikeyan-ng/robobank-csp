/**
 * 
 */
package com.robobank.csp.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.robobank.csp.data.Record;
import com.robobank.csp.utils.ValidationUtils;

/**
 * @author Karthikeyan N
 *
 */
public class CsvImporter implements DataImporter {

	private static final String CSV_SPLIT_BY = ",";
	
	@Override
	public List<Record> doImport(MultipartFile file) {

		List<Record> records = new ArrayList<>();
		
		try {
            InputStream inputStream = file.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            br.lines().skip(1).forEachOrdered(line -> {
            	
            	String[] tokens = line.split(CSV_SPLIT_BY);
                if(tokens.length == 6) {
                	Record record = new Record();
                	record.setTransactionReference(ValidationUtils.validateAndGetTransactionReference(tokens[0]));
                	record.setAccountNumber(ValidationUtils.validateAndGetAccountNumber(tokens[1]));
                	record.setDescription(tokens[2]);
                	record.setStartBalance(Double.parseDouble(tokens[3]));
                	record.setMutationSymbol(ValidationUtils.getMutationSymbol(tokens[4]));
                	record.setMutationValue(ValidationUtils.getMutationValue(tokens[4]));
                	record.setEndBalance(Double.parseDouble(tokens[5]));
                	records.add(record);
                }
            });
            
        }catch (IOException e) {
            e.printStackTrace();
        }
		
		return records;
	}

}
