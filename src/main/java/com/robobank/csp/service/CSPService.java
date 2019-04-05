package com.robobank.csp.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.robobank.csp.data.ValidationResponse;

/**
 * Customer Statement Processor Service Interface
 * 
 * @author Karthikeyan N
 *
 */
public interface CSPService {

	/**
	 * Reads the uploaded file (CSV or XML) and parse the content and validate the data as per
	 * business requirement.
	 * 
	 * @param file uploaded customer statement records
	 * @return
	 */
	List<ValidationResponse> readAndValidate(MultipartFile file);

}
