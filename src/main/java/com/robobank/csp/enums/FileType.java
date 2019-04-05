package com.robobank.csp.enums;

/**
 * @author Karthikeyan N
 *
 */
public enum FileType {

	CSV, XML;
	
	/**
	 * Validate the given file extention. Allowed values are csv or xml
	 * 
	 * @param fileName
	 * @return boolean
	 */
	public static boolean isCorrectFileType(String fileName) {
		return isCsvFileType(fileName) || isXmlFileType(fileName);
	}
	
	/**
	 * Check the given fileName is CSV type
	 * 
	 * @param fileName
	 * @return boolean
	 */
	public static boolean isCsvFileType(String fileName) {
		return fileName.toUpperCase().endsWith(CSV.name());
	}
	
	/**
	 * Check the given fileName is XML type
	 * 
	 * @param fileName
	 * @return boolean
	 */
	public static boolean isXmlFileType(String fileName) {
		return fileName.toUpperCase().endsWith(XML.name());
	}
}
