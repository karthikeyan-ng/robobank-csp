package com.robobank.csp.exception;

/**
 * @author Karthikeyan N
 *
 */
public class ExceptionMessage {

	private ExceptionMessage() {}
	
	public static final String FILE_VALIDATION_MESSAGE = "Unable to process given file '%s'. Expected file type is .csv or .xml";
	
	public static final String INVALID_CSV_LINE = "CSV file record [%s] must have 6 portions";
}
