package com.robobank.csp.process;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.robobank.csp.data.Record;

public interface DataImporter {

	List<Record> doImport(MultipartFile file);

}
