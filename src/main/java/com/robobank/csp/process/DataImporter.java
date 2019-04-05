package com.robobank.csp.process;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.robobank.csp.data.Record;

/**
 * This class is responsible for 
 * 	1. reading customer statement values
 *  2. parse the data 
 *  3. prepare the collected records.
 * 
 * @author Karthikeyan N
 *
 */
public interface DataImporter {

	/**
	 * This method does the following operations 
	 * 	1. reading customer statement values
	 *  2. parse the data 
	 *  3. prepare the collected records.
	 * 
	 * @param file
	 * @return
	 */
	List<Record> doImport(MultipartFile file);

}
