/**
 * 
 */
package com.robobank.csp.process;

import lombok.NonNull;

/**
 * @author Karthikeyan N
 *
 */
public class DataProcessorFactory {

	public static DataImporter getFactory(@NonNull String originalFilename) {
		
		DataImporter importer = null;
		
		if(originalFilename.endsWith("csv")) {
			importer = new CsvImporter();
		} else if(originalFilename.endsWith("xml")) {
			importer = new XmlImporter();
		}
		
		return importer;
	}

}
