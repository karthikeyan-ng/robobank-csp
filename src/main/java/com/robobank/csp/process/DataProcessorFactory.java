package com.robobank.csp.process;

import com.robobank.csp.enums.FileType;

import lombok.NonNull;

/**
 * Bean Factory class which creates different bean implementation based on the
 * type of uploaded file (CSV or XML).
 * 
 * @author Karthikeyan N
 *
 */
public class DataProcessorFactory {
	
	/**
	 * Bean Factory method which creates implementation type and assign it to DataImporter 
	 * 
	 * @param filename
	 * @return
	 */
	public static DataImporter getFactory(@NonNull String filename) {
		
		DataImporter importer = null;
		
		if(FileType.isCsvFileType(filename)) {
			importer = new CsvImporter();
		} else if(FileType.isXmlFileType(filename)) {
			importer = new XmlImporter();
		}
		
		return importer;
	}

}
