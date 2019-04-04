/**
 * 
 */
package com.robobank.csp.controller;

import static com.robobank.csp.consts.SwaggerApiUtils.IMPORT_API_DETAILS;
import static com.robobank.csp.consts.SwaggerApiUtils.REQ_METHOD_POST;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.robobank.csp.data.ValidationResponse;
import com.robobank.csp.exception.FileNotSupportedException;
import com.robobank.csp.service.CSPService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Karthikeyan N
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = CustomerStatementProcessorController.PATH)
@Api(value = CustomerStatementProcessorController.PATH)
public class CustomerStatementProcessorController {
	
	public static final String PATH = "/files";
	
	private CSPService cspService;
	
	public CustomerStatementProcessorController(CSPService cspService) {
		this.cspService = cspService;
	}
	
	@ApiOperation(value = IMPORT_API_DETAILS, httpMethod = REQ_METHOD_POST)
	@PostMapping(consumes = { "multipart/form-data" })
	public List<ValidationResponse> upload(@RequestParam("file") MultipartFile file) {
		
		// validate the input file extention (xml or csv)
		if(!(file.getOriginalFilename().endsWith("csv") || file.getOriginalFilename().endsWith("xml")))
			throw new FileNotSupportedException(String.format("Unable to process given file '%s'", file.getOriginalFilename()));
		
		return cspService.validateAndProcess(file);
		
	}

}
